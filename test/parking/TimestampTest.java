package parking;

import org.junit.jupiter.api.Test;
import util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
* JUnit tests for the compareTo() method of the Timestamp class.
* @author Ann Sajan
*/
class TimestampTest{
  
  /** An earlier date is less than a later date. */
  @Test
  void earlierDate(){
    Timestamp a = new Timestamp(new Date(2026,10,1), 9, 30);
    Timestamp b = new Timestamp(new Date(2026,10,2), 9, 30);
    assertEquals(-1, a.compareTo(b));
  }
    /** A later date is greater than an earlier date. */
  @Test
  void laterDate(){
    Timestamp a = new Timestamp(new Date(2026,10,2), 9, 30);
    Timestamp b = new Timestamp(new Date(2026,10,1), 9, 30);
    assertEquals(1, a.compareTo(b));
  }
    /** On the same date, an earlier hour is less. */
  @Test
  void earlierHour(){
    Timestamp a = new Timestamp(new Date(2026,10,2), 8, 45);
    Timestamp b = new Timestamp(new Date(2026,10,2), 9, 15);
    assertEquals(-1, a.compareTo(b));
  }

    /** On the same date, a later hour is greater. */
  @Test
  void laterHour(){
    Timestamp a = new Timestamp(new Date(2026,10,2), 14, 0);
    Timestamp b = new Timestamp(new Date(2026,10,2), 9, 59);
    assertEquals(1, a.compareTo(b));
  }

   /** On the same date and hour, an earlier minute is less. */
  @Test
  void earlierMinute(){
    Timestamp a = new Timestamp(new Date(2026,10,2), 9, 10);
    Timestamp b = new Timestamp(new Date(2026,10,2), 9, 20);
    assertEquals(-1, a.compareTo(b));
  }
 /** On the same date and hour, a later minute is greater. */
  @Test
  void laterMinute(){
    Timestamp a = new Timestamp(new Date(2026,10,2), 9, 50);
    Timestamp b = new Timestamp(new Date(2026,10,2), 9, 5);
    assertEquals(1, a.compareTo(b));
  }

   /** Tests that two timestamps with the same date, minute and hour return 0.*/
  @Test
  void identicalTimestamps(){
    Timestamp a = new Timestamp(new Date(2026,10,2), 9, 10);
    Timestamp b = new Timestamp(new Date(2026,10,2), 9, 10);
    assertEquals(0, a.compareTo(b));
  }
}
