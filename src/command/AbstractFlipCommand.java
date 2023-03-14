package command;

import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * An abstract class for the functionality of a command that creates a new Image that flips the
 * given image across some axis.
 */
public abstract class AbstractFlipCommand implements IMECommand {

  @Override
  public Image execute(Image inputImage) {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    Pixel[][] outputImagePixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImagePixels[i][j] = executeHelper(i, j, width, height, inputImage);
      }
    }
    return new ImageImpl(inputImage.getWidth(), inputImage.getHeight(), inputImage.getMaxValue(),
            outputImagePixels);
  }

  //returns the Pixel necessary for the type of flip being performed
  protected abstract Pixel executeHelper(int row, int col, int width, int height, Image inputImage);
}
