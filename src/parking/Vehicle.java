package parking;

import util.List;

/**
 * a vehicle thats registered for parking, we identify it by its license plate.
 * Each vehicle keeps a list of its parking history
 * @author Alisha Sharma, Ann Sajan
 */
public abstract class Vehicle implements Observer {
    /** The number of characters in a valid license plate. */
    private static final int PLATE_LENGTH = 7;
    /** The index of the dash in a license plate. */
    private static final int DASH_INDEX = 3;
    /** the dash between the numbers and the letters at the end */
    private static final char DASH = '-';

    /** The license plate, exactly 7 characters. */
    private String plate;
    /** The completed parking activities, oldest first. */
    private List<History> history;

    /**the nofications this vehicle received */
    private List<String>notifications;
    /**
     * Creates a vehicle with the given license plate and no parking history.
     * The plate is stored in uppercase so "r48-jik" and "R48-JIK" match.
     * @param plate the license plate.
     */
    public Vehicle(String plate) {
        this.plate = plate.toUpperCase();
        this.history = new List<History>();
        this.notifications = new List<String>();
    }

    /**
     * Checks if the license plate is in the format "Xdd-XXX", where each X
     * is a letter and each d is a digit.
     * @return true if the plate is valid, otherwise false.
     */
    public boolean isValid() {
        if (plate.length() != PLATE_LENGTH) {
            return false;
        }
        if (!Character.isLetter(plate.charAt(0))) {
            return false;
        }
        for (int i = 1; i < DASH_INDEX; i++) {
            if (!Character.isDigit(plate.charAt(i))) {
                return false;
            }
        }
        if (plate.charAt(DASH_INDEX) != DASH) {
            return false;
        }
        for (int i = DASH_INDEX + 1; i < PLATE_LENGTH; i++) {
            if (!Character.isLetter(plate.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the license plate of this vehicle.
     * @return the license plate.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * returns the parking history so VehicleList can print it
     * @return the parking history, oldest first.
     */
    public List<History> getHistory() {
        return history;
    }

    /**
     * Checks if this vehicle has any parking history.
     * @return true if the vehicle has parking history, otherwise false.
     */
    public boolean hasHistory() {
        return !history.isEmpty();
    }

    /**
     * adds a finished parking activity to the end of the history, so the
     * oldest one is always first and the newest is last
     * @param parking the completed parking activity
     */
    public void addHistory(Parking parking) {
        history.add(new History(parking));
    }

    /**
     * Calculates the parking fee based on the vehicle type and duration.
     * @param deck the deck where the vehicle parked.
     * @param enter the time the vehicle entered.
     * @param exit the time the vehicle exited.
     * @return the parking fee in dollars.
     */
    public abstract int parkingFee(Deck deck, Timestamp enter, Timestamp exit);

    /**
     * Receives a notification and saves it
     * @param message the notification message
     */
    @Override
    public void update(String message) {
        notifications.add(message);
    }

    /**
     * Returns the notifications this vehicle has received
     * @return the list of notifications
     */
    public List<String> getNotifications() {
        return notifications;
    }

    /**
     * Checks if two vehicles have the same license plate.
     * @param obj the object to compare with.
     * @return true if obj is a vehicle with the same plate, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle) {
            Vehicle other = (Vehicle) obj;
            return plate.equals(other.plate);
        }
        return false;
    }

    /**
     * Returns the license plate as the text form of this vehicle.
     * @return the license plate.
     */
    @Override
    public String toString() {
        return plate;
    }
}