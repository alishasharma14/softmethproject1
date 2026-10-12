package util;

import parking.Deck;
import parking.Vehicle;
import parking.Parking;

/**
 * A utility class for sorting lists of objects in place with insertion sort
 * @author Alisha Sharma
 */
public class Sort {

    /**
     * Compares two decks by county then by deck number.
     * @param first the first deck.
     * @param second the second deck.
     * @return a negative number if first comes before second, a positive
     *         number if it comes after, or 0 if they are in the same place
     */
    private static int compareByLocation(Deck first, Deck second) {
        int countyOrder = first.getLocation().getCounty()
                .compareTo(second.getLocation().getCounty());
        if (countyOrder != 0) {
            return countyOrder;
        }
        return first.getNumber() - second.getNumber();
    }

    /**
     * Sorts a list of vehicles by license plate
     * @param list the list of vehicles to sort.
     */
    public static void vehiclesByPlate(List<Vehicle> list) {
        for (int i = 1; i < list.size(); i++) {
            Vehicle current = list.get(i);
            int j = i - 1;
            while (j >= 0
                    && list.get(j).getPlate().compareTo(current.getPlate()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    /**
     * Sorts a list of decks by county then by deck number
     * @param list the list of decks to sort.
     */
    public static void decksByLocation(List<Deck> list) {
        for (int i = 1; i < list.size(); i++) {
            Deck current = list.get(i);
            int j = i - 1;
            while (j >= 0 && compareByLocation(list.get(j), current) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    /**
     * Sorts a list of decks by deck number
     * @param list the list of decks to sort.
     */
    public static void decksByNumber(List<Deck> list) {
        for (int i = 1; i < list.size(); i++) {
            Deck current = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getNumber() > current.getNumber()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    /**
     * Sorts a list of parking activities by license plate.
     * @param list the list of parking activities to sort.
     */
    public static void parkingsByPlate(List<Parking> list) {
        for (int i = 1; i < list.size(); i++) {
            Parking current = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getVehicle().getPlate()
                    .compareTo(current.getVehicle().getPlate()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }
}