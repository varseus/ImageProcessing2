package controller;

/**
 * An interface defining the features of an image processing application, including loading, saving
 * and various processing commands as defined by the implementing classes.
 */
public interface Features {
  /**
   * Loads the image at the given file path into the model, and renders it in the view. Displays
   * an error message in the view if the file path in invalid or the file is corrupted in some way.
   *
   * @param filePath the file path holding the image to load
   */
  void load(String filePath);

  /**
   * Saves the image in the model to the given filePath. Displays an error message in the view if
   * the model does not contain an image, the given file extension is invalid, or the saving fails
   * for some reason.
   *
   * @param filePath the file path to save the image to
   */
  void save(String filePath);

  /**
   * Gives the view access to the features accessible via the methods of the interface.
   */
  void setView();

  /**
   * Takes in a command as a string, and executes it on the image in the model. Displays an error
   * message in the view if the given command is invalid.
   *
   * @param command the command to be performed
   */
  void processCommand(String command);
}
