package view;

import java.io.IOException;

import controller.Features;
import model.Image;

/**
 * Mock version of a view for an image processing application used for testing.
 */
public class MockView implements IMEView {
  private final Appendable log;

  //this will be an array containing one element - the image currently in the model. Passing it in
  //as an array allows us to get the image out of the model at different stages
  private final Image[] image;

  /**
   * Constructor for the MockView class.
   *
   * @param log a log used to show which methods are called, and what messages would be displayed to
   *           the user
   * @param image the image 'rendered' by this mock
   * @throws IllegalArgumentException if the given log is null
   */
  public MockView(Appendable log, Image[] image) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("Log cannot be null");
    }
    this.log = log;
    this.image = image;
  }

  @Override
  public void addFeatures(Features features) {
    try {
      log.append(features.toString() + " received by view.");
    } catch (IOException e) {
      throw new IllegalArgumentException("Something went wrong.");
    }
  }

  @Override
  public void renderImage(Image image) {
    this.image[0] = image;
  }

  @Override
  public String getInput(String message) {
    //this method is used for getting user input. In our controller, it is used for getting an
    //integer to brighten/darken by. Returning -10 will cause a specific error message we would like
    //to test
    return "50";
  }

  @Override
  public void displayMessage(String message) {
    try {
      log.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException("Something went wrong.");
    }
  }
}
