package command;

import model.Image;

/**
 * A command for creating a greyscale image using a color transformation matrix representing a luma.
 */
public class GreyscaleColorTransformationCommand extends AbstractColorTransformationCommand {
  private final double[][] colorMatrix = new double[][]{
      new double[]{.2126, .7152, .0722},
      new double[]{.2126, .7152, .0722},
      new double[]{.2126, .7152, .0722}};

  @Override
  public Image execute(Image inputImage) {
    return this.executeHelper(inputImage, colorMatrix);
  }
}
