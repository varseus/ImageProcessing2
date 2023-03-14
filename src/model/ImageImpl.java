package model;

/**
 * A class that implements the Image interface, using a 2-D array of Pixels to represent an image.
 */
public class ImageImpl implements Image {
  private final int width;
  private final int height;
  private final int maxValue;
  public final Pixel[][] imagePixels;

  /**
   * Constructor for the Image class.
   *
   * @param width       the width of the image
   * @param height      the height of the image
   * @param maxValue    the maximum value of a component of a pixel's rgb value
   * @param imagePixels a 3D array containing the rgb components for every pixel in the image
   */
  public ImageImpl(int width, int height, int maxValue, Pixel[][] imagePixels) {
    if (width < 1 || height < 1 || maxValue < 1) {
      throw new IllegalArgumentException("Invalid argument.");
    }
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    if (imagePixels == null || imagePixels.length != height || imagePixels[0].length != width) {
      throw new IllegalArgumentException("Invalid pixel array.");
    }
    for (Pixel[] row : imagePixels) {
      for (Pixel pixel : row) {
        if (pixel == null) {
          throw new IllegalArgumentException("Invalid pixel array.");
        }
      }
    }
    this.imagePixels = imagePixels;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public Pixel getPixel(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.height || col < 0 || col >= this.width) {
      throw new IllegalArgumentException("Invalid row or column.");
    }
    return this.imagePixels[row][col];
  }
}
