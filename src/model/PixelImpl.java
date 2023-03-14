package model;

/**
 * A class implementing the Pixel interface, with a red, green and blue component.
 */
public class PixelImpl implements Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor for the PixelImpl class.
   *
   * @param red the value of the red component
   * @param green the value of the green component
   * @param blue the value of the blue component
   * @param maxValue the maximum value for any of the rgb components
   */
  public PixelImpl(int red, int green, int blue, int maxValue) {
    if (maxValue < 1) {
      throw new IllegalArgumentException("Invalid max value.");
    }
    if (!((0 <= red && red <= maxValue) && (0 <= green && green <= maxValue)
            && (0 <= blue && blue <= maxValue))) {
      throw new IllegalArgumentException("Invalid pixel component values.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }
}
