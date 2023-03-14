package command;

import model.Image;

/**
 * A command for creating a sepia tone image using a color transformation matrix.
 */
public class SepiaColorTransformationCommand extends AbstractColorTransformationCommand {
  private final double[][] colorMatrix = new double[][]{
      new double[]{.393, .769, .189},
      new double[]{.349, .686, .168},
      new double[]{.272, .534, .131}};

  @Override
  public Image execute(Image inputImage) {
    return this.executeHelper(inputImage, colorMatrix);
  }
}
