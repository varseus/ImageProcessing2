package modeltest;

import org.junit.Test;

import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class containing tests for the PixelImpl class.
 */
public class PixelImplTest {

  @Test
  public void testConstructorThrowsException() {
    //test constructing a pixel with invalid rgb components throws an exception
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(-5, 56, 34, 255));
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(5, -56, 23, 255));
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(255, 0, -34, 255));
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(256, 56, 34, 255));
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(65, 85, 34, 84));
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(0, 0, 2, 1));
    assertThrows("Invalid pixel component values.", IllegalArgumentException.class,
        () -> new PixelImpl(-5, 453, 34, 119));

    //test constructing a pixel with invalid max value throws an exception
    assertThrows("Invalid max value.", IllegalArgumentException.class,
        () -> new PixelImpl(56, 34, 12, 0));
    assertThrows("Invalid max value.", IllegalArgumentException.class,
        () -> new PixelImpl(56, 34, 12, -50));
  }

  @Test
  public void testConstructorWorksCorrectly() {
    Pixel pixel1 = new PixelImpl(56, 255, 0, 255);
    assertEquals(56, pixel1.getRed());
    assertEquals(255, pixel1.getGreen());
    assertEquals(0, pixel1.getBlue());

    Pixel pixel2 = new PixelImpl(15, 0, 4, 15);
    assertEquals(15, pixel2.getRed());
    assertEquals(0, pixel2.getGreen());
    assertEquals(4, pixel2.getBlue());
  }

  @Test
  public void testGetRedGreenAndBlue() {
    Pixel pixel1 = new PixelImpl(234, 23, 45, 255);
    assertEquals(234, pixel1.getRed());
    assertEquals(23, pixel1.getGreen());
    assertEquals(45, pixel1.getBlue());
  }
}
