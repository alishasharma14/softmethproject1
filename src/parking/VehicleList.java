package parking;

/**
 * Resizable array holding the list of vehicles.
 * @author YOUR NAME
 */
public class VehicleList {
    private static final int LENGTH = 4;
    private static final int NOTFOUND = -1;
    private Vehicle[] vehicles;
    private int numVehicles;

    private int find(Vehicle vehicle) { return NOTFOUND; } // TODO (YOU)
    private void grow() { }                                // TODO (YOU)
    public void add(Vehicle vehicle) { }                   // TODO (YOU)
    public void remove(Vehicle vehicle) { }                // TODO (YOU)
    public boolean contains(Vehicle vehicle) { return false; } // TODO (YOU)
    public void printByPlate() { }                         // TODO (PARTNER)
    public void printHistory() { }                         // TODO (PARTNER)
}