package model;

/**
 * An interface representing an image.
 */
public interface Image {

  /**
   * Gets the width of this Image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Gets the height of this Image.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Gets the maximum value of a component of a pixel's rgb value in this Image.
   *
   * @return the maximum value of a component of a pixel's rgb value in the image
   */
  int getMaxValue();

  /**
   * Returns the Pixel at the specified location in this Image.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the Pixel at the specified row and column
   * @throws IllegalArgumentException if the given row and column do not correspond to a Pixel in
   *                                  this Image
   */
  Pixel getPixel(int row, int col) throws IllegalArgumentException;
}
