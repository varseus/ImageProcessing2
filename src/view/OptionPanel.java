package view;

import controller.Features;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;

/**
 * A class representing a panel with options for processing an image.
 */
public class OptionPanel extends JPanel {
  private final JComboBox<String> comboBox;

  /**
   * Constructor for the OptionPanel class.
   */
  public OptionPanel() {
    super();
    this.setBorder(BorderFactory.createTitledBorder("Commands:"));
    String[] options = {"None", "Red component visualization", "Green component visualization",
      "Blue component visualization", "Value component visualization",
      "Intensity component visualization", "Luma component visualization", "Horizontal Flip",
      "Vertical Flip", "Brighten", "Darken", "Blur", "Sharpen", "Greyscale", "Sepia", "Mosaic"};
    this.comboBox = new JComboBox<>();
    for (String option : options) {
      this.comboBox.addItem(option);
    }
    this.add(this.comboBox);
  }

  /**
   * Returns a string for the command selected by the user from the combo boxes.
   * @param event the event triggered by selecting an option from the combo boxes
   * @return a string version of the command chosen
   */
  public String getComboBoxChoice(ActionEvent event) {
    if (event.getSource() instanceof JComboBox) {
      return (String)((JComboBox<String>)event.getSource()).getSelectedItem();
    }
    throw new IllegalStateException("Something went wrong.");
  }

  /**
   * Adds an action listener for when a combo box option is chosen by the user, which calls a method
   * for processing the command relating to the chosen option.
   *
   * @param features the features object that processes the command
   */
  public void addFeatures(Features features) {
    this.comboBox.addActionListener(
        event -> features.processCommand(this.getComboBoxChoice(event)));
  }
}
