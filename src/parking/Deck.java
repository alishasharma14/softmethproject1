package parking;

/**
 * A parking deck with its location, operating hours, and parked vehicles.
 * @author Alisha Sharma, Ann Sajan
 */
public class Deck {
    public static final int MAXCAPACITY = 6;
    private static final int NOTFOUND = -1;

    private int number;          //deck number
    private Location location;   //Location is an enum class
    private Hour hour;           //Hour is an enum class
    private Parking[] parkings;  //list of parking activities
    private int numParked;       //number of parking activities in array
    private boolean open;

    /**
     * Creates a deck with only a deck number, used for searching the list.
     * @param number the deck number.
     */
    public Deck(int number) {
        this.number = number;
    }

    /**
     * Creates an open deck with no vehicles parked.
     * @param number the deck number.
     * @param location the city where the deck is located.
     * @param hour the operating hours.
     * @param capacity the maximum number of vehicles.
     */
    public Deck(int number, Location location, Hour hour, int capacity) {
        this.number = number;
        this.location = location;
        this.hour = hour;
        this.parkings = new Parking[capacity];
        this.numParked = 0;
        this.open = true;
    }

    /**
     * Finds the parking activity of a vehicle in this deck.
     * @param vehicle the vehicle to search for.
     * @return the index of the parking activity or NOTFOUND if not parked.
     */
    private int find(Vehicle vehicle) {
        for(int i = 0; i< numParked; i++){
          if (parkings[i].getVehicle().equals(vehicle)){
              return i;
          }
        }
        return NOTFOUND; 
    }
    /**
     * Sorts the parking activities by license plate with selection sort.
     */
    private void sortByPlate() {
        for (int i = 0; i < numParked - 1; i++) {
            int min = i;
            for (int j = i + 1; j < numParked; j++) {
                String plateJ = parkings[j].getVehicle().getPlate();
                String plateMin = parkings[min].getVehicle().getPlate();
                if (plateJ.compareTo(plateMin) < 0) {
                    min = j;
                }
            }
            Parking temp = parkings[i];
            parkings[i] = parkings[min];
            parkings[min] = temp;
        }
    }

    /**
     * Adds a parking activity when a vehicle enters the deck.
     * @param parking the parking activity to add.
     */
    public void enter(Parking parking) {
        parkings[numParked] = parking;
        numParked++;
    }

    /**
     * Removes a parking activity when a vehicle exits the deck.
     * @param parking the parking activity to remove.
     */
    public void exit(Parking parking) {
         int index = find(parking.getVehicle());
        if (index == NOTFOUND) {
            return;
        }
        numParked--;
        parkings[index] = parkings[numParked];
        parkings[numParked] = null;
    }

    /**
     * Checks if a vehicle is parked in this deck.
     * @param vehicle the vehicle to search for.
     * @return true if the vehicle is parked here, otherwise false.
     */
    public boolean contains(Vehicle vehicle) {
        return find(vehicle) != NOTFOUND;
    }
    /**
     * Returns the parking activity of a vehicle in this deck.
     * @param vehicle the vehicle to search for.
     * @return the parking activity, or null if the vehicle is not parked here.
     */
    public Parking getParking(Vehicle vehicle) {
    int index = find(vehicle);
    if (index == NOTFOUND) {
        return null;
    }
    return parkings[index];
}
    /**
     * Prints the vehicles parked in this deck, ordered by license plate.
     */
    public void printByPlate() {
        sortByPlate();
        System.out.println("** List of vehicles in Deck# " + number
                + ", ordered by plate **");
        for (int i = 0; i < numParked; i++) {
            System.out.println(parkings[i]);
        }
        System.out.println("** end of list **");
    }


    /**
     * Returns the deck number.
     * @return the deck number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the location of the deck.
     * @return the location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the operating hours of the deck.
     * @return the operating hours.
     */
    public Hour getHour() {
        return hour;
    }

    /**
     * Checks if the deck is open.
     * @return true if the deck is open, false if closed.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Marks the deck as open or closed.
     * @param open true to open the deck, false to close.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Checks if the deck has reached its max capacity.
     * @return true if the deck is full, otherwise false.
     */
    public boolean isFull() {
        return numParked == parkings.length;
    }

    /**
     * Checks if any vehicle is parked in the deck.
     * @return true if at least one vehicle is parked, otherwise false.
     */
    public boolean hasVehicles() {
        return numParked > 0;
    }

    /**
     * Checks if two decks have the same deck number.
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
     * @return the text form of the deck.
     */
    @Override
    public String toString() {
        String vehicleWord = (numParked == 1) ? " vehicle" : " vehicles";
        return "Deck#" + number + "@" + location.name()
                + "[open " + hour + "] [capacity " + parkings.length + "] ["
                + numParked + vehicleWord + "] [" + location.getCounty() + "]";
    }
}
