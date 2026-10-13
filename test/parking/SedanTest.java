package parking;

import org.junit.jupiter.api.Test;
import util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the parkingFee() method of the Sedan class.
 * @author Ann Sajan
 */
class SedanTest {
    /** A sedan parked for 3 hours pays the hourly rate of $2: 3 x $2 = $6. */
    @Test
    void hourlyRate() {
        Deck deck = new Deck(1, Location.EDISON, Hour.HR5, 4);
        Vehicle sedan = new Sedan("R48-JIK");
        Timestamp enter = new Timestamp(new Date(2026, 10, 12), 9, 0);
        Timestamp exit = new Timestamp(new Date(2026, 10, 12), 12, 0);
        assertEquals(6, sedan.parkingFee(deck, enter, exit));
    }

    /** A sedan parked for 8 hours exceeds 6 hours, so it pays the daily rate of $18. */
    @Test
    void dailyRate() {
        Deck deck = new Deck(1, Location.EDISON, Hour.HR5, 4);
        Vehicle sedan = new Sedan("R48-JIK");
        Timestamp enter = new Timestamp(new Date(2026, 10, 12), 9, 0);
        Timestamp exit = new Timestamp(new Date(2026, 10, 12), 17, 0);
        assertEquals(18, sedan.parkingFee(deck, enter, exit));
    }
}
