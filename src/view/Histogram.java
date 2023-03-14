package view;

import java.util.Map;

/**
 * An interface representing the data for a histogram.
 */
public interface Histogram {
  /**
   * Gets the contents of the histogram as a map from the component values of an image (usually
   * 0 - 255), to the number of pixels with that red, green, blue and intensity count, represented
   * as an integer array.
   *
   * @return a map representing the contents of the histogram
   */
  Map<Integer, int[]> getContents();
}
