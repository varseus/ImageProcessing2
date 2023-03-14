package command;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

/**
 * An abstract class for the functionality of a command that creates a new Image by applying a
 * color transformation to every pixel of a given image.
 */
public abstract class AbstractColorTransformationCommand implements IMECommand {

  //helper method for execute
  protected Image executeHelper(Image inputImage, double[][] colorMatrix) {
    Pixel[][] outputImagePixels = new Pixel[inputImage.getHeight()][inputImage.getWidth()];
    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {
        Pixel pixel = inputImage.getPixel(i, j);
        int redComponent = this.getComponent(pixel, 0, colorMatrix);
        int greenComponent = this.getComponent(pixel, 1, colorMatrix);
        int blueComponent = this.getComponent(pixel, 2, colorMatrix);
        redComponent = Math.max(Math.min(redComponent, inputImage.getMaxValue()), 0);
        greenComponent = Math.max(Math.min(greenComponent, inputImage.getMaxValue()), 0);
        blueComponent = Math.max(Math.min(blueComponent, inputImage.getMaxValue()), 0);
        outputImagePixels[i][j] = new PixelImpl(redComponent, greenComponent, blueComponent,
                inputImage.getMaxValue());
      }
    }
    return new ImageImpl(inputImage.getWidth(), inputImage.getHeight(), inputImage.getMaxValue(),
            outputImagePixels);
  }

  //helper method for creating a color component of a pixel
  private int getComponent(Pixel pixel, int row, double[][] colorMatrix) {
    return (int)(colorMatrix[row][0] * (double)pixel.getRed()
            + colorMatrix[row][1] * (double)pixel.getGreen()
            + colorMatrix[row][2] * (double)pixel.getBlue());
  }
}
