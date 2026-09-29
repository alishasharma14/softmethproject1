package parking;

/**
 * A resizable array holding the list of registered vehicles.
 * The initial capacity is 4 and it grows by 4 whenever the list is full.
 *
 * @author Alisha Sharma
 */
public class VehicleList {
    /** The initial capacity of the list, and the amount it grows by. */
    private static final int LENGTH = 4;
    /** The index returned when a vehicle is not found. */
    private static final int NOTFOUND = -1;
    /** The array holding the registered vehicles. */
    private Vehicle[] vehicles;
    /** The number of vehicles in the list. */
    private int numVehicles;

    /**
     * Creates an empty vehicle list with the initial capacity.
     */
    public VehicleList() {
        vehicles = new Vehicle[LENGTH];
        numVehicles = 0;
    }

    /**
     * Finds a vehicle in the list.
     *
     * @param vehicle the vehicle to search for.
     * @return the index of the vehicle, or NOTFOUND if not in the list.
     */
    private int find(Vehicle vehicle) {
        for (int i = 0; i < numVehicles; i++) {
            if (vehicles[i].equals(vehicle)) {
                return i;
            }
        }
        return NOTFOUND;
    }

    /**
     * Increases the capacity of the list by 4.
     */
    private void grow() {
        Vehicle[] bigger = new Vehicle[vehicles.length + LENGTH];
        for (int i = 0; i < numVehicles; i++) {
            bigger[i] = vehicles[i];
        }
        vehicles = bigger;
    }

    /**
     * Sorts the vehicles by license plate with insertion sort.
     */
    private void sortByPlate() {
        for (int i = 1; i < numVehicles; i++) {
            Vehicle current = vehicles[i];
            int j = i - 1;
            while (j >= 0
                    && vehicles[j].getPlate().compareTo(current.getPlate()) > 0) {
                vehicles[j + 1] = vehicles[j];
                j--;
            }
            vehicles[j + 1] = current;
        }
    }

    /**
     * Adds a vehicle to the end of the list, growing the list if full.
     *
     * @param vehicle the vehicle to add.
     */
    public void add(Vehicle vehicle) {
        if (numVehicles == vehicles.length) {
            grow();
        }
        vehicles[numVehicles] = vehicle;
        numVehicles++;
    }

    /**
     * Removes a vehicle by replacing it with the last vehicle in the list.
     * It does nothing if the vehicle is not in the list.
     *
     * @param vehicle the vehicle to remove.
     */
    public void remove(Vehicle vehicle) {
        int index = find(vehicle);
        if (index == NOTFOUND) {
            return;
        }
        vehicles[index] = vehicles[numVehicles - 1];
        vehicles[numVehicles - 1] = null;
        numVehicles--;
    }

    /**
     * Checks if a vehicle is in the list.
     *
     * @param vehicle the vehicle to search for.
     * @return true if the vehicle is in the list; false otherwise.
     */
    public boolean contains(Vehicle vehicle) {
        return find(vehicle) != NOTFOUND;
    }

    /**
     * Returns the registered vehicle with the same plate as the given one.
     * Used to get the stored vehicle, which holds the parking history.
     *
     * @param vehicle a vehicle with the plate to search for.
     * @return the registered vehicle, or null if it is not in the list.
     */
    public Vehicle getVehicle(Vehicle vehicle) {
        int index = find(vehicle);
        if (index == NOTFOUND) {
            return null;
        }
        return vehicles[index];
    }

    /**
     * Prints the list of vehicles ordered by plate.
     */
    public void printByPlate() {
        if (numVehicles == 0) {
            System.out.println("Vehicle list is empty - no vehicle is registered.");
            return;
        }
        sortByPlate();
        System.out.println("** List of registered vehicles, ordered by license plate **");
        for (int i = 0; i < numVehicles; i++) {
            System.out.println(vehicles[i]);
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the parking history of all vehicles ordered by plate then by entry time.
     */
    public void printHistory() {
       if (numVehicles == 0) {
            System.out.println("Deck list is empty - no deck is open.");
            return;
        }
        sortByPlate();
        System.out.println("** Parking history for all vehicles, ordered by plate/timestamp **");
        for (int i = 0; i < numVehicles; i++) {
            vehicles[i].printHistory();
        }
        System.out.println("** end of parking history **");
    }
}
