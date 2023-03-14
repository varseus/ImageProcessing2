package model;

import command.IMECommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the IMEModel interface which uses a Map to relate image names to Image objects.
 */
public class IMEModelImpl implements IMEModel {
  private final Map<String, Image> images;

  /**
   * Constructor for the IMEModelImpl class.
   */
  public IMEModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void process(IMECommand command, String inputImageName, String outputImageName)
      throws IllegalArgumentException {
    Image inputImage = this.images.get(inputImageName);
    if (inputImage == null) {
      throw new IllegalArgumentException("File not found");
    }
    if (command == null) {
      throw new IllegalArgumentException("Command cannot be null.");
    }
    Image outputImage = command.execute(inputImage);
    this.images.put(outputImageName, outputImage);
  }

  @Override
  public void addToModel(String name, Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.images.put(name, image);
  }

  @Override
  public Image getImageCopy(String imageName) throws IllegalArgumentException {
    Image inputImage = this.images.get(imageName);
    if (inputImage == null) {
      throw new IllegalArgumentException("File not found");
    }
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    Pixel[][] imagePixelsCopy = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel rgb = inputImage.getPixel(i, j);
        imagePixelsCopy[i][j] = rgb;
      }
    }
    return new ImageImpl(width, height, inputImage.getMaxValue(), imagePixelsCopy);
  }
}
