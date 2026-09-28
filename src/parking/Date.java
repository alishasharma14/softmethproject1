package parking;

import java.util.Calendar;

/**
 * A calendar date.
 * @author Ann Sajan
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4; 
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400; 

    public static final int JAN = Calendar.JANUARY + 1; //Calendar.JANUARY is 0
    public static final int FEB = Calendar.FEBRUARY + 1;
    public static final int APR = Calendar.APRIL + 1;
    public static final int JUN = Calendar.JUNE + 1;
    public static final int SEP = Calendar.SEPTEMBER + 1;
    public static final int NOV = Calendar.NOVEMBER + 1;
    public static final int DEC = Calendar.DECEMBER + 1;

    public static final int MIN_DAY = 1;
    public static final int DAYS_LONGMONTH = 31;
    public static final int DAYS_SHORTMONTH = 30;
    public static final int FEB_DAYS = 28;
    public static final int FEB_LEAPYEAR = 29;

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isValid() {
        return false; // ann
    }

    @Override
    public int compareTo(Date other) {
        return 0; // ann
    }
}
