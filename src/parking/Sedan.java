package parking;

/**
 * A sedan. charged the sedan hourly and daily rates.
 * @author Alisha Sharma
 */
public class Sedan extends Vehicle {

    /**
     * Creates a sedan with the given license plate.
     * @param plate the license plate.
     */
    public Sedan(String plate) {
        super(plate);
    }

    /**
     * Calculates the sedan parking fee.
     * @param deck the deck where the vehicle parked.
     * @param enter the time the vehicle entered.
     * @param exit the time the vehicle exited.
     * @return the parking fee, in dollars.
     */
    @Override
    public int parkingFee(Deck deck, Timestamp enter, Timestamp exit) {
        return 0; // TODO: fee calculation
    }
}