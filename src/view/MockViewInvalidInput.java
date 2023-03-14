package view;

import model.Image;

/**
 * A mock view for an image processing application much like MockView, except this class returns
 * what would be an invalid input when prompted for a positive integer.
 */
public class MockViewInvalidInput extends MockView {
  public String input;

  /**
   * Constructor for the MockViewInvalidInput class.
   *
   * @param log a log used to show which methods are called, and what messages would be displayed to
   *           the user
   * @param image the image 'rendered' by this mock
   * @param input the int value for the view to input to the controller
   */
  public MockViewInvalidInput(Appendable log, Image[] image, String input) {
    super(log, image);
    this.input = input;
  }

  @Override
  public String getInput(String message) {
    return input;
  }
}
