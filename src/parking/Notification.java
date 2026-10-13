package parking;

import util.List;

/**
 * Sends system notifications to every registered vehicle
 *
 * @author Alisha Sharma
 */
public class Notification implements Subject {
    private List<Vehicle> vehicles;

    /**
     * Creates a notification system with no subscribers
     */
    public Notification() {
        vehicles = new List<Vehicle>();
    }

    /**
     * Subscribes a vehicle, unless it is already subscribed
     *
     * @param vehicle the vehicle to subscribe
     */
    @Override
    public void register(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
        }
    }

    /**
     * Unsubscribes a vehicle
     *
     * @param vehicle the vehicle to unsubscribe
     */
    @Override
    public void unregister(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    /**
     * Sends a message to every subscribed vehicle
     *
     * @param message the notification message
     */
    @Override
    public void notify(String message) {
        for (Vehicle vehicle : vehicles) {
            vehicle.update(message);
        }
    }
}