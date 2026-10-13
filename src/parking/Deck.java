package parking;

import util.List;

/**
 * a parking deck, it has a location and hours and the cars parked in it
 *
 * @author Alisha Sharma, Ann Sajan
 */
public class Deck {
    /**
     * what find returns when the vehicle isnt there
     */
    private static final int NOTFOUND = -1;
    /**
     * The maximum number of vehicles any deck can hold.
     */
    public static final int MAXCAPACITY = 6;

    /**
     * The deck number.
     */
    private int number;
    /**
     * the city the deck is in
     */
    private Location location;
    /**
     * The operating hours of the deck.
     */
    private Hour hour;
    /**
     * The parking activities of the vehicles currently in the deck.
     */
    private List<Parking> parkings;
    /**
     * max number of cars that can fit
     */
    private int capacity;
    /**
     * The total parking fees this deck has collected, in dollars.
     */
    private int feesCollected;
    /**
     * True if the deck is open, false if it is closed.
     */
    private boolean open;

    /**
     * Creates a deck with only a deck number, used for searching the list.
     *
     * @param number the deck number.
     */
    public Deck(int number) {
        this.number = number;
    }

    /**
     * makes an open deck with no cars in it yet and no fees
     *
     * @param number   the deck number.
     * @param location the city where the deck is located.
     * @param hour     the operating hours.
     * @param capacity the maximum number of vehicles.
     */
    public Deck(int number, Location location, Hour hour, int capacity) {
        this.number = number;
        this.location = location;
        this.hour = hour;
        this.capacity = capacity;
        this.parkings = new List<Parking>();
        this.feesCollected = 0;
        this.open = true;
    }

    /**
     * Finds the parking activity of a vehicle in this deck.
     *
     * @param vehicle the vehicle to search for.
     * @return the index of the parking activity or NOTFOUND if not parked.
     */
    private int find(Vehicle vehicle) {
        for (int i = 0; i < parkings.size(); i++) {
            if (parkings.get(i).getVehicle().equals(vehicle)) {
                return i;
            }
        }
        return NOTFOUND;
    }

    /**
     * adds a parking activity when a car comes in
     *
     * @param parking the parking activity to add.
     */
    public void enter(Parking parking) {
        parkings.add(parking);
    }

    /**
     * Removes a parking activity when a vehicle exits the deck.
     * It does nothing if the vehicle isnt parked here
     *
     * @param parking the parking activity to remove.
     */
    public void exit(Parking parking) {
        int index = find(parking.getVehicle());
        if (index == NOTFOUND) {
            return;
        }
        parkings.remove(parkings.get(index));
    }

    /**
     * Checks if a vehicle is parked in this deck.
     *
     * @param vehicle the vehicle to search for.
     * @return true if the vehicle is parked here, otherwise false.
     */
    public boolean contains(Vehicle vehicle) {
        return find(vehicle) != NOTFOUND;
    }

    /**
     * Returns the parking activity of a vehicle in this deck.
     *
     * @param vehicle the vehicle to search for.
     * @return the parking activity, or null if the vehicle is not parked here.
     */
    public Parking getParking(Vehicle vehicle) {
        int index = find(vehicle);
        if (index == NOTFOUND) {
            return null;
        }
        return parkings.get(index);
    }

    /**
     * gives back the list of parking activities so DeckList can print them
     *
     * @return the parking activities of the vehicles in the deck.
     */
    public List<Parking> getParkings() {
        return parkings;
    }

    /**
     * Adds a parking fee to the total fees collected by this deck.
     *
     * @param fee the fee to add, in dollars.
     */
    public void addFee(int fee) {
        feesCollected += fee;
    }

    /**
     * Returns the total fees collected by this deck.
     *
     * @return the total fees, in dollars.
     */
    public int getFeesCollected() {
        return feesCollected;
    }

    /**
     * Returns the deck number.
     *
     * @return the deck number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the location of the deck.
     *
     * @return the location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the operating hours of the deck.
     *
     * @return the operating hours.
     */
    public Hour getHour() {
        return hour;
    }

    /**
     * checks if the deck is open
     *
     * @return true if the deck is open, false if closed.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Marks the deck as open or closed.
     *
     * @param open true to open the deck, false to close.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Checks if the deck has reached its max capacity.
     *
     * @return true if the deck is full, otherwise false.
     */
    public boolean isFull() {
        return parkings.size() == capacity;
    }

    /**
     * checks if theres any car parked in the deck right now
     *
     * @return true if at least one vehicle is parked, otherwise false.
     */
    public boolean hasVehicles() {
        return !parkings.isEmpty();
    }

    /**
     * Checks if two decks have the same deck number.
     *
     * @param obj the object to compare with.
     * @return true if obj is a deck with the same number, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deck) {
            Deck other = (Deck) obj;
            return number == other.number;
        }
        return false;
    }

    /**
     * Returns the deck information
     *
     * @return the text form of the deck.
     */
    @Override
    public String toString() {
        return "Deck#" + number + "@" + location.name()
                + "[open " + hour + "] [capacity " + capacity + "] ["
                + parkings.size() + " vehicles] [" + location.getCounty() + "]";
    }
}