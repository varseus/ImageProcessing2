package controllertest;

import org.junit.Test;

import java.io.StringReader;
import java.util.Random;

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
 * A test class for testing the ControllerImplClass can run commands correctly.
 */
public class ControllerImplFunctionObjectTest {

  @Test
  public void testRunRedGreyscale() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("red-component")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("red-component testImage"))
                            .run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "red-component testImage red-greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("red-greyscale");
    assertEquals(243, output.getPixel(0, 0).getRed());
    assertEquals(243, output.getPixel(0, 0).getGreen());
    assertEquals(243, output.getPixel(0, 0).getBlue());
    assertEquals(78, output.getPixel(0, 1).getRed());
    assertEquals(78, output.getPixel(0, 1).getGreen());
    assertEquals(78, output.getPixel(0, 1).getBlue());
    assertEquals(20, output.getPixel(1, 0).getRed());
    assertEquals(20, output.getPixel(1, 0).getGreen());
    assertEquals(20, output.getPixel(1, 0).getBlue());
    assertEquals(54, output.getPixel(1, 1).getRed());
    assertEquals(54, output.getPixel(1, 1).getGreen());
    assertEquals(54, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunGreenGreyscale() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("green-component")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("green-component testImage"))
                            .run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "green-component testImage green-greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("green-greyscale");
    assertEquals(32, output.getPixel(0, 0).getRed());
    assertEquals(32, output.getPixel(0, 0).getGreen());
    assertEquals(32, output.getPixel(0, 0).getBlue());
    assertEquals(132, output.getPixel(0, 1).getRed());
    assertEquals(132, output.getPixel(0, 1).getGreen());
    assertEquals(132, output.getPixel(0, 1).getBlue());
    assertEquals(240, output.getPixel(1, 0).getRed());
    assertEquals(240, output.getPixel(1, 0).getGreen());
    assertEquals(240, output.getPixel(1, 0).getBlue());
    assertEquals(54, output.getPixel(1, 1).getRed());
    assertEquals(54, output.getPixel(1, 1).getGreen());
    assertEquals(54, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunBlueGreyscale() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("blue-component")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("blue-component testImage"))
                            .run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "blue-component testImage blue-greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("blue-greyscale");
    assertEquals(45, output.getPixel(0, 0).getRed());
    assertEquals(45, output.getPixel(0, 0).getGreen());
    assertEquals(45, output.getPixel(0, 0).getBlue());
    assertEquals(90, output.getPixel(0, 1).getRed());
    assertEquals(90, output.getPixel(0, 1).getGreen());
    assertEquals(90, output.getPixel(0, 1).getBlue());
    assertEquals(18, output.getPixel(1, 0).getRed());
    assertEquals(18, output.getPixel(1, 0).getGreen());
    assertEquals(18, output.getPixel(1, 0).getBlue());
    assertEquals(255, output.getPixel(1, 1).getRed());
    assertEquals(255, output.getPixel(1, 1).getGreen());
    assertEquals(255, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunValueGreyscale() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("value-component")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("value-component testImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "value-component testImage value-greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("value-greyscale");
    assertEquals(243, output.getPixel(0, 0).getRed());
    assertEquals(243, output.getPixel(0, 0).getGreen());
    assertEquals(243, output.getPixel(0, 0).getBlue());
    assertEquals(132, output.getPixel(0, 1).getRed());
    assertEquals(132, output.getPixel(0, 1).getGreen());
    assertEquals(132, output.getPixel(0, 1).getBlue());
    assertEquals(240, output.getPixel(1, 0).getRed());
    assertEquals(240, output.getPixel(1, 0).getGreen());
    assertEquals(240, output.getPixel(1, 0).getBlue());
    assertEquals(255, output.getPixel(1, 1).getRed());
    assertEquals(255, output.getPixel(1, 1).getGreen());
    assertEquals(255, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunIntensityGreyscale() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("intensity-component")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("intensity-component testImage"))
                            .run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "intensity-component testImage intensity-greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("intensity-greyscale");
    assertEquals(106, output.getPixel(0, 0).getRed());
    assertEquals(106, output.getPixel(0, 0).getGreen());
    assertEquals(106, output.getPixel(0, 0).getBlue());
    assertEquals(100, output.getPixel(0, 1).getRed());
    assertEquals(100, output.getPixel(0, 1).getGreen());
    assertEquals(100, output.getPixel(0, 1).getBlue());
    assertEquals(92, output.getPixel(1, 0).getRed());
    assertEquals(92, output.getPixel(1, 0).getGreen());
    assertEquals(92, output.getPixel(1, 0).getBlue());
    assertEquals(121, output.getPixel(1, 1).getRed());
    assertEquals(121, output.getPixel(1, 1).getGreen());
    assertEquals(121, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunLumaGreyscale() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("luma-component")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("luma-component testImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "luma-component testImage luma-greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("luma-greyscale");
    assertEquals(77, output.getPixel(0, 0).getRed());
    assertEquals(77, output.getPixel(0, 0).getGreen());
    assertEquals(77, output.getPixel(0, 0).getBlue());
    assertEquals(117, output.getPixel(0, 1).getRed());
    assertEquals(117, output.getPixel(0, 1).getGreen());
    assertEquals(117, output.getPixel(0, 1).getBlue());
    assertEquals(177, output.getPixel(1, 0).getRed());
    assertEquals(177, output.getPixel(1, 0).getGreen());
    assertEquals(177, output.getPixel(1, 0).getBlue());
    assertEquals(68, output.getPixel(1, 1).getRed());
    assertEquals(68, output.getPixel(1, 1).getGreen());
    assertEquals(68, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunHorizontalFlip() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("horizontal-flip")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("horizontal-flip testImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "horizontal-flip testImage flipped-horizontally quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("flipped-horizontally");
    assertEquals(78, output.getPixel(0, 0).getRed());
    assertEquals(132, output.getPixel(0, 0).getGreen());
    assertEquals(90, output.getPixel(0, 0).getBlue());
    assertEquals(243, output.getPixel(0, 1).getRed());
    assertEquals(32, output.getPixel(0, 1).getGreen());
    assertEquals(45, output.getPixel(0, 1).getBlue());
    assertEquals(54, output.getPixel(1, 0).getRed());
    assertEquals(54, output.getPixel(1, 0).getGreen());
    assertEquals(255, output.getPixel(1, 0).getBlue());
    assertEquals(20, output.getPixel(1, 1).getRed());
    assertEquals(240, output.getPixel(1, 1).getGreen());
    assertEquals(18, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunVerticalFlip() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("vertical-flip")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("vertical-flip testImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "vertical-flip testImage flipped-vertically quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("flipped-vertically");
    assertEquals(20, output.getPixel(0, 0).getRed());
    assertEquals(240, output.getPixel(0, 0).getGreen());
    assertEquals(18, output.getPixel(0, 0).getBlue());
    assertEquals(54, output.getPixel(0, 1).getRed());
    assertEquals(54, output.getPixel(0, 1).getGreen());
    assertEquals(255, output.getPixel(0, 1).getBlue());
    assertEquals(243, output.getPixel(1, 0).getRed());
    assertEquals(32, output.getPixel(1, 0).getGreen());
    assertEquals(45, output.getPixel(1, 0).getBlue());
    assertEquals(78, output.getPixel(1, 1).getRed());
    assertEquals(132, output.getPixel(1, 1).getGreen());
    assertEquals(90, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunBrighten() {
    IMEModel model = new IMEModelImpl();
    //show that not providing an int value, as well as an old and new image name will throw an
    //exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("brighten")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("brighten 25")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("brighten 25 testImage")).run());
    //show that not providing the brightness value as an int throws an exception
    assertThrows("Value is not an integer.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("brighten three testImage brightImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "brighten 25 testImage brightImage brighten -85 testImage darkImage quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();

    Image output1 = model.getImageCopy("brightImage");
    assertEquals(255, output1.getPixel(0, 0).getRed());
    assertEquals(57, output1.getPixel(0, 0).getGreen());
    assertEquals(70, output1.getPixel(0, 0).getBlue());
    assertEquals(103, output1.getPixel(0, 1).getRed());
    assertEquals(157, output1.getPixel(0, 1).getGreen());
    assertEquals(115, output1.getPixel(0, 1).getBlue());
    assertEquals(45, output1.getPixel(1, 0).getRed());
    assertEquals(255, output1.getPixel(1, 0).getGreen());
    assertEquals(43, output1.getPixel(1, 0).getBlue());
    assertEquals(79, output1.getPixel(1, 1).getRed());
    assertEquals(79, output1.getPixel(1, 1).getGreen());
    assertEquals(255, output1.getPixel(1, 1).getBlue());

    Image output2 = model.getImageCopy("darkImage");
    assertEquals(158, output2.getPixel(0, 0).getRed());
    assertEquals(0, output2.getPixel(0, 0).getGreen());
    assertEquals(0, output2.getPixel(0, 0).getBlue());
    assertEquals(0, output2.getPixel(0, 1).getRed());
    assertEquals(47, output2.getPixel(0, 1).getGreen());
    assertEquals(5, output2.getPixel(0, 1).getBlue());
    assertEquals(0, output2.getPixel(1, 0).getRed());
    assertEquals(155, output2.getPixel(1, 0).getGreen());
    assertEquals(0, output2.getPixel(1, 0).getBlue());
    assertEquals(0, output2.getPixel(1, 1).getRed());
    assertEquals(0, output2.getPixel(1, 1).getGreen());
    assertEquals(170, output2.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunBlur() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("blur")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("blur testImage")).run());

    int maxValue = 255;
    Pixel pixel00 = new PixelImpl(243, 32, 45, maxValue);
    Pixel pixel01 = new PixelImpl(78, 132, 90, maxValue);
    Pixel pixel02 = new PixelImpl(30, 120, 255, maxValue);
    Pixel pixel10 = new PixelImpl(20, 240, 18, maxValue);
    Pixel pixel11 = new PixelImpl(54, 54, 255, maxValue);
    Pixel pixel12 = new PixelImpl(60, 40, 100, maxValue);
    Pixel pixel20 = new PixelImpl(5, 74, 149, maxValue);
    Pixel pixel21 = new PixelImpl(98, 36, 11, maxValue);
    Pixel pixel22 = new PixelImpl(187, 186, 3, maxValue);
    Pixel[][] pixels = new Pixel[][]{
      new Pixel[]{pixel00, pixel01, pixel02},
      new Pixel[]{pixel10, pixel11, pixel12},
      new Pixel[]{pixel20, pixel21, pixel22}};
    Image image = new ImageImpl(3, 3, maxValue, pixels);

    model.addToModel("testImage", image);
    Readable in = new StringReader("blur testImage blur quit");
    IMEController controller = new IMEControllerImpl(model, in);
    controller.run();
    Image output = model.getImageCopy("blur");
    assertEquals(76, output.getPixel(0, 0).getRed());
    assertEquals(57, output.getPixel(0, 0).getGreen());
    assertEquals(40, output.getPixel(0, 0).getBlue());
    assertEquals(65, output.getPixel(0, 1).getRed());
    assertEquals(76, output.getPixel(0, 1).getGreen());
    assertEquals(99, output.getPixel(0, 1).getBlue());
    assertEquals(28, output.getPixel(0, 2).getRed());
    assertEquals(54, output.getPixel(0, 2).getGreen());
    assertEquals(103, output.getPixel(0, 2).getBlue());
    assertEquals(53, output.getPixel(1, 0).getRed());
    assertEquals(90, output.getPixel(1, 0).getGreen());
    assertEquals(66, output.getPixel(1, 0).getBlue());
    assertEquals(74, output.getPixel(1, 1).getRed());
    assertEquals(95, output.getPixel(1, 1).getGreen());
    assertEquals(119, output.getPixel(1, 1).getBlue());
    assertEquals(59, output.getPixel(1, 2).getRed());
    assertEquals(65, output.getPixel(1, 2).getGreen());
    assertEquals(30, output.getPixel(2, 2).getBlue());
    assertEquals(19, output.getPixel(2, 0).getRed());
    assertEquals(56, output.getPixel(2, 0).getGreen());
    assertEquals(56, output.getPixel(2, 0).getBlue());
    assertEquals(60, output.getPixel(2, 1).getRed());
    assertEquals(65, output.getPixel(2, 1).getGreen());
    assertEquals(61, output.getPixel(2, 1).getBlue());
    assertEquals(69, output.getPixel(2, 2).getRed());
    assertEquals(59, output.getPixel(2, 2).getGreen());
    assertEquals(30, output.getPixel(2, 2).getBlue());
  }

  @Test
  public void testRunSharpen() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("sharpen")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("sharpen testImage")).run());

    int maxValue = 255;
    Pixel pixel00 = new PixelImpl(243, 32, 45, maxValue);
    Pixel pixel01 = new PixelImpl(78, 132, 90, maxValue);
    Pixel pixel02 = new PixelImpl(30, 120, 255, maxValue);
    Pixel pixel03 = new PixelImpl(95, 94, 172, maxValue);
    Pixel pixel04 = new PixelImpl(121, 148, 70, maxValue);
    Pixel pixel10 = new PixelImpl(20, 240, 18, maxValue);
    Pixel pixel11 = new PixelImpl(54, 54, 255, maxValue);
    Pixel pixel12 = new PixelImpl(60, 40, 100, maxValue);
    Pixel pixel13 = new PixelImpl(208, 91, 201, maxValue);
    Pixel pixel14 = new PixelImpl(171, 181, 186, maxValue);
    Pixel pixel20 = new PixelImpl(5, 74, 149, maxValue);
    Pixel pixel21 = new PixelImpl(98, 36, 11, maxValue);
    Pixel pixel22 = new PixelImpl(187, 186, 3, maxValue);
    Pixel pixel23 = new PixelImpl(88, 107, 114, maxValue);
    Pixel pixel24 = new PixelImpl(192, 26, 229, maxValue);
    Pixel pixel30 = new PixelImpl(191, 228, 245, maxValue);
    Pixel pixel31 = new PixelImpl(199, 48, 42, maxValue);
    Pixel pixel32 = new PixelImpl(136, 102, 183, maxValue);
    Pixel pixel33 = new PixelImpl(189, 94, 177, maxValue);
    Pixel pixel34 = new PixelImpl(168, 120, 28, maxValue);
    Pixel pixel40 = new PixelImpl(64, 211, 100, maxValue);
    Pixel pixel41 = new PixelImpl(189, 137, 173, maxValue);
    Pixel pixel42 = new PixelImpl(77, 232, 251, maxValue);
    Pixel pixel43 = new PixelImpl(253, 66, 180, maxValue);
    Pixel pixel44 = new PixelImpl(253, 67, 0, maxValue);

    Pixel[][] pixels = new Pixel[][]{
      new Pixel[]{pixel00, pixel01, pixel02, pixel03, pixel04},
      new Pixel[]{pixel10, pixel11, pixel12, pixel13, pixel14},
      new Pixel[]{pixel20, pixel21, pixel22, pixel23, pixel24},
      new Pixel[]{pixel30, pixel31, pixel32, pixel33, pixel34},
      new Pixel[]{pixel40, pixel41, pixel42, pixel43, pixel44}};
    Image image = new ImageImpl(5, 5, maxValue, pixels);

    model.addToModel("testImage", image);
    Readable in = new StringReader("sharpen testImage sharpen quit");
    IMEController controller = new IMEControllerImpl(model, in);
    controller.run();
    Image output = model.getImageCopy("sharpen");

    //we are not going to test every single pixel - just a select few that illustrate the method
    //works
    assertEquals(233, output.getPixel(0, 0).getRed());
    assertEquals(81, output.getPixel(0, 0).getGreen());
    assertEquals(71, output.getPixel(0, 0).getBlue());
    assertEquals(169, output.getPixel(0, 4).getRed());
    assertEquals(179, output.getPixel(0, 4).getGreen());
    assertEquals(122, output.getPixel(0, 4).getBlue());
    assertEquals(176, output.getPixel(2, 2).getRed());
    assertEquals(65, output.getPixel(2, 2).getGreen());
    assertEquals(0, output.getPixel(2, 2).getBlue());
    assertEquals(145, output.getPixel(4, 0).getRed());
    assertEquals(235, output.getPixel(4, 0).getGreen());
    assertEquals(140, output.getPixel(4, 0).getBlue());
    assertEquals(255, output.getPixel(4, 4).getRed());
    assertEquals(55, output.getPixel(4, 4).getGreen());
    assertEquals(0, output.getPixel(4, 4).getBlue());
    assertEquals(94, output.getPixel(0, 1).getRed());
    assertEquals(180, output.getPixel(0, 1).getGreen());
    assertEquals(177, output.getPixel(0, 1).getBlue());
    assertEquals(96, output.getPixel(1, 1).getRed());
    assertEquals(173, output.getPixel(1, 1).getGreen());
    assertEquals(255, output.getPixel(1, 1).getBlue());
    assertEquals(234, output.getPixel(2, 3).getRed());
    assertEquals(175, output.getPixel(2, 3).getGreen());
    assertEquals(203, output.getPixel(2, 3).getBlue());
    assertEquals(255, output.getPixel(3, 1).getRed());
    assertEquals(255, output.getPixel(3, 1).getGreen());
    assertEquals(190, output.getPixel(3, 1).getBlue());
    assertEquals(162, output.getPixel(4, 2).getRed());
    assertEquals(211, output.getPixel(4, 2).getGreen());
    assertEquals(255, output.getPixel(4, 2).getBlue());
  }

  @Test
  public void testRunGreyscaleColorTransformation() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("greyscale")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("greyscale testImage"))
                            .run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "greyscale testImage greyscale quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("greyscale");
    assertEquals(77, output.getPixel(0, 0).getRed());
    assertEquals(77, output.getPixel(0, 0).getGreen());
    assertEquals(77, output.getPixel(0, 0).getBlue());
    assertEquals(117, output.getPixel(0, 1).getRed());
    assertEquals(117, output.getPixel(0, 1).getGreen());
    assertEquals(117, output.getPixel(0, 1).getBlue());
    assertEquals(177, output.getPixel(1, 0).getRed());
    assertEquals(177, output.getPixel(1, 0).getGreen());
    assertEquals(177, output.getPixel(1, 0).getBlue());
    assertEquals(68, output.getPixel(1, 1).getRed());
    assertEquals(68, output.getPixel(1, 1).getGreen());
    assertEquals(68, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunSepiaColorTransformation() {
    IMEModel model = new IMEModelImpl();
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("sepia")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("sepia testImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "sepia testImage sepia quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image output = model.getImageCopy("sepia");
    assertEquals(128, output.getPixel(0, 0).getRed());
    assertEquals(114, output.getPixel(0, 0).getGreen());
    assertEquals(89, output.getPixel(0, 0).getBlue());
    assertEquals(149, output.getPixel(0, 1).getRed());
    assertEquals(132, output.getPixel(0, 1).getGreen());
    assertEquals(103, output.getPixel(0, 1).getBlue());
    assertEquals(195, output.getPixel(1, 0).getRed());
    assertEquals(174, output.getPixel(1, 0).getGreen());
    assertEquals(135, output.getPixel(1, 0).getBlue());
    assertEquals(110, output.getPixel(1, 1).getRed());
    assertEquals(98, output.getPixel(1, 1).getGreen());
    assertEquals(76, output.getPixel(1, 1).getBlue());
  }

  @Test
  public void testRunCompoundCommand() {
    //this test verifies that the controller is able to handle a series of inputs at once, and that
    //if a command is applied to an image, another command can be applied to the produced image
    //it also demonstrates that images in the model can be overwritten by giving the new image name
    //as an existing image name

    //first, the 2x2 test image is loaded in. Then, a red greyscale command is applied to it.
    //then, the red greyscale is flipped horizontally.
    //then, a blue greyscale command is applied to the horizontally flipped red greyscale (this
    //should do nothing)
    //then, the original red greyscale is brightened and set to the original test image's name
    IMEModel model = new IMEModelImpl();
    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "red-component testImage redImage " +
            "horizontal-flip redImage redHorizImage " +
            "blue-component redHorizImage redBlueHorizImage " +
            "brighten 50 redImage testImage quit");
    IMEController controller = new IMEControllerImpl(model, readable);
    controller.run();
    Image redImage = model.getImageCopy("redImage");
    assertEquals(243, redImage.getPixel(0, 0).getRed());
    assertEquals(243, redImage.getPixel(0, 0).getGreen());
    assertEquals(243, redImage.getPixel(0, 0).getBlue());
    assertEquals(78, redImage.getPixel(0, 1).getRed());
    assertEquals(78, redImage.getPixel(0, 1).getGreen());
    assertEquals(78, redImage.getPixel(0, 1).getBlue());
    assertEquals(20, redImage.getPixel(1, 0).getRed());
    assertEquals(20, redImage.getPixel(1, 0).getGreen());
    assertEquals(20, redImage.getPixel(1, 0).getBlue());
    assertEquals(54, redImage.getPixel(1, 1).getRed());
    assertEquals(54, redImage.getPixel(1, 1).getGreen());
    assertEquals(54, redImage.getPixel(1, 1).getBlue());

    Image redHorizImage = model.getImageCopy("redHorizImage");
    assertEquals(78, redHorizImage.getPixel(0, 0).getRed());
    assertEquals(78, redHorizImage.getPixel(0, 0).getGreen());
    assertEquals(78, redHorizImage.getPixel(0, 0).getBlue());
    assertEquals(243, redHorizImage.getPixel(0, 1).getRed());
    assertEquals(243, redHorizImage.getPixel(0, 1).getGreen());
    assertEquals(243, redHorizImage.getPixel(0, 1).getBlue());
    assertEquals(54, redHorizImage.getPixel(1, 0).getRed());
    assertEquals(54, redHorizImage.getPixel(1, 0).getGreen());
    assertEquals(54, redHorizImage.getPixel(1, 0).getBlue());
    assertEquals(20, redHorizImage.getPixel(1, 1).getRed());
    assertEquals(20, redHorizImage.getPixel(1, 1).getGreen());
    assertEquals(20, redHorizImage.getPixel(1, 1).getBlue());

    Image redBlueHorizImage = model.getImageCopy("redBlueHorizImage");
    assertEquals(78, redBlueHorizImage.getPixel(0, 0).getRed());
    assertEquals(78, redBlueHorizImage.getPixel(0, 0).getGreen());
    assertEquals(78, redBlueHorizImage.getPixel(0, 0).getBlue());
    assertEquals(243, redBlueHorizImage.getPixel(0, 1).getRed());
    assertEquals(243, redBlueHorizImage.getPixel(0, 1).getGreen());
    assertEquals(243, redBlueHorizImage.getPixel(0, 1).getBlue());
    assertEquals(54, redBlueHorizImage.getPixel(1, 0).getRed());
    assertEquals(54, redBlueHorizImage.getPixel(1, 0).getGreen());
    assertEquals(54, redBlueHorizImage.getPixel(1, 0).getBlue());
    assertEquals(20, redBlueHorizImage.getPixel(1, 1).getRed());
    assertEquals(20, redBlueHorizImage.getPixel(1, 1).getGreen());
    assertEquals(20, redBlueHorizImage.getPixel(1, 1).getBlue());

    Image testImageAFTERMODIFICATION = model.getImageCopy("testImage");
    assertEquals(255, testImageAFTERMODIFICATION.getPixel(0, 0).getRed());
    assertEquals(255, testImageAFTERMODIFICATION.getPixel(0, 0).getGreen());
    assertEquals(255, testImageAFTERMODIFICATION.getPixel(0, 0).getBlue());
    assertEquals(128, testImageAFTERMODIFICATION.getPixel(0, 1).getRed());
    assertEquals(128, testImageAFTERMODIFICATION.getPixel(0, 1).getGreen());
    assertEquals(128, testImageAFTERMODIFICATION.getPixel(0, 1).getBlue());
    assertEquals(70, testImageAFTERMODIFICATION.getPixel(1, 0).getRed());
    assertEquals(70, testImageAFTERMODIFICATION.getPixel(1, 0).getGreen());
    assertEquals(70, testImageAFTERMODIFICATION.getPixel(1, 0).getBlue());
    assertEquals(104, testImageAFTERMODIFICATION.getPixel(1, 1).getRed());
    assertEquals(104, testImageAFTERMODIFICATION.getPixel(1, 1).getGreen());
    assertEquals(104, testImageAFTERMODIFICATION.getPixel(1, 1).getBlue());
  }


  @Test
  public void testRunMosaic() {
    IMEModel model = new IMEModelImpl();
    //show that not providing an int value, as well as an old and new image name will throw an
    //exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("mosaic")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("mosaic 1")).run());
    //show that not providing both an old and new image name will throw an exception
    assertThrows("Scanner ran out of values.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model, new StringReader("mosaic 1 testImage")).run());
    //show that not providing the brightness value as an int throws an exception
    assertThrows("Value is not an integer.",
            IllegalStateException.class, () ->
                    new IMEControllerImpl(model,
                            new StringReader("mosaic one testImage brightImage")).run());

    Readable readable = new StringReader("load test/modelTest/testImage.ppm testImage " +
            "mosaic 3 testImage mosaicImage mosaic 1 testImage mosaicImage2 quit");
    IMEController controller = new IMEControllerImpl(model, readable, new Random(1));
    controller.run();

    Image output = model.getImageCopy("mosaicImage");
    assertEquals(243, output.getPixel(0, 0).getRed());
    assertEquals(32, output.getPixel(0, 0).getGreen());
    assertEquals(45, output.getPixel(0, 0).getBlue());
    assertEquals(78, output.getPixel(0, 1).getRed());
    assertEquals(132, output.getPixel(0, 1).getGreen());
    assertEquals(90, output.getPixel(0, 1).getBlue());
    assertEquals(37, output.getPixel(1, 0).getRed());
    assertEquals(147, output.getPixel(1, 0).getGreen());
    assertEquals(136, output.getPixel(1, 0).getBlue());
    assertEquals(37, output.getPixel(1, 1).getRed());
    assertEquals(147, output.getPixel(1, 1).getGreen());
    assertEquals(136, output.getPixel(1, 1).getBlue());

    Image output2 = model.getImageCopy("mosaicImage2");
    assertEquals(98, output2.getPixel(0, 0).getRed());
    assertEquals(114, output2.getPixel(0, 0).getGreen());
    assertEquals(102, output2.getPixel(0, 0).getBlue());
    assertEquals(98, output2.getPixel(0, 1).getRed());
    assertEquals(114, output2.getPixel(0, 1).getGreen());
    assertEquals(102, output2.getPixel(0, 1).getBlue());
    assertEquals(98, output2.getPixel(1, 0).getRed());
    assertEquals(114, output2.getPixel(1, 0).getGreen());
    assertEquals(102, output2.getPixel(1, 0).getBlue());
    assertEquals(98, output2.getPixel(1, 1).getRed());
    assertEquals(114, output2.getPixel(1, 1).getGreen());
    assertEquals(102, output2.getPixel(1, 1).getBlue());
  }
}