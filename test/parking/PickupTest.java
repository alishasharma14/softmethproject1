package parking;

import org.junit.jupiter.api.Test;
import util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the parkingFee() method of the Pickup class.
 * @author Ann Sajan
 */
class PickupTest {
    /** A pickup parked for 3 hours pays the hourly rate of $4: 3 x $4 = $12. */
    @Test
    void hourlyRate() {
        Deck deck = new Deck(1, Location.EDISON, Hour.HR5, 4);
        Vehicle pickup = new Pickup("P48-JIK");
        Timestamp enter = new Timestamp(new Date(2026, 10, 12), 9, 0);
        Timestamp exit = new Timestamp(new Date(2026, 10, 12), 12, 0);
        assertEquals(12, pickup.parkingFee(deck, enter, exit));
    }

    /** A pickup parked for 8 hours exceeds 6 hours, so it pays the daily rate of $30. */
    @Test
    void dailyRate() {
        Deck deck = new Deck(1, Location.EDISON, Hour.HR5, 4);
        Vehicle pickup = new Pickup("P48-JIK");
        Timestamp enter = new Timestamp(new Date(2026, 10, 12), 9, 0);
        Timestamp exit = new Timestamp(new Date(2026, 10, 12), 17, 0);
        assertEquals(30, pickup.parkingFee(deck, enter, exit));
    }
}
