package view;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Image;

/**
 * Class representing a histogram as a line chart.
 */
public class HistogramPanel extends JPanel {
  private Histogram histogram;
  private int width;

  /**
   * Constructor for the HistogramPanel class.
   */
  public HistogramPanel() {
    super();
    this.setBorder(new TitledBorder("Histogram"));
  }

  /**
   * Sets the given image as the image to make the histogram from.
   *
   * @param image the image to make the histogram from
   * @throws IllegalArgumentException if the image is null
   */
  public void setImage(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.histogram = new HistogramImpl(image);
    this.width = image.getMaxValue();
  }

  @Override
  public void paintComponent(Graphics g) {
    if (this.histogram == null) {
      return;
    }
    //get the highest value in all the maps
    int max = this.getMaxCount();
    int xConstant = Math.max(1, this.getWidth() / this.width);
    double yConstant = (double) (this.getHeight() - 50) / (double) (max);
    Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.BLACK};
    for (int i = 0; i < 4; i++) {
      g.setColor(colors[i]);
      for (int j = 1; j <= this.width; j++) {
        g.drawLine((j + 4) * xConstant,
                this.getHeight() - 25 -
                        (int) (this.histogram.getContents().get(j - 1)[i] * yConstant),
                (j + 5) * xConstant,
                this.getHeight() - 25 -
                        (int) (this.histogram.getContents().get(j)[i] * yConstant));
      }
    }
  }

  private int getMaxCount() {
    int overallMax = 0;
    for (int i = 0; i <= this.width; i++) {
      int[] components = this.histogram.getContents().get(i);
      int currentPixelMax = components[0];
      for (int j = 1; j < components.length - 1; j++) {
        currentPixelMax = Math.max(components[j], currentPixelMax);
      }
      overallMax = Math.max(currentPixelMax, overallMax);
    }
    return overallMax;
  }
}