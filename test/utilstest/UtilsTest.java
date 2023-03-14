package utilstest;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class containing tests for the Utils class.
 */
public class UtilsTest {

  @Test
  public void testGetScannerNext() {
    //test that the scanner correctly gives the next value, when it exists, and throws an exception
    //when it doesn't
    Scanner scanner = new Scanner(new StringReader("scanner value"));
    assertEquals("scanner", Utils.getScannerNext(scanner));
    assertEquals("value", Utils.getScannerNext(scanner));
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> Utils.getScannerNext(scanner));
  }

  @Test
  public void testGetScannerNextInt() {
    Scanner scanner = new Scanner(new StringReader("65 six"));
    //test that the scanner correctly gives the next int value when it exists, and throws an
    //exception when it doesn't
    assertEquals(65, Utils.getScannerNextInt(scanner));
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> Utils.getScannerNextInt(scanner));
    //the 'six' is consumed in the previous call of getScannerNextInt, so the Scanner is now empty
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> Utils.getScannerNextInt(scanner));
  }
}
