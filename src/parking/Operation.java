package parking;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The user interface class that reads and processes command lines
 * from the terminal until there is a Q command is entered.
 * @author Alisha Sharma, PARTNER NAME
 */
public class Operation {
    private static final int DECK_DATA_TOKENS = 3;

    private VehicleList vehicles;
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
     * Records vehicle entering a deck (E command).
     * @param tokens the data tokens: deck number, plate, date, time.
     */
    private void enterDeck(StringTokenizer tokens) {
        // TODO (PARTNER)
    }

    /**
     * Records vehicle exiting a deck (X command).
     * @param tokens the data tokens: plate, date, time.
     */
    private void exitDeck(StringTokenizer tokens) {
        // TODO Ann
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
        // TODO Ann: PD with deck number
    }

    /**
     * Prints parking history of all vehicles or one vehicle (PH command).
     * @param tokens the data tokens: an optional license plate.
     */
    private void printHistory(StringTokenizer tokens) {
        // TODO Ann
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