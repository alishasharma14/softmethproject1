package parking;

/**
 * A calendar date.
 * @author PARTNER NAME
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public boolean isValid() {
        return false; // TODO (PARTNER)
    }

    @Override
    public int compareTo(Date other) {
        return 0; // TODO (PARTNER)
    }
}