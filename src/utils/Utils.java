package utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A utility class, used for error checking with Scanners.
 */
public class Utils {
  /**
   * Returns the next value in the Scanner, if it exists.
   *
   * @param scanner the Scanner to check
   * @return the next value in the Scanner
   * @throws IllegalStateException if the Scanner is empty
   */
  public static String getScannerNext(Scanner scanner) throws IllegalStateException {
    String next;
    try {
      next = scanner.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Scanner ran out of values.");
    }
    return next;
  }

  /**
   * Returns the next value in the Scanner, if it exists and is an int.
   * If the value exists, but is not an int, the value is processed by the scanner but not used.
   *
   * @param scanner the Scanner to check
   * @return the next int in the Scanner
   * @throws IllegalStateException if the Scanner is empty or the next value is not an int
   */
  public static int getScannerNextInt(Scanner scanner) throws IllegalStateException {
    String next = Utils.getScannerNext(scanner);
    int nextInt;
    try {
      nextInt = Integer.parseInt(next);
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Value is not an integer.");
    }
    return nextInt;
  }
}
