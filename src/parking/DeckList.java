package parking;

/**
 * Resizable array holding the list of decks.
 * @author YOUR NAME
 */
public class DeckList {
    private static final int ARRAYLENGTH = 4;
    private static final int NOTFOUND = -1;
    private Deck[] decks;
    private int numDecks;

    private int find(Deck deck) { return NOTFOUND; }    // TODO (YOU)
    private void grow() { }                             // TODO (YOU)
    public void open(Deck deck) { }                     // TODO (YOU)
    public void close(Deck deck) { }                    // TODO (YOU)
    public boolean contains(Deck deck) { return false; } // TODO (YOU)
    public void printByLocation() { }                   // TODO (PARTNER)
    public void printVehicles(Deck deck) { }            // TODO (PARTNER)
}