package parking;

import util.List;
import util.Sort;
import parking.Parking;

/**
 * The list of parking decks built on the generic List class.
 *
 * @author Alisha Sharma, Ann Sajan
 */
public class DeckList extends List<Deck> {
    private static final int NOT_FOUND = -1;

    /**
     * Checks if at least one deck in the list is open.
     *
     * @return true if any deck is open, otherwise false
     */
    private boolean hasOpenDeck() {
        for (Deck deck : this) {
            if (deck.isOpen()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens a deck. A new deck is added to the end of the list,
     * and a closed deck already in the list is reopened.
     *
     * @param deck the deck to open.
     */
    public void open(Deck deck) {
        int index = indexOf(deck);
        if (index != NOT_FOUND) {
            get(index).setOpen(true);
            return;
        }
        add(deck);
    }

    /**
     * Marks a deck as closed. The deck stays in the list.
     *
     * @param deck the deck to close.
     */
    public void close(Deck deck) {
        int index = indexOf(deck);
        if (index != NOT_FOUND) {
            get(index).setOpen(false);
        }
    }

    /**
     * Returns the deck in the list with the same number as the given one.
     *
     * @param deck a deck with the number to search for
     * @return the deck in the list or null if it is not in the list
     */
    public Deck getDeck(Deck deck) {
        int index = indexOf(deck);
        if (index == NOT_FOUND) {
            return null;
        }
        return get(index);
    }

    /**
     * Finds the deck where a vehicle is parked.
     *
     * @param vehicle the vehicle to search for.
     * @return the deck where the vehicle is parked or null if not parked.
     */
    public Deck findParked(Vehicle vehicle) {
        for (Deck deck : this) {
            if (deck.contains(vehicle)) {
                return deck;
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
        Sort.decksByLocation(this);
        System.out.println("** List of decks, ordered by county/deck number **");
        for (Deck deck : this) {
            if (deck.isOpen()) {
                System.out.println(deck);
            }
        }
        System.out.println("** end of list **");
    }

    /**
     * prints the cars parked in a deck, ordered by license plate
     *
     * @param deck the deck to print.
     */
    public void printVehicles(Deck deck) {
        int index = indexOf(deck);
        if (index == NOT_FOUND) {
            return;
        }
        Deck found = get(index);
        Sort.parkingsByPlate(found.getParkings());
        System.out.println("** List of vehicles in Deck# " + found.getNumber()
                + ", ordered by plate **");
        for (Parking parking : found.getParkings()) {
            System.out.println(parking);
        }
        System.out.println("** end of list **");
    }
}