package parking;

import org.junit.jupiter.api.Test;
import util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the parkingFee() method of the Van class.
 * @author Ann Sajan
 */
class VanTest {
    /** A van parked for 3 hours pays the hourly rate of $3: 3 x $3 = $9. */
    @Test
    void hourlyRate() {
        Deck deck = new Deck(1, Location.EDISON, Hour.HR5, 4);
        Vehicle van = new Van("V48-JIK");
        Timestamp enter = new Timestamp(new Date(2026, 10, 12), 9, 0);
        Timestamp exit = new Timestamp(new Date(2026, 10, 12), 12, 0);
        assertEquals(9, van.parkingFee(deck, enter, exit));
    }

    /** A van parked for 8 hours exceeds 6 hours, so it pays the daily rate of $25. */
    @Test
    void dailyRate() {
        Deck deck = new Deck(1, Location.EDISON, Hour.HR5, 4);
        Vehicle van = new Van("V48-JIK");
        Timestamp enter = new Timestamp(new Date(2026, 10, 12), 9, 0);
        Timestamp exit = new Timestamp(new Date(2026, 10, 12), 17, 0);
        assertEquals(25, van.parkingFee(deck, enter, exit));
    }
}
