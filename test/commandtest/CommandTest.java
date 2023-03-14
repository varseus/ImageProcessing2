package commandtest;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import command.BlueGreyscaleCommand;
import command.BlurCommand;
import command.BrightenCommand;
import command.GreenGreyscaleCommand;
import command.GreyscaleColorTransformationCommand;
import command.HorizontalFlipCommand;
import command.IMECommand;
import command.IntensityGreyscaleCommand;
import command.LumaGreyscaleCommand;
import command.MosaicCommand;
import command.RedGreyscaleCommand;
import command.SepiaColorTransformationCommand;
import command.SharpenCommand;
import command.ValueGreyscaleCommand;
import command.VerticalFlipCommand;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A class containing tests for various IMECommand implementations.
 */
public class CommandTest {

  Image image;

  @Before
  public void initialize() {
    int maxValue = 255;
    Pixel pixel00 = new PixelImpl(243, 32, 45, maxValue);
    Pixel pixel01 = new PixelImpl(78, 132, 90, maxValue);
    Pixel pixel10 = new PixelImpl(20, 240, 18, maxValue);
    Pixel pixel11 = new PixelImpl(54, 54, 255, maxValue);
    Pixel[][] pixels = new Pixel[][]{
        new Pixel[]{pixel00, pixel01},
        new Pixel[]{pixel10, pixel11}};
    image = new ImageImpl(2, 2, maxValue, pixels);
  }

  @Test
  public void testRedGreyscaleCommand() {
    IMECommand redGreyscale = new RedGreyscaleCommand();
    Image output = redGreyscale.execute(image);
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
  public void testGreenGreyscaleCommand() {
    IMECommand greenGreyscale = new GreenGreyscaleCommand();
    Image output = greenGreyscale.execute(image);
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
  public void testBlueGreyscaleCommand() {
    IMECommand blueGreyscale = new BlueGreyscaleCommand();
    Image output = blueGreyscale.execute(image);
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
  public void testValueGreyscaleCommand() {
    IMECommand valueGreyscale = new ValueGreyscaleCommand();
    Image output = valueGreyscale.execute(image);
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
  public void testIntensityGreyscaleCommand() {
    IMECommand intensityGreyscale = new IntensityGreyscaleCommand();
    Image output = intensityGreyscale.execute(image);
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
  public void testLumaGreyscaleCommand() {
    IMECommand lumaGreyscale = new LumaGreyscaleCommand();
    Image output = lumaGreyscale.execute(image);
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
  public void testHorizontalFlipCommand() {
    IMECommand horizontalFlip = new HorizontalFlipCommand();
    Image output = horizontalFlip.execute(image);
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
  public void testVerticalFlipCommand() {
    IMECommand verticalFlip = new VerticalFlipCommand();
    Image output = verticalFlip.execute(image);
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
  public void testBrighten() {
    IMECommand brighten = new BrightenCommand(25);
    Image output1 = brighten.execute(image);
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

    IMECommand darken = new BrightenCommand(-85);
    Image output2 = darken.execute(image);
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
  public void testBlur() {
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
    image = new ImageImpl(3, 3, maxValue, pixels);

    IMECommand blur = new BlurCommand();
    Image output = blur.execute(image);
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
  public void testSharpen() {
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
    image = new ImageImpl(5, 5, maxValue, pixels);

    IMECommand sharpen = new SharpenCommand();
    Image output = sharpen.execute(image);

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
  public void testGreyscaleColorTransformationCommand() {
    IMECommand greyscaleColorTransformationCommand = new GreyscaleColorTransformationCommand();
    Image output = greyscaleColorTransformationCommand.execute(image);
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
  public void testSepiaColorTransformationCommand() {
    IMECommand sepiaColorTransformationCommand = new SepiaColorTransformationCommand();
    Image output = sepiaColorTransformationCommand.execute(image);
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
  public void testMosaicCommand() {
    IMECommand mosaicCommand = new MosaicCommand(3, new Random(1));
    Image output = mosaicCommand.execute(image);
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

    IMECommand mosaicCommand2 = new MosaicCommand(1, new Random(1));
    Image output2 = mosaicCommand2.execute(image);
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

  @Test (expected = IllegalArgumentException.class)
  public void testMosaicCommandNegSeeds() {
    IMECommand mosaicCommand = new MosaicCommand(0, new Random(1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMosaicCommandZeroSeeds() {
    IMECommand mosaicCommand = new MosaicCommand(-1, new Random(1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMosaicCommandTooManySeeds() {
    IMECommand mosaicCommand = new MosaicCommand(100, new Random(1));
    mosaicCommand.execute(image);
  }
}
