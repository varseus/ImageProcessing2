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
 * Test class for the main method.
 */
public class MainTest {

  @Test
  public void testMainErrorChecking() {
    //show that calling main with one command line argument will throw an exception if that argument
    //is not "-text"
    assertThrows("Invalid command-line arguments.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"-file"}));
    assertThrows("Invalid command-line arguments.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"test/mainTestScript.txt"}));
    //show that calling main with more than two command-line arguments will throw an exception
    assertThrows("Invalid command-line arguments.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"-file", "test/mainTestScript.txt", "helloooo"}));
    assertThrows("Invalid command-line arguments.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"-file", "test/mainTestScript.txt",
            "load", "test/modeltest/testImage.ppm", "testImage"}));
    //show that calling main with two command line arguments where the first one isn't "-file"
    //throws an exception
    assertThrows("Invalid command-line arguments.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"file", "test/mainTestScript.txt"}));
    //show that calling main with two command line arguments where the second isn't a txt file
    //throws an exception
    assertThrows("Invalid command-line arguments.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"-file", "test/modeltest/testImage.ppm"}));
    //show that calling main with two command line arguments where the provided script cannot be
    //found at the given path throws an exception
    assertThrows("Invalid script file name.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"-file", "test/notRealScript.txt"}));
    assertThrows("Invalid script file name.", IllegalArgumentException.class,
        () -> Main.main(new String[]{"-file", "test/controllertest/mainTestScript.txt"}));
  }

  @Test
  public void testMainWorksCorrectlyCommandLineArgs() {
    Main.main(new String[]{"-file", "test/mainTestScript.txt"});
    IMEModel model = new IMEModelImpl();
    IMEController controller = new IMEControllerImpl(model,
            new StringReader("load test/scriptOutput.png im quit"));
    controller.run();
    Image output = model.getImageCopy("im");
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
}
