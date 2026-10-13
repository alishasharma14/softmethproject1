package parking;

/**
 * a sedan. it pays $2 an hour or $18 for the day
 *
 * @author Alisha Sharma
 */
public class Sedan extends Vehicle {
    /**
     * The hourly rate for a sedan in dollars.
     */
    private static final int HOURLY_RATE = 2;
    /**
     * the daily rate for a sedan in dollars
     */
    private static final int DAILY_RATE = 18;

    /**
     * makes a sedan with the given plate
     *
     * @param plate the license plate.
     */
    public Sedan(String plate) {
        super(plate);
    }

    /**
     * Calculates the parking fee for a sedan.
     *
     * @param deck  the deck where the vehicle parked.
     * @param enter the time the vehicle entered.
     * @param exit  the time the vehicle exited.
     * @return the parking fee, in dollars.
     */
    @Override
    public int parkingFee(Deck deck, Timestamp enter, Timestamp exit) {
        return calculateFee(deck, enter, exit, HOURLY_RATE, DAILY_RATE);
    }
}