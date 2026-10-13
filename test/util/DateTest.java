package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* JUnit tests for isValid() method of the Date class.
* @author Ann Sajan
*/
class DateTest{

  /** February 29 in a non-leap year is invalid. */
  @Test
  void invalidFebruary29NonLeapYear(){
    assertFalse(new Date(2026, 2, 29).isValid());
  }
  
  /** 1900 is divisible by 100 but not by 400, so Feb 29 should be invalid. */
  @Test
  void invalidCenturyLeapDay(){
     assertFalse(new Date(1900, 2, 29).isValid());
  }

  /** Day 31 in a 30 day month is invalid. */
  @Test
  void invalidDay31(){
     assertFalse(new Date(2026, 6, 31).isValid());
  }
  
   /** Month 13 is invalid. */
  @Test
  void invalidMonth13(){
     assertFalse(new Date(2026, 13, 3).isValid());
  }

    /** February 29 in a leap year is valid.*/
  @Test
  void validLeapYearDate(){
     assertTrue(new Date(2028, 2, 29).isValid());
  }

   /** December 31 is valid. */
  @Test
  void validLastDay(){
     assertTrue(new Date(2026, 12, 31).isValid());
  }
}
