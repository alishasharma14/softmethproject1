package parking;

/**
 * Resizable array holding the list of decks.
 *
 * @author Alisha Sharma
 */
public class DeckList {
    private static final int ARRAYLENGTH = 4;
    private static final int NOTFOUND = -1;
    private Deck[] decks;
    private int numDecks;

    private int find(Deck deck) {
        return NOTFOUND;
    }    // TODO alisha

    private void grow() {
    }                             // TODO alisha

    public void open(Deck deck) {
    }                     // TODO alisha

    public void close(Deck deck) {
    }                    // TODO alisha

    public boolean contains(Deck deck) {
        return false;
    } // alisha

    public void printByLocation() {
    }                   // TODO Ann

    public void printVehicles(Deck deck) {
    }            // TODO Ann
}