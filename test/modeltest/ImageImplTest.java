package modeltest;

import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class containing tests for the ImageImpl class.
 */
public class ImageImplTest {

  @Test
  public void testConstructorThrowsException() {
    //show that a width < 1 is invalid
    assertThrows("Invalid argument.", IllegalArgumentException.class,
        () -> new ImageImpl(0, 1024, 255, new Pixel[720][1024]));
    //show that a height < 1 is invalid
    assertThrows("Invalid argument.", IllegalArgumentException.class,
        () -> new ImageImpl(720, -1024, 255, new Pixel[720][1024]));
    //show that a max value < 1 is invalid
    assertThrows("Invalid argument.", IllegalArgumentException.class,
        () -> new ImageImpl(720, 1024, 0, new Pixel[720][1024]));
    //show that a null pixel array is invalid
    assertThrows("Invalid pixel array.", IllegalArgumentException.class,
        () -> new ImageImpl(720, 1024, 255, null));

    //show that a pixel array with a width different from the width parameter is invalid
    Pixel whitePixel = new PixelImpl(255, 255, 255, 255);
    Pixel[][] pixels = new Pixel[1024][719];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        pixels[i][j] = whitePixel;
      }
    }
    assertThrows("Invalid pixel array.", IllegalArgumentException.class,
        () -> new ImageImpl(720, 1024, 255, pixels));

    //show that a pixel array with a height different from the height parameter is invalid
    Pixel[][] pixels2 = new Pixel[124][720];
    for (int i = 0; i < pixels2.length; i++) {
      for (int j = 0; j < pixels2[0].length; j++) {
        pixels2[i][j] = whitePixel;
      }
    }
    assertThrows("Invalid pixel array.", IllegalArgumentException.class,
        () -> new ImageImpl(720, 1024, 255, pixels2));

    //show that now because there is one null Pixel in the array, an exception is thrown
    Pixel[][] pixels3 = new Pixel[1024][720];
    for (int i = 0; i < pixels3.length; i++) {
      for (int j = 0; j < pixels3[0].length; j++) {
        pixels3[i][j] = whitePixel;
      }
    }
    pixels3[500][500] = null;
    assertThrows("Invalid pixel array.", IllegalArgumentException.class,
        () -> new ImageImpl(720, 1024, 255, pixels3));
  }

  @Test
  public void testConstructorWorksCorrectly() {
    //test that giving the constructor valid inputs generates the image correctly
    int maxValue = 255;
    Pixel pixel00 = new PixelImpl(243, 32, 45, maxValue);
    Pixel pixel01 = new PixelImpl(78, 132, 90, maxValue);
    Pixel pixel10 = new PixelImpl(20, 240, 18, maxValue);
    Pixel pixel11 = new PixelImpl(54, 54, 255, maxValue);
    Pixel[][] pixels = new Pixel[][]{new Pixel[]{pixel00, pixel01}, new Pixel[]{pixel10, pixel11}};
    Image image = new ImageImpl(2, 2, maxValue, pixels);
    assertEquals(2, image.getWidth());
    assertEquals(2, image.getHeight());
    assertEquals(255, image.getMaxValue());
    assertEquals(243, image.getPixel(0, 0).getRed());
    assertEquals(32, image.getPixel(0, 0).getGreen());
    assertEquals(45, image.getPixel(0, 0).getBlue());
    assertEquals(78, image.getPixel(0, 1).getRed());
    assertEquals(132, image.getPixel(0, 1).getGreen());
    assertEquals(90, image.getPixel(0, 1).getBlue());
    assertEquals(20, image.getPixel(1, 0).getRed());
    assertEquals(240, image.getPixel(1, 0).getGreen());
    assertEquals(18, image.getPixel(1, 0).getBlue());
    assertEquals(54, image.getPixel(1, 1).getRed());
    assertEquals(54, image.getPixel(1, 1).getGreen());
    assertEquals(255, image.getPixel(1, 1).getBlue());
    assertEquals(pixel00, image.getPixel(0, 0));
    assertEquals(pixel01, image.getPixel(0, 1));
    assertEquals(pixel10, image.getPixel(1, 0));
    assertEquals(pixel11, image.getPixel(1, 1));
  }

  @Test
  public void testGetWidthGetHeightGetMaxValue() {
    int maxValue = 127;
    Pixel pixel00 = new PixelImpl(55, 32, 127, maxValue);
    Pixel pixel01 = new PixelImpl(78, 39, 90, maxValue);
    Pixel[][] pixels = new Pixel[][]{new Pixel[]{pixel00, pixel01}};
    Image image = new ImageImpl(2, 1, maxValue, pixels);
    assertEquals(2, image.getWidth());
    assertEquals(1, image.getHeight());
    assertEquals(maxValue, image.getMaxValue());
  }

  @Test
  public void testGetPixel() {
    int maxValue = 255;
    Pixel pixel00 = new PixelImpl(243, 32, 45, maxValue);
    Pixel pixel01 = new PixelImpl(78, 132, 90, maxValue);
    Pixel pixel10 = new PixelImpl(20, 240, 18, maxValue);
    Pixel pixel11 = new PixelImpl(54, 54, 255, maxValue);
    Pixel[][] pixels = new Pixel[][]{
        new Pixel[]{pixel00, pixel01},
        new Pixel[]{pixel10, pixel11}};
    Image image = new ImageImpl(2, 2, maxValue, pixels);

    //test that giving getPixel an invalid location will throw an exception
    assertThrows("Invalid row or column.", IllegalArgumentException.class,
        () -> image.getPixel(-1, 1));
    assertThrows("Invalid row or column.", IllegalArgumentException.class,
        () -> image.getPixel(0, -4));
    assertThrows("Invalid row or column.", IllegalArgumentException.class,
        () -> image.getPixel(2, 1));
    assertThrows("Invalid row or column.", IllegalArgumentException.class,
        () -> image.getPixel(0, 3));

    assertEquals(pixel00, image.getPixel(0, 0));
    assertEquals(pixel01, image.getPixel(0, 1));
    assertEquals(pixel10, image.getPixel(1, 0));
    assertEquals(pixel11, image.getPixel(1, 1));
  }
}
