package view;

import controller.Features;
import model.Image;

/**
 * Interface representing a view for an image processing application.
 */
public interface IMEView {

  /**
   * Gives the view access to the given Features.
   *
   * @param features the Features to be given to the view.
   */
  void addFeatures(Features features);

  /**
   * Renders the given image in the view, and draws its histogram.
   *
   * @param image the image to be rendered
   */
  void renderImage(Image image);

  /**
   * Prompts the user for an input, and returns it as a String.
   *
   * @param message the message to show when prompting the user for their input
   * @return the user's supplied input
   */
  String getInput(String message);

  /**
   * Displays a message to the user.
   *
   * @param message the message to be displayed
   */
  void displayMessage(String message);
}
