package parking;

/**
 * A vehicle registered for parking, identified by its license plate.
 * Each vehicle keeps a singly linked list of its parking history.
 *
 * @author Alisha Sharma
 */
public class Vehicle {
    private static final int PLATE_LENGTH = 7;
    private static final int DASH_INDEX = 3;
    private static final char DASH = '-';

    private String plate;     //exactly 7 characters
    private History history;  //a singly linked list

    /**
     * Creates a vehicle with the given license plate and no parking history.
     * The plate is stored in uppercase so "r48-jik" and "R48-JIK" match.
     *
     * @param plate the license plate.
     */
    public Vehicle(String plate) {
        this.plate = plate.toUpperCase();
        this.history = null;
    }

    /**
     * Checks if the license plate is in the format "Xdd-XXX", where each X
     * is a letter and each d is a digit.
     *
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
     *
     * @return the license plate.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Checks if this vehicle has any parking history.
     *
     * @return true if the vehicle has parking history, otherwise false.
     */
    public boolean hasHistory() {
        return history != null;
    }

    /**
     * Prints the parking history of this vehicle.
     */
    public void printHistory() {
        // TODO ann
    }

    /**
     * Checks if two vehicles have the same license plate.
     *
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
     *
     * @return the license plate.
     */
    @Override
    public String toString() {
        return plate;
    }
}