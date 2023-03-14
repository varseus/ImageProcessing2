package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating a greyscale image using the luma of each pixel, which is the
 * calculated as .2126 * red + .7152 * green + .0722 * blue.
 */
public class LumaGreyscaleCommand extends AbstractCommand {

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    int luma = (int)(.2126 * (double)pixel.getRed() + .7152 * (double)pixel.getGreen()
            + .0722 * (double)pixel.getBlue());
    return new PixelImpl(luma, luma, luma, maxValue);
  }
}
