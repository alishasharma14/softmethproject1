package parking;

/**
 * A parking deck.
 *
 * @author Alisha Sharma, Ann Sujan
 */
public class Deck {
    public static final int MAXCAPACITY = 6;
    private int number;          //deck number
    private Location location;
    private Hour hour;
    private Parking[] parkings;  //a list of parking activities
    private int numParked;       //number of parking activities in array
    private boolean open;

    private int find(Vehicle vehicle) {
        return -1;
    } // TODO ann

    public void enter(Parking parking) {
    }           // TODO ann

    public void exit(Parking parking) {
    }            // TODO ann
}