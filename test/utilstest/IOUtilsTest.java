package utilstest;

import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.Image;
import model.Pixel;
import utils.IOUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test class for the methods of the IOUtils class.
 */
public class IOUtilsTest {

  @Test
  public void testLoadErrorChecking() {

    //loading a file that doesn't exist will throw an exception
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> IOUtils.load("test/modelTest/notRealFile.ppm"));
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> IOUtils.load("test/modelTest/blah.png"));
    //loading a file that exists, but at a different path will throw an exception
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> IOUtils.load("src/modelTest/testImage.ppm"));
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> IOUtils.load("src/controllertest/testImage.jpg"));
    //loading a file that isn't a supported file type will throw an exception
    assertThrows("Invalid file type.", IllegalArgumentException.class,
        () -> IOUtils.load("test/modelTest/testTXT.txt"));
    //loading a P6 ppm will throw an exception
    assertThrows("Invalid PPM file: plain RAW file should begin with P3",
            IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/testImage2.ppm"));
    //loading a ppm with invalid width will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/InvalidWidth.ppm"));
    //loading a ppm with invalid height will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/InvalidHeight.ppm"));
    //loading a ppm with invalid max value will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/InvalidMaxValue.ppm"));
    //loading a ppm with an invalid red component in one of the pixels will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/InvalidRed.ppm"));
    //loading a ppm with an invalid green component in one of the pixels will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/InvalidGreen.ppm"));
    //loading a ppm with an invalid blue component in one of the pixels will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> IOUtils.load("test/modelTest/InvalidBlue.ppm"));
    //loading a file of the correct type, but with broken data inside will throw an exception
    assertThrows("Unable to create FileInputStream.", IllegalStateException.class,
        () -> IOUtils.load("test/controllertest/brokenJPG.jpg"));
  }

  @Test
  public void testLoadingPPMWorksCorrectly() {
    //test that loading a valid PPM generates the correct image
    Image image = IOUtils.load("test/modelTest/testImage.ppm");
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
  }

  @Test
  public void testLoadingBMPAndPNGWorksCorrectly() {
    //since we have shown that the ppm loads correctly, loading in the same image from ppm, bmp and
    //png formats and showing that they are the same will prove that the bmp and png loaded
    //correctly
    Image ppmImage = IOUtils.load("test/modelTest/testImage.ppm");
    Image bmpImage = IOUtils.load("test/controllertest/testImage.bmp");
    Image pngImage = IOUtils.load("test/controllertest/testImage.png");

    //now show that the bmp is identical to the ppm
    assertEquals(ppmImage.getWidth(), bmpImage.getWidth());
    assertEquals(ppmImage.getHeight(), bmpImage.getHeight());
    assertEquals(ppmImage.getMaxValue(), bmpImage.getMaxValue());
    assertEquals(ppmImage.getPixel(0, 0).getRed(), bmpImage.getPixel(0, 0).getRed());
    assertEquals(ppmImage.getPixel(0, 0).getGreen(), bmpImage.getPixel(0, 0).getGreen());
    assertEquals(ppmImage.getPixel(0, 0).getBlue(), bmpImage.getPixel(0, 0).getBlue());
    assertEquals(ppmImage.getPixel(0, 1).getRed(), bmpImage.getPixel(0, 1).getRed());
    assertEquals(ppmImage.getPixel(0, 1).getGreen(), bmpImage.getPixel(0, 1).getGreen());
    assertEquals(ppmImage.getPixel(0, 1).getBlue(), bmpImage.getPixel(0, 1).getBlue());
    assertEquals(ppmImage.getPixel(1, 0).getRed(), bmpImage.getPixel(1, 0).getRed());
    assertEquals(ppmImage.getPixel(1, 0).getGreen(), bmpImage.getPixel(1, 0).getGreen());
    assertEquals(ppmImage.getPixel(1, 0).getBlue(), bmpImage.getPixel(1, 0).getBlue());
    assertEquals(ppmImage.getPixel(1, 1).getRed(), bmpImage.getPixel(1, 1).getRed());
    assertEquals(ppmImage.getPixel(1, 1).getGreen(), bmpImage.getPixel(1, 1).getGreen());
    assertEquals(ppmImage.getPixel(1, 1).getBlue(), bmpImage.getPixel(1, 1).getBlue());

    //now show that the png is identical to the ppm
    assertEquals(ppmImage.getWidth(), pngImage.getWidth());
    assertEquals(ppmImage.getHeight(), pngImage.getHeight());
    assertEquals(ppmImage.getMaxValue(), pngImage.getMaxValue());
    assertEquals(ppmImage.getPixel(0, 0).getRed(), pngImage.getPixel(0, 0).getRed());
    assertEquals(ppmImage.getPixel(0, 0).getGreen(), pngImage.getPixel(0, 0).getGreen());
    assertEquals(ppmImage.getPixel(0, 0).getBlue(), pngImage.getPixel(0, 0).getBlue());
    assertEquals(ppmImage.getPixel(0, 1).getRed(), pngImage.getPixel(0, 1).getRed());
    assertEquals(ppmImage.getPixel(0, 1).getGreen(), pngImage.getPixel(0, 1).getGreen());
    assertEquals(ppmImage.getPixel(0, 1).getBlue(), pngImage.getPixel(0, 1).getBlue());
    assertEquals(ppmImage.getPixel(1, 0).getRed(), pngImage.getPixel(1, 0).getRed());
    assertEquals(ppmImage.getPixel(1, 0).getGreen(), pngImage.getPixel(1, 0).getGreen());
    assertEquals(ppmImage.getPixel(1, 0).getBlue(), pngImage.getPixel(1, 0).getBlue());
    assertEquals(ppmImage.getPixel(1, 1).getRed(), pngImage.getPixel(1, 1).getRed());
    assertEquals(ppmImage.getPixel(1, 1).getGreen(), pngImage.getPixel(1, 1).getGreen());
    assertEquals(ppmImage.getPixel(1, 1).getBlue(), pngImage.getPixel(1, 1).getBlue());
  }

  @Test
  public void testLoadingJPGAndJPEGWorksCorrectly() {
    //since jpgs and jpegs are lossy, confirming they are the same as an identical ppm won't work.
    //So, we will show that when loaded in, the testImage has the correct dimensions, maxValue and
    //every pixel is as expected. However, since jpgs and jpegs should be identical, we will show
    //that the same image saved as a jpeg and jpg are the same as each other
    Image jpgImage = IOUtils.load("test/controllertest/testImage.jpg");
    Image jpegImage = IOUtils.load("test/controllertest/testImage.jpeg");

    //now show that the jpg has the expected specs (values for colors were found with color picker
    //to ensure they're the same before and after loading)
    assertEquals(2, jpgImage.getWidth());
    assertEquals(2, jpgImage.getHeight());
    assertEquals(255, jpgImage.getMaxValue());
    assertEquals(103, jpgImage.getPixel(0, 0).getRed());
    assertEquals(124, jpgImage.getPixel(0, 0).getGreen());
    assertEquals(109, jpgImage.getPixel(0, 0).getBlue());
    assertEquals(78, jpgImage.getPixel(0, 1).getRed());
    assertEquals(99, jpgImage.getPixel(0, 1).getGreen());
    assertEquals(84, jpgImage.getPixel(0, 1).getBlue());
    assertEquals(115, jpgImage.getPixel(1, 0).getRed());
    assertEquals(136, jpgImage.getPixel(1, 0).getGreen());
    assertEquals(121, jpgImage.getPixel(1, 0).getBlue());
    assertEquals(68, jpgImage.getPixel(1, 1).getRed());
    assertEquals(89, jpgImage.getPixel(1, 1).getGreen());
    assertEquals(74, jpgImage.getPixel(1, 1).getBlue());

    //now show that loading in the identical image from a .jpeg extension creates an identical image
    //to the .jpg version
    assertEquals(jpgImage.getWidth(), jpegImage.getWidth());
    assertEquals(jpgImage.getHeight(), jpegImage.getHeight());
    assertEquals(jpgImage.getMaxValue(), jpegImage.getMaxValue());
    assertEquals(jpgImage.getPixel(0, 0).getRed(), jpegImage.getPixel(0, 0).getRed());
    assertEquals(jpgImage.getPixel(0, 0).getGreen(), jpegImage.getPixel(0, 0).getGreen());
    assertEquals(jpgImage.getPixel(0, 0).getBlue(), jpegImage.getPixel(0, 0).getBlue());
    assertEquals(jpgImage.getPixel(0, 1).getRed(), jpegImage.getPixel(0, 1).getRed());
    assertEquals(jpgImage.getPixel(0, 1).getGreen(), jpegImage.getPixel(0, 1).getGreen());
    assertEquals(jpgImage.getPixel(0, 1).getBlue(), jpegImage.getPixel(0, 1).getBlue());
    assertEquals(jpgImage.getPixel(1, 0).getRed(), jpegImage.getPixel(1, 0).getRed());
    assertEquals(jpgImage.getPixel(1, 0).getGreen(), jpegImage.getPixel(1, 0).getGreen());
    assertEquals(jpgImage.getPixel(1, 0).getBlue(), jpegImage.getPixel(1, 0).getBlue());
    assertEquals(jpgImage.getPixel(1, 1).getRed(), jpegImage.getPixel(1, 1).getRed());
    assertEquals(jpgImage.getPixel(1, 1).getGreen(), jpegImage.getPixel(1, 1).getGreen());
    assertEquals(jpgImage.getPixel(1, 1).getBlue(), jpegImage.getPixel(1, 1).getBlue());
  }

  @Test
  public void testSaveExceptionChecking() {
    Image image = IOUtils.load("test/modelTest/testImage.ppm");

    //test that calling save on a null image throws an exception
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> IOUtils.save(null, "testImage.ppm"));
    //test that when the fileWriter throws an IOException when it cannot be created (in this case
    //because the given file name cannot be created as '/' is an invalid name) the correct exception
    //is thrown
    assertThrows("FileWriter could not be created.", IllegalStateException.class,
        () -> IOUtils.save(image, "/.ppm"));
    //test that you cannot save to a file that doesn't end in one of the valid file formats
    assertThrows("Invalid file type.", IllegalArgumentException.class,
        () -> IOUtils.save(image, "test/controllertest/hi"));
    assertThrows("Invalid file type.", IllegalArgumentException.class,
        () -> IOUtils.save(image, "test/controllertest/output.txt"));
  }

  @Test
  public void testSavingPPMWorksCorrectly() {
    Image image = IOUtils.load("test/modelTest/testImage.ppm");

    //test that saving a valid ppm file saves correctly by reloading the file, and
    //checking that that image is the same as the original
    IOUtils.save(image, "test/controllertest/output.ppm");
    Image viewSaveCopy = IOUtils.load("test/controllertest/output.ppm");
    assertEquals(2, viewSaveCopy.getWidth());
    assertEquals(2, viewSaveCopy.getHeight());
    assertEquals(255, viewSaveCopy.getMaxValue());
    assertEquals(243, viewSaveCopy.getPixel(0, 0).getRed());
    assertEquals(32, viewSaveCopy.getPixel(0, 0).getGreen());
    assertEquals(45, viewSaveCopy.getPixel(0, 0).getBlue());
    assertEquals(78, viewSaveCopy.getPixel(0, 1).getRed());
    assertEquals(132, viewSaveCopy.getPixel(0, 1).getGreen());
    assertEquals(90, viewSaveCopy.getPixel(0, 1).getBlue());
    assertEquals(20, viewSaveCopy.getPixel(1, 0).getRed());
    assertEquals(240, viewSaveCopy.getPixel(1, 0).getGreen());
    assertEquals(18, viewSaveCopy.getPixel(1, 0).getBlue());
    assertEquals(54, viewSaveCopy.getPixel(1, 1).getRed());
    assertEquals(54, viewSaveCopy.getPixel(1, 1).getGreen());
    assertEquals(255, viewSaveCopy.getPixel(1, 1).getBlue());
  }

  @Test
  public void testSavingBMPAndPNGWorksCorrectly() {
    //because we know loading works, we can assume the first loaded image is valid. Then, by saving
    //and reloading, testing that the newly loaded image is the same as the original will show that
    //it was saved correctly. Note- this test is only for bmp and png because this won't work for
    //jpg and jpeg as they are lossy (and we already tested ppm)
    Image bmpImage = IOUtils.load("test/controllertest/testImage.bmp");
    IOUtils.save(bmpImage, "test/controllertest/output.bmp");
    Image reloadedBMPImage = IOUtils.load("test/controllertest/output.bmp");
    assertEquals(bmpImage.getWidth(), reloadedBMPImage.getWidth());
    assertEquals(bmpImage.getHeight(), reloadedBMPImage.getHeight());
    assertEquals(bmpImage.getMaxValue(), reloadedBMPImage.getMaxValue());
    assertEquals(bmpImage.getPixel(0, 0).getRed(), reloadedBMPImage.getPixel(0, 0).getRed());
    assertEquals(bmpImage.getPixel(0, 0).getGreen(), reloadedBMPImage.getPixel(0, 0).getGreen());
    assertEquals(bmpImage.getPixel(0, 0).getBlue(), reloadedBMPImage.getPixel(0, 0).getBlue());
    assertEquals(bmpImage.getPixel(0, 1).getRed(), reloadedBMPImage.getPixel(0, 1).getRed());
    assertEquals(bmpImage.getPixel(0, 1).getGreen(), reloadedBMPImage.getPixel(0, 1).getGreen());
    assertEquals(bmpImage.getPixel(0, 1).getBlue(), reloadedBMPImage.getPixel(0, 1).getBlue());
    assertEquals(bmpImage.getPixel(1, 0).getRed(), reloadedBMPImage.getPixel(1, 0).getRed());
    assertEquals(bmpImage.getPixel(1, 0).getGreen(), reloadedBMPImage.getPixel(1, 0).getGreen());
    assertEquals(bmpImage.getPixel(1, 0).getBlue(), reloadedBMPImage.getPixel(1, 0).getBlue());
    assertEquals(bmpImage.getPixel(1, 1).getRed(), reloadedBMPImage.getPixel(1, 1).getRed());
    assertEquals(bmpImage.getPixel(1, 1).getGreen(), reloadedBMPImage.getPixel(1, 1).getGreen());
    assertEquals(bmpImage.getPixel(1, 1).getBlue(), reloadedBMPImage.getPixel(1, 1).getBlue());

    Image pngImage = IOUtils.load("test/controllertest/testImage.png");
    IOUtils.save(pngImage, "test/controllertest/output.png");
    Image reloadedPNGImage = IOUtils.load("test/controllertest/output.png");
    assertEquals(pngImage.getWidth(), reloadedPNGImage.getWidth());
    assertEquals(pngImage.getHeight(), reloadedPNGImage.getHeight());
    assertEquals(pngImage.getMaxValue(), reloadedPNGImage.getMaxValue());
    assertEquals(pngImage.getPixel(0, 0).getRed(), reloadedPNGImage.getPixel(0, 0).getRed());
    assertEquals(pngImage.getPixel(0, 0).getGreen(), reloadedPNGImage.getPixel(0, 0).getGreen());
    assertEquals(pngImage.getPixel(0, 0).getBlue(), reloadedPNGImage.getPixel(0, 0).getBlue());
    assertEquals(pngImage.getPixel(0, 1).getRed(), reloadedPNGImage.getPixel(0, 1).getRed());
    assertEquals(pngImage.getPixel(0, 1).getGreen(), reloadedPNGImage.getPixel(0, 1).getGreen());
    assertEquals(pngImage.getPixel(0, 1).getBlue(), reloadedPNGImage.getPixel(0, 1).getBlue());
    assertEquals(pngImage.getPixel(1, 0).getRed(), reloadedPNGImage.getPixel(1, 0).getRed());
    assertEquals(pngImage.getPixel(1, 0).getGreen(), reloadedPNGImage.getPixel(1, 0).getGreen());
    assertEquals(pngImage.getPixel(1, 0).getBlue(), reloadedPNGImage.getPixel(1, 0).getBlue());
    assertEquals(pngImage.getPixel(1, 1).getRed(), reloadedPNGImage.getPixel(1, 1).getRed());
    assertEquals(pngImage.getPixel(1, 1).getGreen(), reloadedPNGImage.getPixel(1, 1).getGreen());
    assertEquals(pngImage.getPixel(1, 1).getBlue(), reloadedPNGImage.getPixel(1, 1).getBlue());
  }

  @Test
  public void testSavingJPGAndJPEGWorksCorrectly() {
    //because jpgs and jpegs are lossy, we can't just test that saving them and loading back in will
    //give us the same image. So, we simply test that saving as a jpg and loading it back in gives
    //the new values we expect for the colors (maxValue, height and width shouldn't change). Then,
    //we test saving as a jpeg and loading it back in gives the same values as the jpg version
    Image initialImage = IOUtils.load("test/modeltest/testImage.ppm");
    IOUtils.load("test/modeltest/testImage.ppm");
    IOUtils.save(initialImage, "test/controllertest/output.jpg");
    Image jpgImage = IOUtils.load("test/controllertest/output.jpg");

    assertEquals(2, jpgImage.getWidth());
    assertEquals(2, jpgImage.getHeight());
    assertEquals(255, jpgImage.getMaxValue());
    assertEquals(103, jpgImage.getPixel(0, 0).getRed());
    assertEquals(124, jpgImage.getPixel(0, 0).getGreen());
    assertEquals(109, jpgImage.getPixel(0, 0).getBlue());
    assertEquals(78, jpgImage.getPixel(0, 1).getRed());
    assertEquals(99, jpgImage.getPixel(0, 1).getGreen());
    assertEquals(84, jpgImage.getPixel(0, 1).getBlue());
    assertEquals(115, jpgImage.getPixel(1, 0).getRed());
    assertEquals(136, jpgImage.getPixel(1, 0).getGreen());
    assertEquals(121, jpgImage.getPixel(1, 0).getBlue());
    assertEquals(68, jpgImage.getPixel(1, 1).getRed());
    assertEquals(89, jpgImage.getPixel(1, 1).getGreen());
    assertEquals(74, jpgImage.getPixel(1, 1).getBlue());

    IOUtils.save(initialImage, "test/controllertest/output.jpeg");
    Image jpegImage = IOUtils.load("test/controllertest/output.jpeg");
    assertEquals(jpgImage.getWidth(), jpegImage.getWidth());
    assertEquals(jpgImage.getHeight(), jpegImage.getHeight());
    assertEquals(jpgImage.getMaxValue(), jpegImage.getMaxValue());
    assertEquals(jpgImage.getPixel(0, 0).getRed(), jpegImage.getPixel(0, 0).getRed());
    assertEquals(jpgImage.getPixel(0, 0).getGreen(), jpegImage.getPixel(0, 0).getGreen());
    assertEquals(jpgImage.getPixel(0, 0).getBlue(), jpegImage.getPixel(0, 0).getBlue());
    assertEquals(jpgImage.getPixel(0, 1).getRed(), jpegImage.getPixel(0, 1).getRed());
    assertEquals(jpgImage.getPixel(0, 1).getGreen(), jpegImage.getPixel(0, 1).getGreen());
    assertEquals(jpgImage.getPixel(0, 1).getBlue(), jpegImage.getPixel(0, 1).getBlue());
    assertEquals(jpgImage.getPixel(1, 0).getRed(), jpegImage.getPixel(1, 0).getRed());
    assertEquals(jpgImage.getPixel(1, 0).getGreen(), jpegImage.getPixel(1, 0).getGreen());
    assertEquals(jpgImage.getPixel(1, 0).getBlue(), jpegImage.getPixel(1, 0).getBlue());
    assertEquals(jpgImage.getPixel(1, 1).getRed(), jpegImage.getPixel(1, 1).getRed());
    assertEquals(jpgImage.getPixel(1, 1).getGreen(), jpegImage.getPixel(1, 1).getGreen());
    assertEquals(jpgImage.getPixel(1, 1).getBlue(), jpegImage.getPixel(1, 1).getBlue());
  }

  @Test
  public void testGetBufferedImage() {
    assertThrows("Image cannot be null.", IllegalArgumentException.class,
        () -> IOUtils.getBufferedImage(null));

    Image image = IOUtils.load("test/modeltest/testImage.ppm");
    BufferedImage bufferedImage = IOUtils.getBufferedImage(image);
    assertEquals(image.getWidth(), bufferedImage.getWidth());
    assertEquals(image.getHeight(), bufferedImage.getHeight());
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color color = new Color(bufferedImage.getRGB(j, i));
        Pixel pixel = image.getPixel(i, j);
        assertEquals(pixel.getRed(), color.getRed());
      }
    }
  }
}
