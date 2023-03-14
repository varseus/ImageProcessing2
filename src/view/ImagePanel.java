package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import utils.IOUtils;
import model.Image;

/**
 * Class representing a panel which holds an image in a scroll pane.
 */
public class ImagePanel extends JPanel {
  private final JLabel imageLabel;

  /**
   * Constructor for the ImagePanel class.
   */
  public ImagePanel() {
    super();
    this.setBorder(new TitledBorder("Current Image"));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    this.imageLabel  = new JLabel();
    this.addScrollPane();
  }

  /**
   * Renders the given image in the panel, adding a scroll pane if necessary.
   *
   * @param image the image to be rendered
   */
  public void renderImage(Image image) {
    this.removeAll();

    BufferedImage bufferedImage = IOUtils.getBufferedImage(image);

    this.imageLabel.setIcon(new ImageIcon(bufferedImage));
    this.addScrollPane();
  }

  //helper method for adding a scroll pane to the panel
  private void addScrollPane() {
    JScrollPane scrollPane = new JScrollPane(this.imageLabel);
    scrollPane.setPreferredSize(new Dimension(100, 300));
    this.add(scrollPane);
  }
}
