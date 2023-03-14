package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating a greyscale image using the red component of each pixel.
 */
public class RedGreyscaleCommand extends AbstractCommand {

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    return new PixelImpl(pixel.getRed(), pixel.getRed(), pixel.getRed(), maxValue);
  }
}
