package model;

/**
 * Interface representing a pixel in an image.
 */
public interface Pixel {
  /**
   * Gets the red component value.
   *
   * @return the red component value
   */
  int getRed();

  /**
   * Gets the green component value.
   *
   * @return the green component value
   */
  int getGreen();

  /**
   * Gets the blue component value.
   *
   * @return the blue component value
   */
  int getBlue();
}
