package parking;

/**
 * a pickup truck. pays $4 an hour or $30 for the day
 * @author Alisha Sharma
 */
public class Pickup extends Vehicle {
    /** The hourly rate for a pickup in dollars. */
    private static final int HOURLY_RATE = 4;
    /** The daily rate for a pickup in dollars. */
    private static final int DAILY_RATE = 30;

    /**
     * Creates a pickup with the given license plate.
     * @param plate the license plate.
     */
    public Pickup(String plate) {
        super(plate);
    }

    /**
     * Calculates the parking fee for a pickup truck.
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