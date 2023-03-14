package featurestest;

import org.junit.Test;

import java.io.IOException;

import command.BlueGreyscaleCommand;
import command.BlurCommand;
import command.BrightenCommand;
import command.GreenGreyscaleCommand;
import command.GreyscaleColorTransformationCommand;
import command.HorizontalFlipCommand;
import command.IntensityGreyscaleCommand;
import command.LumaGreyscaleCommand;
import command.MosaicCommand;
import command.RedGreyscaleCommand;
import command.SepiaColorTransformationCommand;
import command.SharpenCommand;
import command.ValueGreyscaleCommand;
import command.VerticalFlipCommand;
import controller.GUIController;
import controller.Features;
import model.IMEModel;
import model.IMEModelImpl;
import model.Image;
import model.Pixel;
import utils.IOUtils;
import view.IMEView;
import view.JFrameView;
import view.MockView;
import view.MockViewInvalidInput;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test class for the methods of BetterController.
 */
public class GUIControllerTest {
  @Test
  public void testConstructor() {
    assertThrows("Parameters cannot be null.", IllegalArgumentException.class,
        () -> new GUIController(null, new JFrameView()));
    assertThrows("Parameters cannot be null.", IllegalArgumentException.class,
        () -> new GUIController(new IMEModelImpl(), null));

    Appendable stringBuilder = new StringBuilder();
    Features controller = new GUIController(new IMEModelImpl(),
            new MockView(stringBuilder, null));
    assertEquals(controller + " received by view.", stringBuilder.toString());
  }

  @Test
  public void testLoad() throws IOException {
    Appendable stringBuilder = new StringBuilder();
    Appendable ourStringBuilder = new StringBuilder();
    IMEModel model = new IMEModelImpl();
    Image[] imageArray = new Image[1];
    IMEView view = new MockView(stringBuilder, imageArray);
    Features controller = new GUIController(model, view);
    ourStringBuilder.append(controller + " received by view.");

    //show that loading an invalid file path/file will give the view the correct error message
    controller.load("test/modelTest/notRealFile.ppm");
    ourStringBuilder.append("Invalid file name or path.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.load("test/modelTest/testTXT.txt");
    ourStringBuilder.append("Invalid file type.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.load("test/modelTest/testImage2.ppm");
    ourStringBuilder.append("Invalid PPM file: plain RAW file should begin with P3");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.load("test/modelTest/InvalidRed.ppm");
    ourStringBuilder.append("Value is not an integer.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.load("test/controllertest/brokenJPG.jpg");
    ourStringBuilder.append("Unable to create FileInputStream.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    //show that loading a valid image gives the image to the model under the name "im", and show
    //that the image given to the model and the view is the correct Image object
    controller.load("test/modeltest/testImage.ppm");
    Image expectedImage = IOUtils.load("test/modeltest/testImage.ppm");
    Image modelImage = model.getImageCopy("im");
    Image viewImage = imageArray[0];

    assertEquals(expectedImage.getWidth(), modelImage.getWidth());
    assertEquals(expectedImage.getWidth(), viewImage.getWidth());
    assertEquals(expectedImage.getHeight(), modelImage.getHeight());
    assertEquals(expectedImage.getHeight(), viewImage.getHeight());
    assertEquals(expectedImage.getMaxValue(), modelImage.getMaxValue());
    assertEquals(expectedImage.getMaxValue(), viewImage.getMaxValue());
    for (int i = 0; i < expectedImage.getHeight(); i++) {
      for (int j = 0; j < expectedImage.getWidth(); j++) {
        Pixel expectedPixel = expectedImage.getPixel(i, j);
        assertEquals(expectedPixel.getRed(), modelImage.getPixel(i, j).getRed());
        assertEquals(expectedPixel.getGreen(), modelImage.getPixel(i, j).getGreen());
        assertEquals(expectedPixel.getBlue(), modelImage.getPixel(i, j).getBlue());

        assertEquals(expectedPixel.getRed(), viewImage.getPixel(i, j).getRed());
        assertEquals(expectedPixel.getGreen(), viewImage.getPixel(i, j).getGreen());
        assertEquals(expectedPixel.getBlue(), viewImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testSave() throws IOException {
    Appendable stringBuilder = new StringBuilder();
    Appendable ourStringBuilder = new StringBuilder();
    IMEModel model = new IMEModelImpl();
    IMEView view = new MockView(stringBuilder, new Image[1]);
    Features controller = new GUIController(model, view);
    ourStringBuilder.append(controller + " received by view.");

    controller.save("testImage.ppm");
    ourStringBuilder.append("Cannot save without loading an image first.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.load("test/modeltest/testImage.ppm");

    controller.save("/.ppm");
    ourStringBuilder.append("FileWriter could not be created.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.save("hello");
    ourStringBuilder.append("Invalid file type.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    //show that actually saving to a valid file path works as intended, by loading the image back
    //in and comparing to the original image
    controller.save("test/featurestest/savetest.png");
    Image originalImage = model.getImageCopy("im");
    Image reloadedImage = IOUtils.load("test/featurestest/savetest.png");

    assertEquals(originalImage.getWidth(), reloadedImage.getWidth());
    assertEquals(originalImage.getHeight(), reloadedImage.getHeight());
    assertEquals(originalImage.getMaxValue(), reloadedImage.getMaxValue());
    for (int i = 0; i < originalImage.getHeight(); i++) {
      for (int j = 0; j < originalImage.getWidth(); j++) {
        Pixel expectedPixel = originalImage.getPixel(i, j);
        assertEquals(expectedPixel.getRed(), reloadedImage.getPixel(i, j).getRed());
        assertEquals(expectedPixel.getGreen(), reloadedImage.getPixel(i, j).getGreen());
        assertEquals(expectedPixel.getBlue(), reloadedImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testSetView() {
    Appendable log = new StringBuilder();
    Features controller = new GUIController(new IMEModelImpl(), new MockView(log, null));
    controller.setView();
    assertEquals(controller + " received by view." + controller + " received by view.",
            log.toString());
  }

  @Test
  public void testProcessCommandErrorChecking() throws IOException {
    IMEModel model = new IMEModelImpl();
    Appendable stringBuilder = new StringBuilder();
    Appendable ourStringBuilder = new StringBuilder();
    Image[] imageArray = new Image[1];
    MockViewInvalidInput view = new MockViewInvalidInput(stringBuilder, imageArray, "-10");
    Features controller = new GUIController(model, view);
    ourStringBuilder.append(controller + " received by view.");

    controller.processCommand("Invalid");
    ourStringBuilder.append("Invalid command.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    //we overrode the getInput in the mock to return -10, so calling the brightness command should
    //throw an error
    controller.processCommand("Brighten");
    ourStringBuilder.append("Value must be a positive integer.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());
    controller.processCommand("Mosaic");
    ourStringBuilder.append("Value must be a positive integer.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    //we overrode the getInput in the mock to return 1000, so calling the mosaic command should
    //throw an error
    controller.load("res/koala-vertical.png");
    view.input = "1000000000";
    controller.processCommand("Mosaic");
    ourStringBuilder.append("Cannot make mosaic with more seeds than pixels.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());
  }

  @Test
  public void testProcessCommand() throws IOException {

    IMEModel model = new IMEModelImpl();
    Appendable stringBuilder = new StringBuilder();
    Appendable ourStringBuilder = new StringBuilder();
    Image[] imageArray = new Image[1];
    MockViewInvalidInput view = new MockViewInvalidInput(stringBuilder, imageArray, "50");
    Features controller = new GUIController(model, view);
    ourStringBuilder.append(controller + " received by view.");
    assertEquals(ourStringBuilder.toString(), stringBuilder.toString());

    controller.load("test/modeltest/testImage.ppm");
    Image originalRedComponent = new RedGreyscaleCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Red component visualization");
    Image newRedComponent = model.getImageCopy("im");
    this.compareImages(originalRedComponent, newRedComponent);

    controller.load("test/modeltest/testImage.ppm");
    Image originalGreenComponent = new GreenGreyscaleCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Green component visualization");
    Image newGreenComponent = model.getImageCopy("im");
    this.compareImages(originalGreenComponent, newGreenComponent);

    controller.load("test/modeltest/testImage.ppm");
    Image originalBlueComponent = new BlueGreyscaleCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Blue component visualization");
    Image newBlueComponent = model.getImageCopy("im");
    this.compareImages(originalBlueComponent, newBlueComponent);

    controller.load("test/modeltest/testImage.ppm");
    Image originalValueComponent = new ValueGreyscaleCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Value component visualization");
    Image newValueComponent = model.getImageCopy("im");
    this.compareImages(originalValueComponent, newValueComponent);

    controller.load("test/modeltest/testImage.ppm");
    Image originalIntensityComponent = new IntensityGreyscaleCommand().execute(
            model.getImageCopy("im"));
    controller.processCommand("Intensity component visualization");
    Image newIntensityComponent = model.getImageCopy("im");
    this.compareImages(originalIntensityComponent, newIntensityComponent);

    controller.load("test/modeltest/testImage.ppm");
    Image originalLumaComponent = new LumaGreyscaleCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Luma component visualization");
    Image newLumaComponent = model.getImageCopy("im");
    this.compareImages(originalLumaComponent, newLumaComponent);

    controller.load("test/modeltest/testImage.ppm");
    Image originalHorizontalFlip = new HorizontalFlipCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Horizontal Flip");
    Image newHorizontalFlip = model.getImageCopy("im");
    this.compareImages(originalHorizontalFlip, newHorizontalFlip);

    controller.load("test/modeltest/testImage.ppm");
    Image originalVerticalFlip = new VerticalFlipCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Vertical Flip");
    Image newVerticalFlip = model.getImageCopy("im");
    this.compareImages(originalVerticalFlip, newVerticalFlip);

    controller.load("test/modeltest/testImage.ppm");
    Image originalBrighten = new BrightenCommand(50).execute(model.getImageCopy("im"));
    controller.processCommand("Brighten");
    Image newBrighten = model.getImageCopy("im");
    this.compareImages(originalBrighten, newBrighten);

    controller.load("test/modeltest/testImage.ppm");
    Image originalDarken = new BrightenCommand(-50).execute(model.getImageCopy("im"));
    controller.processCommand("Darken");
    Image newDarken = model.getImageCopy("im");
    this.compareImages(originalDarken, newDarken);

    controller.load("test/modeltest/testImage.ppm");
    Image originalBlur = new BlurCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Blur");
    Image newBlur = model.getImageCopy("im");
    this.compareImages(originalBlur, newBlur);

    controller.load("test/modeltest/testImage.ppm");
    Image originalSharpen = new SharpenCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Sharpen");
    Image newSharpen = model.getImageCopy("im");
    this.compareImages(originalSharpen, newSharpen);

    controller.load("test/modeltest/testImage.ppm");
    Image originalGreyscale = new GreyscaleColorTransformationCommand().execute(
            model.getImageCopy("im"));
    controller.processCommand("Greyscale");
    Image newGreyscale = model.getImageCopy("im");
    this.compareImages(originalGreyscale, newGreyscale);

    controller.load("test/modeltest/testImage.ppm");
    Image originalSepia = new SepiaColorTransformationCommand().execute(model.getImageCopy("im"));
    controller.processCommand("Sepia");
    Image newSepia = model.getImageCopy("im");
    this.compareImages(originalSepia, newSepia);


    view.input = "3";
    controller.load("test/modeltest/testImage.ppm");
    Image originalMosaic = new MosaicCommand(3).execute(model.getImageCopy("im"));
    controller.processCommand("Mosaic");
    Image newMosaic = model.getImageCopy("im");
    this.compareImages(originalSepia, newSepia);
  }

  private void compareImages(Image expected, Image actual) {
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertEquals(expected.getMaxValue(), actual.getMaxValue());
    for (int i = 0; i < expected.getHeight(); i++) {
      for (int j = 0; j < expected.getWidth(); j++) {
        Pixel expectedPixel = expected.getPixel(i, j);
        assertEquals(expectedPixel.getRed(), actual.getPixel(i, j).getRed());
        assertEquals(expectedPixel.getGreen(), actual.getPixel(i, j).getGreen());
        assertEquals(expectedPixel.getBlue(), actual.getPixel(i, j).getBlue());
      }
    }
  }
}
