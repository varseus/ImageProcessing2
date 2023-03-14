package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating a greyscale image using the green component of each pixel.
 */
public class GreenGreyscaleCommand extends AbstractCommand {

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    return new PixelImpl(pixel.getGreen(), pixel.getGreen(), pixel.getGreen(), maxValue);
  }
}
