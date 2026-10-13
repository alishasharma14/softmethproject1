package parking;

/**
 * An object that sends notifications to the observers registered with it
 *
 * @author Alisha Sharma
 */
public interface Subject {
    /**
     * Subscribes a vehicle to notifications
     *
     * @param vehicle the vehicle to subscribe
     */
    void register(Vehicle vehicle);

    /**
     * Unsubscribes a vehicle from notifications
     *
     * @param vehicle the vehicle to unsubscribe
     */
    void unregister(Vehicle vehicle);

    /**
     * Sends a message to every subscribed vehicle
     *
     * @param message the notification message
     */
    void notify(String message);
}