package parking;

/**
 * Cities where the parking decks are located.
 * @author YOUR NAME
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    PISCATAWAY("Middlesex", "08854"),
    EDISON("Middlesex", "08817"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }
}