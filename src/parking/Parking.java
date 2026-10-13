package parking;

/**
 * A parking activity: a vehicle, when it entered, and when it exited.
 *
 * @author Ann Sajan
 */
public class Parking {
    /**
     * The vehicle that parked.
     */
    private Vehicle vehicle;
    /**
     * The time the vehicle entered the deck.
     */
    private Timestamp enter;
    /**
     * The time the vehicle exited the deck, or null if still parked.
     */
    private Timestamp exit;
    /**
     * The number of the deck the vehicle parked in.
     */
    private int deckNum;

    

    /**
     * Creates a parking activity for a vehicle that has just entered a deck.
     * The exit time is not known yet.
     *
     * @param vehicle the vehicle that entered
     * @param enter   the time the vehicle entered
     * @param deckNum the number of the deck the vehicle entered
     */
    public Parking(Vehicle vehicle, Timestamp enter, int deckNum) {
        this.vehicle = vehicle;
        this.enter = enter;
        this.exit = null;
        this.deckNum = deckNum;
    }

    /**
     * Returns the vehicle of this parking activity.
     *
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Returns the time the vehicle entered the deck.
     *
     * @return the entry timestamp
     */
    public Timestamp getEnter() {
        return enter;
    }

    /**
     * Returns the time the vehicle exited the deck.
     *
     * @return the exit timestamp, or null if the vehicle has not exited
     */
    public Timestamp getExit() {
        return exit;
    }

    /**
     * Records the time the vehicle exited the deck.
     *
     * @param exit the exit timestamp
     */
    public void setExit(Timestamp exit) {
        this.exit = exit;
    }
     /**
     * Returns the parking activity as a line of parking history, such as
     * [entered Deck# 110: 2026-03-09 10:48][exited: 2026-03-09 11:50].
     *
     * @return the history text of the parking activity
     */
    public String historyText() {
          return "     [entered Deck# " + deckNum + ": " + enter
                + "][exited: " + exit + "]";
    }

    /**
     * Returns the parking activity as text, such as
     * R48-JIK [entered: 2026-03-09 10:48][exited: 2026-03-09 11:50].
     *
     * @return the textual representation of the parking activity
     */
    @Override
    public String toString() {
        return vehicle + " [entered: " + enter + "][exited: " + exit + "]";
    }
}
