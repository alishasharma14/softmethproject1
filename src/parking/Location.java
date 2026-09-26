package parking;

/**
 * The cities where the parking decks are located, with county and zip code.
 *
 * @author Alisha Sharma
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

    /**
     * Creates a location with its county and zip code.
     *
     * @param county the county the city is in.
     * @param zip    the zip code of the city.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Returns the county of this location.
     *
     * @return the county name.
     */
    public String getCounty() {
        return county;
    }

    /**
     * Returns the zip code of this location.
     *
     * @return the zip code.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Finds the location that matches a city name, ignoring upper/lower case.
     *
     * @param city the city name typed by the user.
     * @return the matching location, or null if the city is not valid.
     */
    public static Location fromName(String city) {
        for (Location location : Location.values()) {
            if (location.name().equalsIgnoreCase(city)) {
                return location;
            }
        }
        return null;
    }
}