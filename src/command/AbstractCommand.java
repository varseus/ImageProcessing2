package command;

import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * An abstract class for the functionality of a command that creates a new Image one Pixel at a
 * time, only getting the data for the new Pixel from the corresponding Pixel in the original Image.
 */
public abstract class AbstractCommand implements IMECommand {

  @Override
  public Image execute(Image inputImage) {
    Pixel[][] outputImagePixels = new Pixel[inputImage.getHeight()][inputImage.getWidth()];
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        Pixel pixel = this.executeHelper(inputImage.getPixel(i, j), inputImage.getMaxValue());
        outputImagePixels[i][j] = pixel;
      }
    }
    return new ImageImpl(inputImage.getWidth(), inputImage.getHeight(), inputImage.getMaxValue(),
            outputImagePixels);
  }

  /**
   * Applies a transformation to a given Pixel by modifying its rgb components.
   *
   * @param pixel the Pixel to be transformed
   * @param maxValue the maximum value for the Pixel to be created
   */
  protected abstract Pixel executeHelper(Pixel pixel, int maxValue);
}
