package parking;

/**
 * Operating hours for the parking decks.
 * @author YOUR NAME
 */
public enum Hour {
    HR5(5, 0, 21, 30),
    HR6(6, 30, 18, 30),
    HR7(7, 0, 18, 0);

    private final int startHour;
    private final int startMinute;
    private final int endHour;
    private final int endMinute;

    Hour(int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }
}