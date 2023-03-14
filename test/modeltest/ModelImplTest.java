package modeltest;

import org.junit.Test;

import java.io.StringReader;

import command.BrightenCommand;
import command.HorizontalFlipCommand;
import command.RedGreyscaleCommand;
import controller.IMEController;
import controller.IMEControllerImpl;
import model.IMEModel;
import model.IMEModelImpl;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class containing tests for the ModelImpl class.
 */
public class ModelImplTest {

  @Test
  public void testProcess() {
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    //calling process on an image name that isn't in the model throws an exception
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> model.process(new RedGreyscaleCommand(), "testImage", "output"));
    //calling process on an image name that isn't in the model throws an exception
    controller.load("test/modelTest/testImage.ppm", "testImage");
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> model.process(new RedGreyscaleCommand(), "tsetImage", "output"));
    //calling process with a null command throws an exception
    assertThrows("Command cannot be null.", IllegalArgumentException.class,
        () -> model.process(null, "testImage", "output"));

    //test calling process with a variety of IMECommand produces the desired outputs. we do not
    //test every command here as it is unnecessary to do so, as the model only executes the given
    //command and puts the output in the model
    model.process(new RedGreyscaleCommand(), "testImage", "redGreyscale");
    Image redGreyscaleCopy = model.getImageCopy("redGreyscale");
    assertEquals(243, redGreyscaleCopy.getPixel(0, 0).getRed());
    assertEquals(243, redGreyscaleCopy.getPixel(0, 0).getGreen());
    assertEquals(243, redGreyscaleCopy.getPixel(0, 0).getBlue());
    assertEquals(78, redGreyscaleCopy.getPixel(0, 1).getRed());
    assertEquals(78, redGreyscaleCopy.getPixel(0, 1).getGreen());
    assertEquals(78, redGreyscaleCopy.getPixel(0, 1).getBlue());
    assertEquals(20, redGreyscaleCopy.getPixel(1, 0).getRed());
    assertEquals(20, redGreyscaleCopy.getPixel(1, 0).getGreen());
    assertEquals(20, redGreyscaleCopy.getPixel(1, 0).getBlue());
    assertEquals(54, redGreyscaleCopy.getPixel(1, 1).getRed());
    assertEquals(54, redGreyscaleCopy.getPixel(1, 1).getGreen());
    assertEquals(54, redGreyscaleCopy.getPixel(1, 1).getBlue());

    model.process(new BrightenCommand(50), "redGreyscale", "brightRedGreyscale");
    Image brightRedGreyscaleCopy = model.getImageCopy("brightRedGreyscale");
    assertEquals(255, brightRedGreyscaleCopy.getPixel(0, 0).getRed());
    assertEquals(255, brightRedGreyscaleCopy.getPixel(0, 0).getGreen());
    assertEquals(255, brightRedGreyscaleCopy.getPixel(0, 0).getBlue());
    assertEquals(128, brightRedGreyscaleCopy.getPixel(0, 1).getRed());
    assertEquals(128, brightRedGreyscaleCopy.getPixel(0, 1).getGreen());
    assertEquals(128, brightRedGreyscaleCopy.getPixel(0, 1).getBlue());
    assertEquals(70, brightRedGreyscaleCopy.getPixel(1, 0).getRed());
    assertEquals(70, brightRedGreyscaleCopy.getPixel(1, 0).getGreen());
    assertEquals(70, brightRedGreyscaleCopy.getPixel(1, 0).getBlue());
    assertEquals(104, brightRedGreyscaleCopy.getPixel(1, 1).getRed());
    assertEquals(104, brightRedGreyscaleCopy.getPixel(1, 1).getGreen());
    assertEquals(104, brightRedGreyscaleCopy.getPixel(1, 1).getBlue());

    model.process(new HorizontalFlipCommand(), "brightRedGreyscale", "brightRedGreyscale");
    Image newBrightRedGreyscaleCopy = model.getImageCopy("brightRedGreyscale");
    assertEquals(128, newBrightRedGreyscaleCopy.getPixel(0, 0).getRed());
    assertEquals(128, newBrightRedGreyscaleCopy.getPixel(0, 0).getGreen());
    assertEquals(128, newBrightRedGreyscaleCopy.getPixel(0, 0).getBlue());
    assertEquals(255, newBrightRedGreyscaleCopy.getPixel(0, 1).getRed());
    assertEquals(255, newBrightRedGreyscaleCopy.getPixel(0, 1).getGreen());
    assertEquals(255, newBrightRedGreyscaleCopy.getPixel(0, 1).getBlue());
    assertEquals(104, newBrightRedGreyscaleCopy.getPixel(1, 0).getRed());
    assertEquals(104, newBrightRedGreyscaleCopy.getPixel(1, 0).getGreen());
    assertEquals(104, newBrightRedGreyscaleCopy.getPixel(1, 0).getBlue());
    assertEquals(70, newBrightRedGreyscaleCopy.getPixel(1, 1).getRed());
    assertEquals(70, newBrightRedGreyscaleCopy.getPixel(1, 1).getGreen());
    assertEquals(70, newBrightRedGreyscaleCopy.getPixel(1, 1).getBlue());
  }

  @Test
  public void testAddToModel() {
    IMEModel model = new IMEModelImpl();
    //show that giving addToModel a null Image throws an exception
    assertThrows("Image cannot be null.", IllegalArgumentException.class,
        () -> model.addToModel("nullImage", null));

    //show that giving addToModel a valid Image puts that Image in the model at the right name
    Pixel pixel = new PixelImpl(213, 54, 123, 255);
    Image image = new ImageImpl(1, 1, 255,
            new Pixel[][]{new Pixel[]{pixel}});
    model.addToModel("imageName", image);
    Image imageCopy = model.getImageCopy("imageName");
    assertEquals(1, imageCopy.getWidth());
    assertEquals(1, imageCopy.getHeight());
    assertEquals(255, imageCopy.getMaxValue());
    assertEquals(pixel, imageCopy.getPixel(0, 0));
  }

  @Test
  public void getImageCopy() {
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model, new StringReader(""));
    //test calling getImageCopy on an image name that isn't in the model throws an exception
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> model.getImageCopy("testImage"));
    //test calling getImageCopy on an image name that isn't in the model throws an exception
    controller.load("test/modelTest/testImage.ppm", "testImage");
    assertThrows("File not found.", IllegalArgumentException.class,
        () -> model.getImageCopy("tsetImage"));

    //test calling getImageCopy on a valid image name gives the correct copy
    Image testImageCopy = model.getImageCopy("testImage");
    assertEquals(2, testImageCopy.getWidth());
    assertEquals(2, testImageCopy.getHeight());
    assertEquals(255, testImageCopy.getMaxValue());
    assertEquals(243, testImageCopy.getPixel(0, 0).getRed());
    assertEquals(32, testImageCopy.getPixel(0, 0).getGreen());
    assertEquals(45, testImageCopy.getPixel(0, 0).getBlue());
    assertEquals(78, testImageCopy.getPixel(0, 1).getRed());
    assertEquals(132, testImageCopy.getPixel(0, 1).getGreen());
    assertEquals(90, testImageCopy.getPixel(0, 1).getBlue());
    assertEquals(20, testImageCopy.getPixel(1, 0).getRed());
    assertEquals(240, testImageCopy.getPixel(1, 0).getGreen());
    assertEquals(18, testImageCopy.getPixel(1, 0).getBlue());
    assertEquals(54, testImageCopy.getPixel(1, 1).getRed());
    assertEquals(54, testImageCopy.getPixel(1, 1).getGreen());
    assertEquals(255, testImageCopy.getPixel(1, 1).getBlue());
  }
}