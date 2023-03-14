package command;

import model.Image;

/**
 * A command for creating a sharpened version of the given image.
 */
public class SharpenCommand extends AbstractFilterCommand {
  private final double[][] kernel = new double[][]{
      new double[]{-.125, -.125, -.125, -.125, -.125},
      new double[]{-.125, .25, .25, .25, -.125},
      new double[]{-.125, .25, 1, .25, -.125},
      new double[]{-.125, .25, .25, .25, -.125},
      new double[]{-.125, -.125, -.125, -.125, -.125}};

  @Override
  public Image execute(Image inputImage) {
    return this.executeHelper(inputImage, this.kernel);
  }
}