package parking;

/**
 * An object that receives system notifications.
 *
 * @author Alisha Sharma
 */
public interface Observer {
    /**
     * Receives a notification message.
     *
     * @param message the notification message.
     */
    void update(String message);
}