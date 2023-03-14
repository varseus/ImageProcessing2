package controller;

/**
 * An interface holding the functions required for the controller of an image processing
 * application.
 */
public interface IMEController {

  /**
   * Runs the image processing application. Specific functionality may vary by
   * implementation.
   */
  void run();

  /**
   * Creates an Image from the file found at the given path, then adds the image to the model
   * using the given image name. The accepted file types are dependent on the implementation.
   *
   * @param fileName the path to find the image at
   * @param imageName the name to give to the Image
   * @throws IllegalArgumentException if the fileName does not exist
   * @throws IllegalStateException if the file is corrupted in some way
   */
  void load(String fileName, String imageName) throws IllegalArgumentException,
          IllegalStateException;

  /**
   * Takes the given image name and saves the corresponding Image as a file under the given
   * file name. Which file types are valid will be dependent on the implementation.
   *
   * @param imageName the name of the Image to save
   * @param fileName the file name for saved file
   * @throws IllegalArgumentException if the given name does not correspond to an Image in the model
   *                                  or the file type is invalid
   * @throws IllegalStateException if creating or writing to the file fails
   */
  void save(String imageName, String fileName) throws IllegalArgumentException,
          IllegalStateException;
}
