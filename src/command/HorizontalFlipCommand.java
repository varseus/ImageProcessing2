package command;

import model.Image;
import model.Pixel;

/**
 * A command for creating an image that flips the given image horizontally.
 */
public class HorizontalFlipCommand extends AbstractFlipCommand {

  @Override
  protected Pixel executeHelper(int row, int col, int width, int height, Image inputImage) {
    return inputImage.getPixel(row, width - 1 - col);
  }
}
