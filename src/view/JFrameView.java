package view;

import controller.Features;

import java.awt.HeadlessException;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Image;

/**
 * Implements the IMEView interface. Constructs a GUI using the Swing framework.
 */
public class JFrameView extends JFrame implements IMEView {
  private final ImagePanel imagePanel;
  private final HistogramPanel histogramPanel;
  private final JButton loadButton;
  private final JButton saveButton;
  private final OptionPanel optionPanel;

  /**
   * Constructor for the JFrameView class.
   */
  public JFrameView() {
    super("GRIME");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());

    this.imagePanel = new ImagePanel();
    topPanel.add(this.imagePanel);

    this.histogramPanel = new HistogramPanel();
    topPanel.add(this.histogramPanel);

    this.loadButton = new JButton("Load");
    bottomPanel.add(this.loadButton);

    this.saveButton = new JButton("Save");
    bottomPanel.add(this.saveButton);

    this.optionPanel = new OptionPanel();
    bottomPanel.add(this.optionPanel);

    this.add(topPanel);
    this.add(bottomPanel);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    this.loadButton.addActionListener(event -> features.load(this.getUserFilePathLoad()));
    this.saveButton.addActionListener(event -> features.save(this.getUserFilePathSave()));
    this.optionPanel.addFeatures(features);
  }

  //gives the user a JFileChooser to choose a file from, filters the shown files to only the
  //supported image extensions, and returns the file path of the chosen file
  private String getUserFilePathLoad() {
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "ppm", "jpg", "jpeg", "bmp", "png");
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getPath();
    }
    return null;
  }

  //gives the user a JFileChooser to save an image, and returns the supplied file path
  private String getUserFilePathSave() {
    JFileChooser fileChooser = new JFileChooser(".");
    int returnValue = fileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getPath();
    }
    return null;
  }

  @Override
  public void renderImage(Image image) {
    this.imagePanel.renderImage(image);
    this.histogramPanel.setImage(image);
    this.histogramPanel.setVisible(false);
    this.histogramPanel.setVisible(true);
  }

  @Override
  public String getInput(String message) {
    try {
      return JOptionPane.showInputDialog(message);
    } catch (HeadlessException e) {
      return null;
    }
  }

  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message",
            JOptionPane.PLAIN_MESSAGE);
  }
}
