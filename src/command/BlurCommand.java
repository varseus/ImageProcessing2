package command;

import model.Image;

/**
 * A command for creating a blurred version of the given image.
 */
public class BlurCommand extends AbstractFilterCommand {
  private final double[][] kernel = new double[][]{
      new double[]{.0625, .125, .0625},
      new double[]{.125, .25, .125},
      new double[]{.0625, .125, .0625}};

  @Override
  public Image execute(Image inputImage) {
    return this.executeHelper(inputImage, this.kernel);
  }
}
