package command;

import model.Pixel;
import model.PixelImpl;

/**
 * A command for creating an image that brightens the given image by the given value.
 */
public class BrightenCommand extends AbstractCommand {
  private final int value;

  public BrightenCommand(int value) {
    this.value = value;
  }

  @Override
  protected Pixel executeHelper(Pixel pixel, int maxValue) {
    int red = Math.max(Math.min(pixel.getRed() + this.value, maxValue), 0);
    int green = Math.max(Math.min(pixel.getGreen() + this.value, maxValue), 0);
    int blue = Math.max(Math.min(pixel.getBlue() + this.value, maxValue), 0);
    return new PixelImpl(red, green, blue, maxValue);
  }
}
