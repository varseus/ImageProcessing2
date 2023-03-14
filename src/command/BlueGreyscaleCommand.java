package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating a greyscale image using the blue component of each pixel.
 */
public class BlueGreyscaleCommand extends AbstractCommand {

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    return new PixelImpl(pixel.getBlue(), pixel.getBlue(), pixel.getBlue(), maxValue);
  }
}
