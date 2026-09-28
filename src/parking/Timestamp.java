package parking;

/**
 * A date and time.
 * @author Ann Sajan
 */
public class Timestamp implements Comparable<Timestamp> {
    private Date date;
    private byte hour;
    private byte minute;

    @Override
    public int compareTo(Timestamp other) {
        return 0; // TODO Ann
    }
}
