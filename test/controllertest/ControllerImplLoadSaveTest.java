package controllertest;

import org.junit.Test;

import java.io.StringReader;

import controller.IMEController;
import controller.IMEControllerImpl;
import model.IMEModel;
import model.IMEModelImpl;
import model.Image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class containing tests for the constructor, loading and saving methods for the ControllerImpl
 * class.
 */
public class ControllerImplLoadSaveTest {

  @Test
  public void testConstructorThrowsException() {
    //test that giving a null model throws an exception
    assertThrows("Parameters cannot be null.", IllegalArgumentException.class,
        () -> new IMEControllerImpl(null, new StringReader("")));
    //test that giving a null Readable throws an exception
    assertThrows("Parameters cannot be null.", IllegalArgumentException.class,
        () -> new IMEControllerImpl(new IMEModelImpl(), null));
  }

  @Test
  public void testInvalidOperations() {
    //provide a series of commands that are *almost* correct and show that an exception is still
    //thrown
    IMEModel model = new IMEModelImpl();
    assertThrows("Invalid command.", IllegalArgumentException.class,
        () -> new IMEControllerImpl(model, new StringReader("q")).run());
    assertThrows("Invalid command.", IllegalArgumentException.class,
        () -> new IMEControllerImpl(model, new StringReader("LOAD quit")).run());
    assertThrows("Invalid command.", IllegalArgumentException.class,
        () -> new IMEControllerImpl(model,
                    new StringReader("load test/modelTest/testImage testImage " +
                            "flip-horizontally testImage horizontal-flip")).run());
    assertThrows("Invalid command.", IllegalArgumentException.class,
        () -> new IMEControllerImpl(model,
                    new StringReader("load test/modelTest/testImage testImage " +
                            "diagonal-flip testImage diagonal-flip")).run());
  }

  @Test
  public void testRunQuit() {
    IMEModel model = new IMEModelImpl();
    IMEController controller1 = new IMEControllerImpl(model, new StringReader(""));
    //show that not saying quit causes an IllegalStateException
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> controller1.run());
    //show that saying quit gets rid of this exception
    IMEController controller2 = new IMEControllerImpl(model, new StringReader("quit"));
  }

  @Test
  public void testLoadExceptionChecking() {
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));

    //loading a file that doesn't exist will throw an exception
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> controller.load("test/modelTest/notRealFile.ppm", "testImage"));
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> controller.load("test/modelTest/blah.png", "testImage"));
    //loading a file that exists, but at a different path will throw an exception
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> controller.load("src/modelTest/testImage.ppm", "testImage"));
    assertThrows("Invalid file name or path.", IllegalArgumentException.class,
        () -> controller.load("src/controllertest/testImage.jpg", "testImage"));
    //loading a file that isn't a supported file type will throw an exception
    assertThrows("Invalid file type.", IllegalArgumentException.class,
        () -> controller.load("test/modelTest/testTXT.txt", "testImage"));
    //loading a P6 ppm will throw an exception
    assertThrows("Invalid PPM file: plain RAW file should begin with P3",
            IllegalStateException.class,
        () -> controller.load("test/modelTest/testImage2.ppm", "testImage"));
    //loading a ppm with invalid width will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> controller.load("test/modelTest/InvalidWidth.ppm", "testImage"));
    //loading a ppm with invalid height will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> controller.load("test/modelTest/InvalidHeight.ppm", "testImage"));
    //loading a ppm with invalid max value will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> controller.load("test/modelTest/InvalidMaxValue.ppm", "testImage"));
    //loading a ppm with an invalid red component in one of the pixels will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> controller.load("test/modelTest/InvalidRed.ppm", "testImage"));
    //loading a ppm with an invalid green component in one of the pixels will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> controller.load("test/modelTest/InvalidGreen.ppm", "testImage"));
    //loading a ppm with an invalid blue component in one of the pixels will throw an exception
    assertThrows("Value is not an integer.", IllegalStateException.class,
        () -> controller.load("test/modelTest/InvalidBlue.ppm", "testImage"));
    //loading a file of the correct type, but with broken data inside will throw an exception
    assertThrows("Unable to create FileInputStream.", IllegalStateException.class,
        () -> controller.load("test/controllertest/brokenJPG.jpg", "broken"));
  }

  @Test
  public void testLoadingPPMWorksCorrectly() {
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    //test that loading a valid PPM generates the correct image
    controller.load("test/modelTest/testImage.ppm", "testImage");
    Image imageCopy = model.getImageCopy("testImage");
    assertEquals(2, imageCopy.getWidth());
    assertEquals(2, imageCopy.getHeight());
    assertEquals(255, imageCopy.getMaxValue());
    assertEquals(243, imageCopy.getPixel(0, 0).getRed());
    assertEquals(32, imageCopy.getPixel(0, 0).getGreen());
    assertEquals(45, imageCopy.getPixel(0, 0).getBlue());
    assertEquals(78, imageCopy.getPixel(0, 1).getRed());
    assertEquals(132, imageCopy.getPixel(0, 1).getGreen());
    assertEquals(90, imageCopy.getPixel(0, 1).getBlue());
    assertEquals(20, imageCopy.getPixel(1, 0).getRed());
    assertEquals(240, imageCopy.getPixel(1, 0).getGreen());
    assertEquals(18, imageCopy.getPixel(1, 0).getBlue());
    assertEquals(54, imageCopy.getPixel(1, 1).getRed());
    assertEquals(54, imageCopy.getPixel(1, 1).getGreen());
    assertEquals(255, imageCopy.getPixel(1, 1).getBlue());
  }

  @Test
  public void testLoadingBMPAndPNGWorksCorrectly() {
    //since we have shown that the ppm loads correctly, loading in the same image from ppm, bmp and
    //png formats and showing that they are the same will prove that the bmp and png loaded
    //correctly
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    controller.load("test/modelTest/testImage.ppm", "testImagePPM");
    controller.load("test/controllertest/testImage.bmp", "testImageBMP");
    controller.load("test/controllertest/testImage.png", "testImagePNG");
    Image ppmImage = model.getImageCopy("testImagePPM");
    Image bmpImage = model.getImageCopy("testImageBMP");
    Image pngImage = model.getImageCopy("testImagePNG");

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
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    controller.load("test/controllertest/testImage.jpg", "testImageJPG");
    controller.load("test/controllertest/testImage.jpeg", "testImageJPEG");
    Image jpgImage = model.getImageCopy("testImageJPG");
    Image jpegImage = model.getImageCopy("testImageJPEG");

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
  public void testRunLoad() {
    IMEModel model1 = new IMEModelImpl();
    //show that not providing both a file name and an image name will throw an exception
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> new IMEControllerImpl(model1, new StringReader("load")).run());
    //show that not providing both a file name and an image name will throw an exception
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> new IMEControllerImpl(model1, new StringReader("load testImage.ppm")).run());

    //show that calling load correctly loads the image into the model correctly by getting a copy
    //of the image, and verifying the width, height, max value and individual pixel components are
    //correct
    Readable readable1 = new StringReader("load test/modelTest/testImage.ppm testImage quit");
    IMEController controller1 = new IMEControllerImpl(model1, readable1);
    controller1.run();
    Image ppmImageCopy = model1.getImageCopy("testImage");
    assertEquals(2, ppmImageCopy.getWidth());
    assertEquals(2, ppmImageCopy.getHeight());
    assertEquals(255, ppmImageCopy.getMaxValue());
    assertEquals(243, ppmImageCopy.getPixel(0, 0).getRed());
    assertEquals(32, ppmImageCopy.getPixel(0, 0).getGreen());
    assertEquals(45, ppmImageCopy.getPixel(0, 0).getBlue());
    assertEquals(78, ppmImageCopy.getPixel(0, 1).getRed());
    assertEquals(132, ppmImageCopy.getPixel(0, 1).getGreen());
    assertEquals(90, ppmImageCopy.getPixel(0, 1).getBlue());
    assertEquals(20, ppmImageCopy.getPixel(1, 0).getRed());
    assertEquals(240, ppmImageCopy.getPixel(1, 0).getGreen());
    assertEquals(18, ppmImageCopy.getPixel(1, 0).getBlue());
    assertEquals(54, ppmImageCopy.getPixel(1, 1).getRed());
    assertEquals(54, ppmImageCopy.getPixel(1, 1).getGreen());
    assertEquals(255, ppmImageCopy.getPixel(1, 1).getBlue());

    //show another example of the loading command working. Since load simply gives calls the load()
    //method, we are not going to show an example for every image type as it is unnecessary
    IMEModel model2 = new IMEModelImpl();
    Readable readable2 = new StringReader("load test/controllertest/testImage.png testImage quit");
    IMEController controller2 = new IMEControllerImpl(model2, readable2);
    controller2.run();
    Image pngImageCopy = model2.getImageCopy("testImage");
    assertEquals(2, pngImageCopy.getWidth());
    assertEquals(2, pngImageCopy.getHeight());
    assertEquals(255, pngImageCopy.getMaxValue());
    assertEquals(243, pngImageCopy.getPixel(0, 0).getRed());
    assertEquals(32, pngImageCopy.getPixel(0, 0).getGreen());
    assertEquals(45, pngImageCopy.getPixel(0, 0).getBlue());
    assertEquals(78, pngImageCopy.getPixel(0, 1).getRed());
    assertEquals(132, pngImageCopy.getPixel(0, 1).getGreen());
    assertEquals(90, pngImageCopy.getPixel(0, 1).getBlue());
    assertEquals(20, pngImageCopy.getPixel(1, 0).getRed());
    assertEquals(240, pngImageCopy.getPixel(1, 0).getGreen());
    assertEquals(18, pngImageCopy.getPixel(1, 0).getBlue());
    assertEquals(54, pngImageCopy.getPixel(1, 1).getRed());
    assertEquals(54, pngImageCopy.getPixel(1, 1).getGreen());
    assertEquals(255, pngImageCopy.getPixel(1, 1).getBlue());
  }

  @Test
  public void testSaveExceptionChecking() {
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    //test that calling save on an image name that isn't in the model throws an exception
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> controller.save("testImage", "testImage.jpg"));
    //add an image to the model
    controller.load("test/modelTest/testImage.ppm", "testImage");
    //test that calling save on an image name that isn't in the model throws an exception
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> controller.save("tsetImage", "testImage.ppm"));
    //test that when the fileWriter throws an IOException when it cannot be created (in this case
    //because the given file name cannot be created as '/' is an invalid name) the correct exception
    //is thrown
    assertThrows("FileWriter could not be created.", IllegalStateException.class,
        () -> controller.save("testImage", "/.ppm"));
    //test that you cannot save to a file that doesn't end in one of the valid file formats
    assertThrows("Invalid file type.", IllegalArgumentException.class,
        () -> controller.save("testImage", "test/controllertest/hi"));
    assertThrows("Invalid file type.", IllegalArgumentException.class,
        () -> controller.save("testImage", "test/controllertest/output.txt"));
  }

  @Test
  public void testSavingPPMWorksCorrectly() {
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    controller.load("test/modelTest/testImage.ppm", "testImage");

    //test that saving a valid ppm file saves correctly by reloading the file into the model, and
    //checking that that image is the same as the original
    controller.save("testImage", "test/controllertest/output.ppm");
    controller.load("test/controllertest/output.ppm", "output");
    Image viewSaveCopy = model.getImageCopy("output");
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
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    controller.load("test/controllertest/testImage.bmp", "testImageBMP");
    controller.save("testImageBMP", "test/controllertest/output.bmp");
    controller.load("test/controllertest/output.bmp", "newTestImageBMP");
    Image bmpImage = model.getImageCopy("testImageBMP");
    Image reloadedBMPImage = model.getImageCopy("newTestImageBMP");
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

    controller.load("test/controllertest/testImage.png", "testImagePNG");
    controller.save("testImagePNG", "test/controllertest/output.png");
    controller.load("test/controllertest/output.png", "newTestImagePNG");
    Image pngImage = model.getImageCopy("testImagePNG");
    Image reloadedPNGImage = model.getImageCopy("newTestImagePNG");
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
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    controller.load("test/modeltest/testImage.ppm", "testImage");
    controller.save("testImage", "test/controllertest/output.jpg");
    controller.load("test/controllertest/output.jpg", "newTestImageJPG");

    Image jpgImage = model.getImageCopy("newTestImageJPG");
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

    controller.save("testImage", "test/controllertest/output.jpeg");
    controller.load("test/controllertest/output.jpeg", "newTestImageJPEG");
    Image jpegImage = model.getImageCopy("newTestImageJPEG");
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
  public void testRunSave() {
    IMEModel model1 = new IMEModelImpl();
    //show that not providing both an image name and a file name will throw an exception
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> new IMEControllerImpl(model1, new StringReader("save")).run());
    //show that not providing both an image name and a file name will throw an exception
    assertThrows("Scanner ran out of values.", IllegalStateException.class,
        () -> new IMEControllerImpl(model1, new StringReader("save testImage")).run());

    //show that calling save correctly saves the image correctly by reading the produced file, and
    //verifying that the image produced is the same as the original image
    Readable readable1 = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "save testImage test/controllertest/controllerSave.ppm " +
            "load test/controllertest/controllerSave.ppm controllerSave quit");
    IMEController controller1 = new IMEControllerImpl(model1, readable1);
    controller1.run();
    Image controllerSaveCopy = model1.getImageCopy("controllerSave");
    assertEquals(2, controllerSaveCopy.getWidth());
    assertEquals(2, controllerSaveCopy.getHeight());
    assertEquals(255, controllerSaveCopy.getMaxValue());
    assertEquals(243, controllerSaveCopy.getPixel(0, 0).getRed());
    assertEquals(32, controllerSaveCopy.getPixel(0, 0).getGreen());
    assertEquals(45, controllerSaveCopy.getPixel(0, 0).getBlue());
    assertEquals(78, controllerSaveCopy.getPixel(0, 1).getRed());
    assertEquals(132, controllerSaveCopy.getPixel(0, 1).getGreen());
    assertEquals(90, controllerSaveCopy.getPixel(0, 1).getBlue());
    assertEquals(20, controllerSaveCopy.getPixel(1, 0).getRed());
    assertEquals(240, controllerSaveCopy.getPixel(1, 0).getGreen());
    assertEquals(18, controllerSaveCopy.getPixel(1, 0).getBlue());
    assertEquals(54, controllerSaveCopy.getPixel(1, 1).getRed());
    assertEquals(54, controllerSaveCopy.getPixel(1, 1).getGreen());
    assertEquals(255, controllerSaveCopy.getPixel(1, 1).getBlue());

    //here, we show that you can load in an image of a certain file format, then save it as another
    //and still get the correct results
    IMEModel model2 = new IMEModelImpl();
    Readable readable2 = new StringReader("load test/controllertest/testImage.png testImage " +
            "save testImage test/controllertest/comboOutput.bmp " +
            "load test/controllertest/comboOutput.bmp reloadedTestImage " +
            "save reloadedTestImage test/controllertest/comboOutput.jpeg " +
            "load test/controllertest/comboOutput.jpeg reloadedAgainTestImage " +
            "save reloadedAgainTestImage test/controllertest/comboOutput.ppm " +
            "load test/controllertest/comboOutput.ppm reloadedAgainAgainTestImage quit");
    IMEController controller2 = new IMEControllerImpl(model2, readable2);
    controller2.run();
    Image originalImage = model2.getImageCopy("testImage");
    Image firstReload = model2.getImageCopy("reloadedTestImage");
    assertEquals(originalImage.getWidth(), firstReload.getWidth());
    assertEquals(originalImage.getHeight(), firstReload.getHeight());
    assertEquals(originalImage.getMaxValue(), firstReload.getMaxValue());
    assertEquals(originalImage.getPixel(0, 0).getRed(), firstReload.getPixel(0, 0).getRed());
    assertEquals(originalImage.getPixel(0, 0).getGreen(), firstReload.getPixel(0, 0).getGreen());
    assertEquals(originalImage.getPixel(0, 0).getBlue(), firstReload.getPixel(0, 0).getBlue());
    assertEquals(originalImage.getPixel(0, 1).getRed(), firstReload.getPixel(0, 1).getRed());
    assertEquals(originalImage.getPixel(0, 1).getGreen(), firstReload.getPixel(0, 1).getGreen());
    assertEquals(originalImage.getPixel(0, 1).getBlue(), firstReload.getPixel(0, 1).getBlue());
    assertEquals(originalImage.getPixel(1, 0).getRed(), firstReload.getPixel(1, 0).getRed());
    assertEquals(originalImage.getPixel(1, 0).getGreen(), firstReload.getPixel(1, 0).getGreen());
    assertEquals(originalImage.getPixel(1, 0).getBlue(), firstReload.getPixel(1, 0).getBlue());
    assertEquals(originalImage.getPixel(1, 1).getRed(), firstReload.getPixel(1, 1).getRed());
    assertEquals(originalImage.getPixel(1, 1).getGreen(), firstReload.getPixel(1, 1).getGreen());
    assertEquals(originalImage.getPixel(1, 1).getBlue(), firstReload.getPixel(1, 1).getBlue());

    //now, saving as a jpg will cause loss. The next two Images will be slightly different
    Image secondReload = model2.getImageCopy("reloadedAgainTestImage");
    assertEquals(2, secondReload.getWidth());
    assertEquals(2, secondReload.getHeight());
    assertEquals(255, secondReload.getMaxValue());
    assertEquals(103, secondReload.getPixel(0, 0).getRed());
    assertEquals(124, secondReload.getPixel(0, 0).getGreen());
    assertEquals(109, secondReload.getPixel(0, 0).getBlue());
    assertEquals(78, secondReload.getPixel(0, 1).getRed());
    assertEquals(99, secondReload.getPixel(0, 1).getGreen());
    assertEquals(84, secondReload.getPixel(0, 1).getBlue());
    assertEquals(115, secondReload.getPixel(1, 0).getRed());
    assertEquals(136, secondReload.getPixel(1, 0).getGreen());
    assertEquals(121, secondReload.getPixel(1, 0).getBlue());
    assertEquals(68, secondReload.getPixel(1, 1).getRed());
    assertEquals(89, secondReload.getPixel(1, 1).getGreen());
    assertEquals(74, secondReload.getPixel(1, 1).getBlue());

    Image thirdReload = model2.getImageCopy("reloadedAgainAgainTestImage");
    assertEquals(secondReload.getWidth(), thirdReload.getWidth());
    assertEquals(secondReload.getHeight(), thirdReload.getHeight());
    assertEquals(secondReload.getMaxValue(), thirdReload.getMaxValue());
    assertEquals(secondReload.getPixel(0, 0).getRed(), thirdReload.getPixel(0, 0).getRed());
    assertEquals(secondReload.getPixel(0, 0).getGreen(), thirdReload.getPixel(0, 0).getGreen());
    assertEquals(secondReload.getPixel(0, 0).getBlue(), thirdReload.getPixel(0, 0).getBlue());
    assertEquals(secondReload.getPixel(0, 1).getRed(), thirdReload.getPixel(0, 1).getRed());
    assertEquals(secondReload.getPixel(0, 1).getGreen(), thirdReload.getPixel(0, 1).getGreen());
    assertEquals(secondReload.getPixel(0, 1).getBlue(), thirdReload.getPixel(0, 1).getBlue());
    assertEquals(secondReload.getPixel(1, 0).getRed(), thirdReload.getPixel(1, 0).getRed());
    assertEquals(secondReload.getPixel(1, 0).getGreen(), thirdReload.getPixel(1, 0).getGreen());
    assertEquals(secondReload.getPixel(1, 0).getBlue(), thirdReload.getPixel(1, 0).getBlue());
    assertEquals(secondReload.getPixel(1, 1).getRed(), thirdReload.getPixel(1, 1).getRed());
    assertEquals(secondReload.getPixel(1, 1).getGreen(), thirdReload.getPixel(1, 1).getGreen());
    assertEquals(secondReload.getPixel(1, 1).getBlue(), thirdReload.getPixel(1, 1).getBlue());
  }
}