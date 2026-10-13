package parking;

/**
 * The operating hours of the parking decks, in 24-hour format.
 *
 * @author Alisha Sharma
 */
public enum Hour {
    HR5(5, 0, 21, 30),
    HR6(6, 30, 18, 30),
    HR7(7, 0, 18, 0);

    private static final int MINUTES_PER_HOUR = 60;

    private final int startHour;
    private final int startMinute;
    private final int endHour;
    private final int endMinute;

    /**
     * Creates an operating time frame with its opening and closing times.
     *
     * @param startHour   the hour the deck opens.
     * @param startMinute the minute the deck opens.
     * @param endHour     the hour the deck closes.
     * @param endMinute   the minute the deck closes.
     */
    Hour(int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    /**
     * Converts an hour and minute into the number of minutes since midnight.
     *
     * @param hour   the hour.
     * @param minute the minute.
     * @return the total minutes since midnight.
     */
    private static int toMinutes(int hour, int minute) {
        return hour * MINUTES_PER_HOUR + minute;
    }

    /**
     * Checks if a time is within the operating hours, including the
     * opening and closing times.
     *
     * @param hour   the hour to check.
     * @param minute the minute to check.
     * @return true if the deck is open at that time; false otherwise.
     */
    public boolean isOpen(int hour, int minute) {
        int time = toMinutes(hour, minute);
        return time >= toMinutes(startHour, startMinute)
                && time <= toMinutes(endHour, endMinute);
    }

    /**
     * Finds the time frame that matches a code, ignoring upper/lower case.
     *
     * @param code the code typed by the user, such as "HR7".
     * @return the matching time frame, or null if the code is not valid.
     */
    public static Hour fromCode(String code) {
        for (Hour hour : Hour.values()) {
            if (hour.name().equalsIgnoreCase(code)) {
                return hour;
            }
        }
        return null;
    }

    /**
     * Returns the operating hours as text, such as "6:30 ~ 18:30".
     *
     * @return the operating hours.
     */
    @Override
    public String toString() {
        return startHour + ":" + String.format("%02d", startMinute) + " ~ "
                + endHour + ":" + String.format("%02d", endMinute);
    }

    /**
     * returns the hour the deck opens
     *
     * @return the opening hour.
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Returns the minute the deck opens.
     *
     * @return the opening minute.
     */
    public int getStartMinute() {
        return startMinute;
    }
}