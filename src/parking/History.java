package parking;

/**
 * A node in the singly linked list of parking history.
 *
 * @author Ann Sajan
 */
public class History {
    /**
     * The parking activity.
     */
    private Parking parking;
    /**
     * The next node in the list.
     */
    private History next;

    /**
     * Creates a node holding a parking activity, not yet linked to any node.
     *
     * @param parking the completed parking activity
     */
    public History(Parking parking) {
        this.parking = parking;
        this.next = null;
    }

    /**
     * Returns the parking activity held by this node.
     *
     * @return the parking activity
     */
    public Parking getParking() {
        return parking;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next node, or null if this is the last node
     */
    public History getNext() {
        return next;
    }

    /**
     * Sets the next node in the list.
     *
     * @param next the node to link after this one
     */
    public void setNext(History next) {
        this.next = next;
    }
}
