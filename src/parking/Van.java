package parking;

/**
 * A van. it pays $3 an hour or $25 for the day.
 * @author Alisha Sharma
 */
public class Van extends Vehicle {
    /** the hourly rate for a van in dollars */
    private static final int HOURLY_RATE = 3;
    /** The daily rate for a van in dollars. */
    private static final int DAILY_RATE = 25;

    /**
     * Creates a van with the given license plate.
     * @param plate the license plate.
     */
    public Van(String plate) {
        super(plate);
    }

    /**
     * calculates the parking fee for a van
     * @param deck the deck where the vehicle parked.
     * @param enter the time the vehicle entered.
     * @param exit the time the vehicle exited.
     * @return the parking fee in dollars.
     */
    @Override
    public int parkingFee(Deck deck, Timestamp enter, Timestamp exit) {
        return calculateFee(deck, enter, exit, HOURLY_RATE, DAILY_RATE);
    }
}