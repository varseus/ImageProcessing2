package view;

import java.util.HashMap;
import java.util.Map;

import model.Image;
import model.Pixel;

/**
 * Implementation of the Histogram interface. Uses a HashMap to store the data.
 */
public class HistogramImpl implements Histogram {
  private Map<Integer, int[]> componentCounts;

  /**
   * Constructor for the HistogramImpl class.
   *
   * @param image the image used to create the histogram
   * @throws IllegalArgumentException if the given image is null
   */
  public HistogramImpl(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.setComponentCounts(image);
  }

  //sets the data for the histogram from the given image
  private void setComponentCounts(Image image) {
    this.componentCounts = new HashMap<>();
    for (int i = 0; i <= image.getMaxValue(); i++) {
      this.componentCounts.put(i, new int[4]);
      for (int j = 0; j < 4; j++) {
        this.componentCounts.get(i)[j] = 0;
      }
    }

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel pixel = image.getPixel(i, j);
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int intensity = this.getIntensity(pixel);

        this.componentCounts.get(red)[0]++;
        this.componentCounts.get(green)[1]++;
        this.componentCounts.get(blue)[2]++;
        this.componentCounts.get(intensity)[3]++;
      }
    }
  }

  //gets the intensity of a given pixel
  private int getIntensity(Pixel pixel) {
    return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
  }

  @Override
  public Map<Integer, int[]> getContents() {
    Map<Integer, int[]> toReturn = new HashMap<>();
    for (Map.Entry<Integer, int[]> entry : this.componentCounts.entrySet()) {
      toReturn.put(entry.getKey(), entry.getValue().clone());
    }
    return toReturn;
  }
}
