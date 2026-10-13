package parking;

import util.Date;
import util.List;

import java.util.Calendar;

/**
 * a vehicle thats registered for parking, we identify it by its license plate.
 * Each vehicle keeps its parking history and the notifications it got
 * @author Alisha Sharma, Ann Sajan
 */
public abstract class Vehicle implements Observer {
    /** The number of characters in a valid license plate. */
    private static final int PLATE_LENGTH = 7;
    /** The index of the dash in a license plate. */
    private static final int DASH_INDEX = 3;
    /** the dash between the numbers and the letters at the end */
    private static final char DASH = '-';
    /** minutes in one hour */
    private static final int MINUTES_PER_HOUR = 60;
    /** The number of minutes in one day. */
    private static final int MINUTES_PER_DAY = 1440;
    /** milliseconds in one day, used for counting days between two dates */
    private static final long MILLIS_PER_DAY = 86400000L;
    /** if a car stays more than this many hours in one day it pays the daily rate */
    private static final int MAX_HOURLY = 6;

    /** The license plate, kept the way the user typed it. */
    private String plate;
    /** The completed parking activities, oldest first. */
    private List<History> history;
    /** the notifications this vehicle got */
    private List<String> notifications;

    /**
     * Creates a vehicle with the given license plate and no parking history.
     * @param plate the license plate.
     */
    public Vehicle(String plate) {
        this.plate = plate;
        this.history = new List<History>();
        this.notifications = new List<String>();
    }

    /**
     * turns a date into a Calendar at midnight so we can do math with it
     * @param date the date to convert.
     * @return a Calendar set to midnight on that date.
     */
    private Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(date.getYear(), date.getMonth() - Date.JAN, date.getDay());
        return calendar;
    }

    /**
     * Counts how many days apart two dates are.
     * @param first the earlier date.
     * @param second the later date.
     * @return the number of days from the first date to the second.
     */
    private int daysBetween(Date first, Date second) {
        long difference = toCalendar(second).getTimeInMillis()
                - toCalendar(first).getTimeInMillis();
        return (int) Math.round((double) difference / MILLIS_PER_DAY);
    }

    /**
     * gets how many minutes past midnight a timestamp is
     * @param time the timestamp.
     * @return the minutes since midnight.
     */
    private int minuteOfDay(Timestamp time) {
        return time.getHour() * MINUTES_PER_HOUR + time.getMinute();
    }

    /**
     * Calculates the fee for one day of parking. Partial hours count as a
     * full hour, and more than 6 hours pays the daily rate instead
     * @param minutes the minutes parked during that day.
     * @param hourlyRate the hourly rate for this vehicle type.
     * @param dailyRate the daily rate for this vehicle type.
     * @return the fee for that day, in dollars.
     */
    private int segmentFee(int minutes, int hourlyRate, int dailyRate) {
        int hours = (minutes + MINUTES_PER_HOUR - 1) / MINUTES_PER_HOUR;
        if (hours > MAX_HOURLY) {
            return dailyRate;
        }
        return hours * hourlyRate;
    }

    /**
     * calculates the total fee. the stay gets split into days that start at
     * the decks opening time and each day is charged on its own
     * @param deck the deck where the vehicle parked.
     * @param enter the time the vehicle entered.
     * @param exit the time the vehicle exited.
     * @param hourlyRate the hourly rate for this vehicle type.
     * @param dailyRate the daily rate for this vehicle type.
     * @return the total parking fee, in dollars.
     */
    protected int calculateFee(Deck deck, Timestamp enter, Timestamp exit,
                               int hourlyRate, int dailyRate) {
        int opening = deck.getHour().getStartHour() * MINUTES_PER_HOUR
                + deck.getHour().getStartMinute();
        int start = minuteOfDay(enter);
        int end = daysBetween(enter.getDate(), exit.getDate()) * MINUTES_PER_DAY
                + minuteOfDay(exit);
        int fee = 0;
        while (start < end) {
            int nextOpening = (start / MINUTES_PER_DAY + 1) * MINUTES_PER_DAY + opening;
            int segmentEnd = Math.min(end, nextOpening);
            fee += segmentFee(segmentEnd - start, hourlyRate, dailyRate);
            start = segmentEnd;
        }
        return fee;
    }

    /**
     * Checks if the license plate is in the format "Xdd-XXX", where each X
     * is a letter and each d is a digit.
     * @return true if the plate is valid, otherwise false.
     */
    public boolean isValid() {
        if (plate.length() != PLATE_LENGTH) {
            return false;
        }
        if (!Character.isLetter(plate.charAt(0))) {
            return false;
        }
        for (int i = 1; i < DASH_INDEX; i++) {
            if (!Character.isDigit(plate.charAt(i))) {
                return false;
            }
        }
        if (plate.charAt(DASH_INDEX) != DASH) {
            return false;
        }
        for (int i = DASH_INDEX + 1; i < PLATE_LENGTH; i++) {
            if (!Character.isLetter(plate.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the license plate of this vehicle.
     * @return the license plate.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * returns the parking history so VehicleList can print it
     * @return the parking history, oldest first.
     */
    public List<History> getHistory() {
        return history;
    }

    /**
     * Checks if this vehicle has any parking history.
     * @return true if the vehicle has parking history, otherwise false.
     */
    public boolean hasHistory() {
        return !history.isEmpty();
    }

    /**
     * adds a finished parking activity to the end of the history, so the
     * oldest one is always first
     * @param parking the completed parking activity
     */
    public void addHistory(Parking parking) {
        history.add(new History(parking));
    }

    /**
     * Calculates the parking fee based on the vehicle type and how long it stayed.
     * @param deck the deck where the vehicle parked.
     * @param enter the time the vehicle entered.
     * @param exit the time the vehicle exited.
     * @return the parking fee, in dollars.
     */
    public abstract int parkingFee(Deck deck, Timestamp enter, Timestamp exit);

    /**
     * gets a notification and saves it
     * @param message the notification message.
     */
    @Override
    public void update(String message) {
        notifications.add(message);
    }

    /**
     * Returns the notifications this vehicle has received.
     * @return the list of notifications.
     */
    public List<String> getNotifications() {
        return notifications;
    }

    /**
     * Checks if two vehicles have the same license plate, ignoring upper and lower case.
     * @param obj the object to compare with.
     * @return true if obj is a vehicle with the same plate, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle) {
            Vehicle other = (Vehicle) obj;
            return plate.equalsIgnoreCase(other.plate);
        }
        return false;
    }

    /**
     * returns the plate and the type, like "Z22-KVQ [Sedan]"
     * @return the text form of the vehicle.
     */
    @Override
    public String toString() {
        return plate + " [" + getClass().getSimpleName() + "]";
    }
}