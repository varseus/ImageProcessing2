package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating a greyscale image using the intensity of each pixel, which is the
 * average of the red, green and blue components.
 */
public class IntensityGreyscaleCommand extends AbstractCommand {

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    return new PixelImpl(average, average, average, maxValue);
  }
}
