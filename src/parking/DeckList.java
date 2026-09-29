package parking;

/**
 * A resizable array holding the list of parking decks.
 * The initial capacity is 4 and it grows by 4 whenever the list is full.
 * @author Alisha Sharma
 */
public class DeckList {
    private static final int ARRAYLENGTH = 4;
    private static final int NOTFOUND = -1;

    private Deck[] decks;
    private int numDecks;

    /**
     * Creates an empty deck list with the initial capacity.
     */
    public DeckList() {
        decks = new Deck[ARRAYLENGTH];
        numDecks = 0;
    }

    /**
     * Finds a deck in the list by its deck number.
     * @param deck the deck to search for.
     * @return the index of the deck or NOTFOUND if it not in the list.
     */
    private int find(Deck deck) {
        for (int i = 0; i < numDecks; i++) {
            if (decks[i].equals(deck)) {
                return i;
            }
        }
        return NOTFOUND;
    }

    /**
     * Increases the capacity of the list by 4.
     */
    private void grow() {
        Deck[] bigger = new Deck[decks.length + ARRAYLENGTH];
        for (int i = 0; i < numDecks; i++) {
            bigger[i] = decks[i];
        }
        decks = bigger;
    }

    /**
     * Compares two decks by county, then by deck number.
     * @param first the first deck.
     * @param second the second deck.
     * @return a negative number if first comes before second, a positive
     *         number if it comes after or 0 if they are in the same place.
     */
    private int compareByLocation(Deck first, Deck second) {
        int countyOrder = first.getLocation().getCounty()
                .compareTo(second.getLocation().getCounty());
        if (countyOrder != 0) {
            return countyOrder;
        }
        return first.getNumber() - second.getNumber();
    }

    /**
     * Sorts the decks by county, then by deck number, with insertion sort.
     */
    private void sortByLocation() {
        for (int i = 1; i < numDecks; i++) {
            Deck current = decks[i];
            int j = i - 1;
            while (j >= 0 && compareByLocation(decks[j], current) > 0) {
                decks[j + 1] = decks[j];
                j--;
            }
            decks[j + 1] = current;
        }
    }

    /**
     * Checks if at least one deck in the list is open.
     * @return true if any deck is open; false otherwise.
     */
    private boolean hasOpenDeck() {
        for (int i = 0; i < numDecks; i++) {
            if (decks[i].isOpen()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens a deck and A new deck is added to the end of the list,
     * A closed deck already in the list is reopened.
     * @param deck the deck to open.
     */
    public void open(Deck deck) {
        int index = find(deck);
        if (index != NOTFOUND) {
            decks[index].setOpen(true);
            return;
        }
        if (numDecks == decks.length) {
            grow();
        }
        decks[numDecks] = deck;
        numDecks++;
    }

    /**
     * Marks a deck as closed and the deck stays in the list.
     * @param deck the deck to close.
     */
    public void close(Deck deck) {
        int index = find(deck);
        if (index != NOTFOUND) {
            decks[index].setOpen(false);
        }
    }

    /**
     * Checks if a deck is in the list, open or closed.
     * @param deck the deck to search for.
     * @return true if the deck is in the list, otherwise false.
     */
    public boolean contains(Deck deck) {
        return find(deck) != NOTFOUND;
    }

    /**
     * Returns the deck in the list with the same number as the given one.
     * @param deck a deck with the number to search for.
     * @return the deck in the list or null if it is not in the list.
     */
    public Deck getDeck(Deck deck) {
        int index = find(deck);
        if (index == NOTFOUND) {
            return null;
        }
        return decks[index];
    }

    /**
     * Finds the open deck where a vehicle is parked.
     * @param vehicle the vehicle to search for.
     * @return the deck where the vehicle is parked or null if not parked.
     */
    public Deck findParked(Vehicle vehicle) {
        for (int i = 0; i < numDecks; i++) {
            if (decks[i].contains(vehicle)) {
                return decks[i];
            }
        }
        return null;
    }

    /**
     * Prints the open decks ordered by county and then by deck number.
     */
    public void printByLocation() {
        if (!hasOpenDeck()) {
            System.out.println("Deck list is empty - no deck is open.");
            return;
        }
        sortByLocation();
        System.out.println("** List of decks, ordered by county/deck number **");
        for (int i = 0; i < numDecks; i++) {
            if (decks[i].isOpen()) {
                System.out.println(decks[i]);
            }
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the vehicles parked in a deck ordered by license plate.
     * @param deck the deck to print.
     */
    public void printVehicles(Deck deck) {
        int index = find(deck);
        if (index != NOTFOUND) {
        decks[index].printByPlate();
    }
}
