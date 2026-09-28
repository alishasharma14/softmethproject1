package parking;

/**
 * A calendar date.
 * @author Ann Sajan
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public boolean isValid() {
        return false; // ann
    }

    @Override
    public int compareTo(Date other) {
        return 0; // ann
    }
}
