package util;

import java.util.Calendar;

/**
 * A calendar date.
 *
 * @author Ann Sajan
 */
public class Date implements Comparable<Date> {
    /**
     * The year of this date.
     */
    private int year;
    /**
     * The month of this date.
     */
    private int month;
    /**
     * The day of the month.
     */
    private int day;
    /**
     * Years between leap years.
     */
    public static final int QUADRENNIAL = 4;
    /**
     * Century years are not leap years unless divisible by 400.
     */
    public static final int CENTENNIAL = 100;
    /**
     * Century years divisible by this are leap years.
     */
    public static final int QUATERCENTENNIAL = 400;

    /**
     * January, where Calendar.JANUARY is 0.
     */
    public static final int JAN = Calendar.JANUARY + 1;
    /**
     * February.
     */
    public static final int FEB = Calendar.FEBRUARY + 1;
    /**
     * April.
     */
    public static final int APR = Calendar.APRIL + 1;
    /**
     * June.
     */
    public static final int JUN = Calendar.JUNE + 1;
    /**
     * September.
     */
    public static final int SEP = Calendar.SEPTEMBER + 1;
    /**
     * November.
     */
    public static final int NOV = Calendar.NOVEMBER + 1;
    /**
     * December.
     */
    public static final int DEC = Calendar.DECEMBER + 1;

    /**
     * The first day of any month.
     */
    public static final int MIN_DAY = 1;
    /**
     * Days in a 31-day month.
     */
    public static final int DAYS_LONGMONTH = 31;
    /**
     * Days in a 30-day month.
     */
    public static final int DAYS_SHORTMONTH = 30;
    /**
     * Days in February in a non-leap year.
     */
    public static final int FEB_DAYS = 28;
    /**
     * Days in February in a leap year.
     */
    public static final int FEB_LEAPYEAR = 29;

    /**
     * Creates a date from a year, month (1-12), and day.
     *
     * @param year  the year
     * @param month the month, where January is 1
     * @param day   the day of the month
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Checks whether this date is a valid calendar date.
     *
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        if (month < JAN || month > DEC || day < MIN_DAY) {
            return false;
        }
        if (day > daysInMonth()) {
            return false;
        }
        return true;
    }

    /**
     * Finds the number of days in this date's month.
     *
     * @return the number of days in the month
     */
    private int daysInMonth() {
        if (month == FEB) {
            if (isLeap()) {
                return FEB_LEAPYEAR;
            }
            return FEB_DAYS;
        }
        if (month == APR || month == JUN || month == SEP || month == NOV) {
            return DAYS_SHORTMONTH;
        }
        return DAYS_LONGMONTH;
    }

    /**
     * Checks whether this date's year is a leap year.
     *
     * @return true if a leap year, false otherwise
     */
    private boolean isLeap() {
        if (year % QUADRENNIAL != 0) {
            return false;
        }
        if (year % CENTENNIAL != 0) {
            return true;
        }
        return year % QUATERCENTENNIAL == 0;
    }

    /**
     * Compares this date to another, by year, then month, then day.
     *
     * @param other the date to compare to
     * @return -1 if this date is earlier, 1 if later, 0 if the same
     */
    @Override
    public int compareTo(Date other) {
        if (year < other.year) {
            return -1;
        }
        if (year > other.year) {
            return 1;
        }
        if (month < other.month) {
            return -1;
        }
        if (month > other.month) {
            return 1;
        }
        if (day < other.day) {
            return -1;
        }
        if (day > other.day) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns the year of this date.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the month of this date, where January is 1.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the day of the month.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Checks whether another object is the same date.
     *
     * @param obj the object to compare to
     * @return true if it is a Date with the same year, month, and day
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date other = (Date) obj;
            if (year != other.year) {
                return false;
            }
            if (month != other.month) {
                return false;
            }
            if (day != other.day) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the date as year-month-day, such as 2026-03-09.
     *
     * @return the textual representation of the date
     */
    @Override
    public String toString() {
        return String.format("%d-%02d-%02d", year, month, day);
    }


}


