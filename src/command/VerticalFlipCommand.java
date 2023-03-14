package command;

import model.Image;
import model.Pixel;

/**
 * A command for creating an image that flips the given image vertically.
 */
public class VerticalFlipCommand extends AbstractFlipCommand {

  @Override
  protected Pixel executeHelper(int row, int col, int width, int height, Image inputImage) {
    return inputImage.getPixel(height - 1 - row, col);
  }
}
