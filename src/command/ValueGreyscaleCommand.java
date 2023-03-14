package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating a greyscale image using the value of each pixel, which is the maximum of
 * the red, green and blue components.
 */
public class ValueGreyscaleCommand extends AbstractCommand {

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    int value = Math.max(Math.max(pixel.getRed(), pixel.getGreen()), pixel.getBlue());
    return new PixelImpl(value, value, value, maxValue);
  }
}
