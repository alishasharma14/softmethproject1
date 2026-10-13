package parking;

import util.Date;

import java.util.Calendar;

/**
 * A date and time.
 *
 * @author Ann Sajan
 */
public class Timestamp implements Comparable<Timestamp> {
    /**
     * The date of this timestamp.
     */
    private Date date;
    /**
     * The hour in 24-hour format.
     */
    private byte hour;
    /**
     * The minute of the hour.
     */
    private byte minute;
    /**
     * The largest valid hour in 24-hour format.
     */
    public static final int MAX_HOUR = 23;
    /**
     * The largest valid minute.
     */
    public static final int MAX_MINUTE = 59;
    /**
     * The number of days a vehicle may stay in a deck.
     */
    private static final int MAX_STAY_DAYS = 2;

    /**
     * Creates a timestamp from a date, an hour, and a minute.
     *
     * @param date   the date
     * @param hour   the hour, 0 to 23
     * @param minute the minute, 0 to 59
     */
    public Timestamp(Date date, int hour, int minute) {
        this.date = date;
        this.hour = (byte) hour;
        this.minute = (byte) minute;
    }

    /**
     * Returns the date of this timestamp.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the hour of this timestamp.
     *
     * @return the hour, 0 to 23
     */
    public int getHour() {
        return hour;
    }

    /**
     * Returns the minute of this timestamp.
     *
     * @return the minute, 0 to 59
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Checks if this timestamp is more than two days after another.
     *
     * @param entered the earlier timestamp
     * @return true if more than two days later, false otherwise
     */
    public boolean exceedsTwoDays(Timestamp entered) {
        Calendar limit = Calendar.getInstance();
        limit.clear();
        limit.set(entered.date.getYear(), entered.date.getMonth() - Date.JAN,
                entered.date.getDay());
        limit.add(Calendar.DAY_OF_MONTH, MAX_STAY_DAYS);
        Date limitDate = new Date(limit.get(Calendar.YEAR),
                limit.get(Calendar.MONTH) + Date.JAN,
                limit.get(Calendar.DAY_OF_MONTH));
        Timestamp limitStamp = new Timestamp(limitDate, entered.hour, entered.minute);
        return compareTo(limitStamp) > 0;
    }

    /**
     * Compares this timestamp to another, by date, then hour, then minute.
     *
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
     *
     * @return the textual representation of the timestamp
     */
    @Override
    public String toString() {
        return date + String.format(" %02d:%02d", hour, minute);
    }

}
