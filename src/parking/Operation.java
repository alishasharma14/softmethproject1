package parking;

import util.Date;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The user interface class that reads and processes command lines
 * from the terminal until there is a Q command is entered.
 * @author Alisha Sharma, Ann Sajan
 */
public class Operation {
     /** The number of data tokens needed to open a new deck after its number. */
    private static final int DECK_DATA_TOKENS = 3;
    /** The number of data tokens in an E command. */
    private static final int ENTER_TOKENS = 4;
    /** The number of data tokens in an X command. */
    private static final int EXIT_TOKENS = 3;
    /** The number of parts in a date such as 2026-03-09. */
    private static final int DATE_PARTS = 3;
    /** The number of parts in a time such as 10:48. */
    private static final int TIME_PARTS = 2;
    /** The longest digit string that safely fits in an int. */
    private static final int MAX_DIGITS = 9;
    /** The value returned for a token that is not a valid number. */
    private static final int INVALID = -1;

    /** The registered vehicles. */
    private VehicleList vehicles;
    /** The parking decks. */
    private DeckList decks;

    /**
     * Creates the user interface with an empty vehicle list and deck list.
     */
    public Operation() {
        vehicles = new VehicleList();
        decks = new DeckList();
    }

    /**
     * Sends a command to the method that handles it.
     * @param command the command.
     * @param tokens the data tokens after the command.
     */
    private void processCommand(String command, StringTokenizer tokens) {
        switch (command) {
            case "A" -> registerVehicle(tokens);
            case "R" -> removeVehicle(tokens);
            case "O" -> openDeck(tokens);
            case "C" -> closeDeck(tokens);
            case "E" -> enterDeck(tokens);
            case "X" -> exitDeck(tokens);
            case "PP" -> vehicles.printByPlate();
            case "PD" -> printDecks(tokens);
            case "PH" -> printHistory(tokens);
            default -> System.out.println(command + " is an invalid command!");
        }
    }

    /**
     * Checks if a token contains only digits.
     * @param token the token to check.
     * @return true if the token is not empty and all digits, otherwise false.
     */
    private boolean isDigits(String token) {
        if (token.isEmpty()) {
            return false;
        }
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     * Converts a token of digits to a number without risking an overflow.
     * @param token the token to convert.
     * @return the number, or INVALID if the token is not all digits or is too long.
     */
    private int toInt(String token) {
        if (!isDigits(token) || token.length() > MAX_DIGITS) {
            return INVALID;
        }
        return Integer.parseInt(token);
    }

    /**
     * Checks if there is another token and prints a message if there isn't.
     * @param tokens the data tokens.
     * @return true if there is another token, otherwise false.
     */
    private boolean hasToken(StringTokenizer tokens) {
        if (!tokens.hasMoreTokens()) {
            System.out.println("Missing data tokens.");
            return false;
        }
        return true;
    }

    /**
     * Registers a vehicle (A command).
     * @param tokens the data tokens: the license plate.
     */
    private void registerVehicle(StringTokenizer tokens) {
        if (!hasToken(tokens)) {
            return;
        }
        String plate = tokens.nextToken();
        Vehicle vehicle = new Vehicle(plate);
        if (!vehicle.isValid()) {
            System.out.println(plate + " - invalid license plate format.");
            return;
        }
        if (vehicles.contains(vehicle)) {
            System.out.println(plate + " is already registered.");
            return;
        }
        vehicles.add(vehicle);
        System.out.println(plate + " registered.");
    }

    /**
     * Unregisters a vehicle (R command).
     * @param tokens the data tokens: the license plate.
     */
    private void removeVehicle(StringTokenizer tokens) {
        if (!hasToken(tokens)) {
            return;
        }
        String plate = tokens.nextToken();
        Vehicle vehicle = new Vehicle(plate);
        if (!vehicle.isValid()) {
            System.out.println(plate + " - invalid license plate format.");
            return;
        }
        Vehicle registered = vehicles.getVehicle(vehicle);
        if (registered == null) {
            System.out.println(plate + " does not exist; cannot be unregistered.");
            return;
        }
        if (decks.findParked(registered) != null) {
            System.out.println("Cannot be unregistered - " + plate + " is in a deck.");
            return;
        }
        if (registered.hasHistory()) {
            System.out.println("Cannot be unregistered - " + plate
                    + " has parking history.");
            return;
        }
        vehicles.remove(registered);
        System.out.println(plate + " unregistered.");
    }

    /**
     * Opens a new deck or reopens a closed deck (O command).
     * @param tokens the data tokens: deck number, city, hours, capacity.
     */
    private void openDeck(StringTokenizer tokens) {
        if (!hasToken(tokens)) {
            return;
        }
        String numberToken = tokens.nextToken();
        if (!isDigits(numberToken)) {
            System.out.println(numberToken + " - invalid deck number.");
            return;
        }
        int number = Integer.parseInt(numberToken);
        Deck existing = decks.getDeck(new Deck(number));
        if (existing != null && existing.isOpen()) {
            System.out.println("Deck#" + number + " - is already open.");
        } else if (existing != null) {
            decks.open(existing);
            System.out.println("Deck#" + number + " - was closed, now reopened.");
        } else if (tokens.countTokens() < DECK_DATA_TOKENS) {
            System.out.println("Error opening Deck#" + number
                    + " - missing data tokens.");
        } else {
            createDeck(number, tokens);
        }
    }

    /**
     * Validates the city, hours, and capacity, then opens a new deck.
     * @param number the deck number.
     * @param tokens the data tokens: city, hours, capacity.
     */
    private void createDeck(int number, StringTokenizer tokens) {
        String city = tokens.nextToken();
        String hourCode = tokens.nextToken();
        String capacityToken = tokens.nextToken();
        Location location = Location.fromName(city);
        if (location == null) {
            System.out.println(city + " - invalid location.");
            return;
        }
        Hour hour = Hour.fromCode(hourCode);
        if (hour == null) {
            System.out.println(hourCode + " - invalid operation hours");
            return;
        }
        if (!isDigits(capacityToken)) {
            System.out.println(capacityToken
                    + " - invalid capacity; it's not an integer.");
            return;
        }
        int capacity = Integer.parseInt(capacityToken);
        if (capacity > Deck.MAXCAPACITY) {
            System.out.println(capacity + " - exceeds the maximum deck capacity "
                    + Deck.MAXCAPACITY);
            return;
        }
        decks.open(new Deck(number, location, hour, capacity));
        System.out.println("Deck#" + number + " opened.");
    }

    /**
     * Closes an open deck with no vehicles parked (C command).
     * @param tokens the data tokens: the deck number.
     */
    private void closeDeck(StringTokenizer tokens) {
        if (!hasToken(tokens)) {
            return;
        }
        String numberToken = tokens.nextToken();
        if (!isDigits(numberToken)) {
            System.out.println(numberToken
                    + " - invalid deck number; it contains characters.");
            return;
        }
        int number = Integer.parseInt(numberToken);
        Deck deck = decks.getDeck(new Deck(number));
        if (deck == null) {
            System.out.println("Deck#" + number + " - does not exist.");
        } else if (!deck.isOpen()) {
            System.out.println("Deck#" + number + " - is already closed.");
        } else if (deck.hasVehicles()) {
            System.out.println("Deck#" + number
                    + " - has vehicles parked; cannot be closed.");
        } else {
            decks.close(deck);
            System.out.println("Deck#" + number + " - closed.");
        }
    }
    /**
     * Checks that a deck can accept a vehicle: valid number, exists, open, not full.
     * Prints the reason if it cannot.
     * @param token the deck number as typed.
     * @return the deck, or null if it cannot accept a vehicle.
     */
    private Deck findDeckForEntry(String token) {
        int number = toInt(token);
        if (number == INVALID) {
            System.out.println(token + " - invalid deck number; it contains characters.");
            return null;
        }
        Deck deck = decks.getDeck(new Deck(number));
        if (deck == null) {
            System.out.println("Deck#" + number + " - does not exist.");
        } else if (!deck.isOpen()) {
            System.out.println("Deck#" + number + " - is closed for parking.");
        } else if (deck.isFull()) {
            System.out.println("Deck#" + number + " - is full.");
        } else {
            return deck;
        }
        return null;
    }

     /**
     * Builds a timestamp from a date token and a time token.
     * Checks the date, then the hour, then the minute, and prints the reason
     * if one is invalid.
     * @param dateToken the date as typed, such as 2026-03-09.
     * @param timeToken the time as typed, such as 10:48.
     * @return the timestamp, or null if the date or time is invalid.
     */
    private Timestamp readTimestamp(String dateToken, String timeToken) {
        StringTokenizer d = new StringTokenizer(dateToken, "-");
        StringTokenizer t = new StringTokenizer(timeToken, ":");
        if (d.countTokens() != DATE_PARTS || t.countTokens() != TIME_PARTS) {
            System.out.println(dateToken + " - invalid calendar date.");
            return null;
        }
        int year = toInt(d.nextToken());
        int month = toInt(d.nextToken());
        int day = toInt(d.nextToken());
        Date date = new Date(year, month, day);
        if (year == INVALID || !date.isValid()) {
            System.out.println(dateToken + " - invalid calendar date.");
            return null;
        }
        String hourToken = t.nextToken();
        String minuteToken = t.nextToken();
        int hour = toInt(hourToken);
        int minute = toInt(minuteToken);
        if (hour == INVALID || hour > Timestamp.MAX_HOUR) {
            System.out.println(hourToken + " - invalid hour.");
            return null;
        }
        if (minute == INVALID || minute > Timestamp.MAX_MINUTE) {
            System.out.println(minuteToken + " - invalid minute.");
            return null;
        }
        return new Timestamp(date, hour, minute);
    }

    /**
     * Records vehicle entering a deck (E command).
     * @param tokens the data tokens: deck number, plate, date, time.
     */
    private void enterDeck(StringTokenizer tokens) {
           if (tokens.countTokens() < ENTER_TOKENS) {
            System.out.println("Missing data tokens.");
            return;
        }
        Deck deck = findDeckForEntry(tokens.nextToken());
        String plate = tokens.nextToken();
        String dateToken = tokens.nextToken();
        String timeToken = tokens.nextToken();
        if (deck == null) {
            return;
        }
        Vehicle probe = new Vehicle(plate);
        if (!probe.isValid()) {
            System.out.println(plate + " - invalid license plate format.");
            return;
        }
        Vehicle vehicle = vehicles.getVehicle(probe);
        if (vehicle == null) {
            System.out.println(plate + " - is not registered.");
            return;
        }
        if (decks.findParked(vehicle) != null) {
            System.out.println("Error entering - " + plate + " is already in a deck.");
            return;
        }
        Timestamp stamp = readTimestamp(dateToken, timeToken);
        if (stamp == null) {
            return;
        }
        if (!deck.getHour().isOpen(stamp.getHour(), stamp.getMinute())) {
            System.out.println("Error entering - not within the operating hours: "
                    + deck.getHour());
            return;
        }
        deck.enter(new Parking(vehicle, stamp));
        System.out.println(vehicle + " entered Deck#" + deck.getNumber()
                + " on " + stamp);
    }

    /**
     * Records vehicle exiting a deck (X command).
     * @param tokens the data tokens: plate, date, time.
     */
    private void exitDeck(StringTokenizer tokens) {
        if (tokens.countTokens() < EXIT_TOKENS) {
            System.out.println("Missing data tokens.");
            return;
        }
        String plate = tokens.nextToken();
        Vehicle probe = new Vehicle(plate);
        if (!probe.isValid()) {
            System.out.println(plate + " - invalid license plate format.");
            return;
        }
        Deck deck = decks.findParked(probe);
        String dateToken = tokens.nextToken();
        String timeToken = tokens.nextToken();
        if (deck == null) {
            System.out.println("Error exiting - " + plate + " is not in a deck.");
            return;
        }
        Timestamp stamp = readTimestamp(dateToken, timeToken);
        if (stamp == null) {
            return;
        }
        if (!deck.getHour().isOpen(stamp.getHour(), stamp.getMinute())) {
            System.out.println("Error exiting - not within the operating hours: "
                    + deck.getHour());
            return;
        }
        finishExit(deck, probe, stamp);
    }
    /**
     * Checks the exit time against the entry time, then completes the exit.
     * @param deck the deck the vehicle is in.
     * @param probe a vehicle with the plate that is exiting.
     * @param stamp the exit time.
     */
    private void finishExit(Deck deck, Vehicle probe, Timestamp stamp) {
        Parking parking = deck.getParking(probe);
        Timestamp entered = parking.getEnter();
        if (stamp.compareTo(entered) < 0) {
            System.out.println("Exiting time " + stamp + " before entering time "
                    + entered);
            return;
        }
        if (stamp.exceedsTwoDays(entered)) {
            System.out.println("Invalid exiting time - exceeds two days.");
            return;
        }
        parking.setExit(stamp);
        deck.exit(parking);
        parking.getVehicle().addHistory(parking);
        System.out.println(parking.getVehicle() + " exited Deck#"
                + deck.getNumber() + " on " + stamp);
    }

    /**
     * Prints the open decks or the vehicles in one deck (PD command).
     * @param tokens the data tokens: an optional deck number.
     */
    private void printDecks(StringTokenizer tokens) {
        if (!tokens.hasMoreTokens()) {
            decks.printByLocation();
            return;
        }
       String numberToken = tokens.nextToken();
        int number = toInt(numberToken);
        if (number == INVALID) {
            System.out.println(numberToken
                    + " - invalid deck number; it contains characters.");
            return;
        }
        Deck deck = decks.getDeck(new Deck(number));
        if (deck == null) {
            System.out.println("Deck#" + number + " - does not exist.");
        } else if (!deck.isOpen()) {
            System.out.println("Deck#" + number + " - is closed.");
        } else {
            decks.printVehicles(deck);
        }
    }

    /**
     * Prints parking history of all vehicles or one vehicle (PH command).
     * @param tokens the data tokens: an optional license plate.
     */
    private void printHistory(StringTokenizer tokens) {
         if (!tokens.hasMoreTokens()) {
            vehicles.printHistory();
            return;
        }
        String plate = tokens.nextToken();
        Vehicle probe = new Vehicle(plate);
        if (!probe.isValid()) {
            System.out.println(plate + " - invalid license plate format.");
            return;
        }
        Vehicle vehicle = vehicles.getVehicle(probe);
        if (vehicle == null) {
            System.out.println(plate + " does not exist.");
        } else if (!vehicle.hasHistory()) {
            System.out.println(vehicle + " - no parking history.");
        } else {
            System.out.println("** Parking history for " + vehicle + "**");
            vehicle.printHistory();
            System.out.println("** end of list **");
        }
    }

    /**
     * Reads and processes command lines until Q command is entered.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Parking Management System is in operation.");
        System.out.println();
        while (scanner.hasNextLine()) {
            StringTokenizer tokens = new StringTokenizer(scanner.nextLine());
            if (!tokens.hasMoreTokens()) {
                continue;
            }
            String command = tokens.nextToken();
            if (command.equals("Q")) {
                break;
            }
            processCommand(command, tokens);
        }
        System.out.println("Parking Management System is terminated.");
    }
}
