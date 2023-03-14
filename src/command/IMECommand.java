package command;

import model.Image;

/**
 * An interface for function objects which perform image processing commands.
 */
public interface IMECommand {

  /**
   * Creates a new Image by performing a function on the given input Image.
   *
   * @param inputImage the input Image to have the function performed on
   * @return a new Image that has been processed in some way
   */
  Image execute(Image inputImage);
}
