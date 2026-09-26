package parking;

/**
 * A parking deck.
 * @author YOUR NAME, PARTNER NAME
 */
public class Deck {
    public static final int MAXCAPACITY = 6;
    private int number;          //deck number
    private Location location;
    private Hour hour;
    private Parking[] parkings;  //a list of parking activities
    private int numParked;       //number of parking activities in array
    private boolean open;

    private int find(Vehicle vehicle) { return -1; } // TODO (PARTNER)
    public void enter(Parking parking) { }           // TODO (PARTNER)
    public void exit(Parking parking) { }            // TODO (PARTNER)
}