package command;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

/**
 * An abstract class representing a function object for applying an image filter to an image.
 */
public abstract class AbstractFilterCommand implements IMECommand {

  //helper method for execute. Applies the given kernel to the inputImage
  protected Image executeHelper(Image inputImage, double[][] kernel) {
    Pixel[][] outputImagePixels = new Pixel[inputImage.getHeight()][inputImage.getWidth()];

    int kernelCenterRow = kernel.length / 2;
    int kernelCenterCol = kernel[0].length / 2;

    for (int i = 0; i < inputImage.getHeight(); i++) {
      for (int j = 0; j < inputImage.getWidth(); j++) {

        double redComponent = 0;
        double greenComponent = 0;
        double blueComponent = 0;

        for (int k = 0; k < kernel.length; k++) {
          for (int l = 0; l < kernel[0].length; l++) {
            int dx = l - kernelCenterCol;
            int dy = k - kernelCenterRow;
            Pixel inputPixel;
            try {
              inputPixel = inputImage.getPixel(i + dy, j + dx);
            } catch (IllegalArgumentException e) {
              inputPixel = new PixelImpl(0, 0, 0, inputImage.getMaxValue());
            }
            redComponent += (double)inputPixel.getRed() * kernel[k][l];
            greenComponent += (double)inputPixel.getGreen() * kernel[k][l];
            blueComponent += (double)inputPixel.getBlue() * kernel[k][l];
          }
        }
        redComponent = Math.max(Math.min(redComponent, inputImage.getMaxValue()), 0);
        greenComponent = Math.max(Math.min(greenComponent, inputImage.getMaxValue()), 0);
        blueComponent = Math.max(Math.min(blueComponent, inputImage.getMaxValue()), 0);
        Pixel outputPixel = new PixelImpl((int)Math.floor(redComponent),
                (int)Math.floor(greenComponent),
                (int)Math.floor(blueComponent), inputImage.getMaxValue());

        outputImagePixels[i][j] = outputPixel;
      }
    }
    return new ImageImpl(inputImage.getWidth(), inputImage.getHeight(), inputImage.getMaxValue(),
            outputImagePixels);
  }
}
