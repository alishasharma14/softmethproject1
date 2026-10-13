package parking;

import util.List;
import util.Sort;

/**
 * The list of registered vehicles built on the generic List class.
 *
 * @author Alisha Sharma, Ann Sajan
 */
public class VehicleList extends List<Vehicle> {
    /** What indexOf returns when the vehicle is not in the list. */
    private static final int NOT_FOUND = -1;

    /**
     * Returns the registered vehicle with the same plate as the given one.
     * Use this to get the stored vehicle which holds the parking history.
     *
     * @param vehicle a vehicle with the plate to search for.
     * @return the registered vehicle or null if not in the list.
     */
    public Vehicle getVehicle(Vehicle vehicle) {
        int index = indexOf(vehicle);
        if (index == NOT_FOUND) {
            return null;
        }
        return get(index);
    }

    /**
     * Prints the list of vehicles by license plate.
     */
    public void printByPlate() {
        if (isEmpty()) {
            System.out.println("Vehicle list is empty - no vehicle is registered.");
            return;
        }
        Sort.vehiclesByPlate(this);
        System.out.println("** List of registered vehicles, ordered by license plate **");
        for (Vehicle vehicle : this) {
            System.out.println(vehicle);
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the parking history of all vehicles ordered by plate then by entry time.
     */
    public void printHistory() {
        if (size() == 0) {
            System.out.println("Deck list is empty - no deck is open.");
            return;
        }
        Sort.vehiclesByPlate(this);
        System.out.println("** Parking history for all vehicles, ordered by plate/timestamp **");
       for (Vehicle vehicle : this) {
        if (vehicle.hasHistory()) {
            System.out.println(vehicle);
            printHistory(vehicle);
        }
       }
        System.out.println("** end of parking history **");
    }

    /**
     * Prints the parking history of one vehicle, oldest first.
     *
     * @param vehicle the vehicle whose history is printed.
     */
    public void printHistory(Vehicle vehicle) {
        for (History record : vehicle.getHistory()) {
            System.out.println(record.getParking().historyText());
        }
    }
    
/**
 * Prints the notifications of all vehicles, ordered by license plate.
 */
    public void printNotifications() {
        if (isEmpty()) {
        System.out.println("Vehicle list is empty - no vehicle is registered.");
        return;
    }
    Sort.vehiclesByPlate(this);
    System.out.println("** Notifications, ordered by plate **");
    for (Vehicle vehicle : this) {
        System.out.println(vehicle);
        printNotifications(vehicle);
    }
    System.out.println("** end of list **");
}
    
/**
 * Prints the notifications of one vehicle, if it has any.
 *
 * @param vehicle the vehicle whose notifications are printed.
 */
public void printNotifications(Vehicle vehicle) {
    if (vehicle.getNotifications().isEmpty()) {
        return;
    }
    System.out.println("    ** notifications ** ");
    for (String message : vehicle.getNotifications()) {
        System.out.println("    " + message);
    }
}
}
