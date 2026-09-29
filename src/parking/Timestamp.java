package parking;

/**
 * A date and time.
 * @author Ann Sajan
 */
public class Timestamp implements Comparable<Timestamp> {
    /** The date of this timestamp. */
    private Date date;
    /** The hour in 24-hour format. */
    private byte hour;
    /** The minute of the hour. */
    private byte minute;
    /** The largest valid hour in 24-hour format. */
    public static final int MAX_HOUR = 23;
    /** The largest valid minute. */
    public static final int MAX_MINUTE = 59;

/**
 * Creates a timestamp from a date, an hour, and a minute.
 * @param date the date
 * @param hour the hour, 0 to 23
 * @param minute the minute, 0 to 59
 */
    public Timestamp(Date date, int hour, int minute) {
        this.date = date;
        this.hour = (byte) hour;
        this.minute = (byte) minute;
    }
/**
 * Returns the date of this timestamp.
 * @return the date
 */
    public Date getDate(){
        return date;
    }
/**
 * Returns the hour of this timestamp.
 * @return the hour, 0 to 23
 */
    public int getHour(){
        return hour;
    }
/**
 * Returns the minute of this timestamp.
 * @return the minute, 0 to 59
 */
    public int getMinute(){
        return minute;
    }
/**
 * Compares this timestamp to another, by date, then hour, then minute.
 * @param other the timestamp to compare to
 * @return -1 if this timestamp is earlier, 1 if later, 0 if the same
 */

    @Override
    public int compareTo(Timestamp other) {
        int dateComparison = date.compareTo(other.date);
        if (dateComparison < 0) {
            return -1;
        }
        if (dateComparison > 0) {
            return 1;
        }
        if (hour < other.hour) {
            return -1;
        }
        if (hour > other.hour) {
            return 1;
        }
        if (minute < other.minute) {
            return -1;
        }
        if (minute > other.minute) {
            return 1;
        }
        return 0; 
    }
  /**
   * Returns the timestamp as text, such as 2026-03-09 10:48.
   * @return the textual representation of the timestamp
   */
    @Override
    public String toString() {
        return date + String.format(" %02d:%02d", hour, minute);
    }
  /**
   * Testbed main to unit-test compareTo().
   * @param args not used
   */
    public static void main(String[] args) {
        Timestamp base = new Timestamp(new Date(2026, 10, 9), 10, 48);

        Timestamp laterYear = new Timestamp(new Date(2027, 1, 1), 8, 0);
        System.out.println("Test 1: later year, expect -1, got " + base.compareTo(laterYear));

        Timestamp laterHour = new Timestamp(new Date(2026, 10, 9), 11, 0);
        System.out.println("Test 2: later hour, expect -1, got " + base.compareTo(laterHour));

        Timestamp laterMinute = new Timestamp(new Date(2026, 10, 9), 10, 49);
        System.out.println("Test 3: later minute, expect -1, got " + base.compareTo(laterMinute));

        Timestamp earlierMonth = new Timestamp(new Date(2026, 3, 9), 23, 59);
        System.out.println("Test 4: earlier month, expect 1, got " + base.compareTo(earlierMonth));

        Timestamp earlierHour = new Timestamp(new Date(2026, 10, 9), 9, 59);
        System.out.println("Test 5: earlier hour, expect 1, got " + base.compareTo(earlierHour));

        Timestamp earlierMinute = new Timestamp(new Date(2026, 10, 9), 10, 47);
        System.out.println("Test 6: earlier minute, expect 1, got " + base.compareTo(earlierMinute));

        Timestamp same = new Timestamp(new Date(2026, 10, 9), 10, 48);
        System.out.println("Test 7: same time, expect 0, got " + base.compareTo(same));
    }
}
