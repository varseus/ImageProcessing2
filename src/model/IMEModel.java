package model;

import command.IMECommand;

/**
 * An interface defining functions for processing images. This model will represent a collection
 * of images on which different processing functions can be performed.
 */
public interface IMEModel {

  /**
   * Given an image name, performs a function on the corresponding Image and saves the produced
   * Image under the given output image name.
   * @param command the function to be performed on the input image
   * @param inputImageName the name of the input Image
   * @param outputImageName the name of the output Image
   * @throws IllegalArgumentException if the inputImageName is not found in the model or the given
   *                                  IMECommand is null
   */
  void process(IMECommand command, String inputImageName, String outputImageName)
      throws IllegalArgumentException;

  /**
   * Gets a copy of the Image corresponding to the given image name.
   *
   * @param imageName the name of the image to return
   * @return the Image corresponding to the given name
   * @throws IllegalArgumentException if the image name does not correspond to an Image in the model
   */
  Image getImageCopy(String imageName) throws IllegalArgumentException;

  /**
   * Adds the given Image to the model under the given name.
   *
   * @param name the image name to be added to the model
   * @param image the Image to be added to the model
   * @throws IllegalArgumentException if the given model is null
   */
  void addToModel(String name, Image image) throws IllegalArgumentException;
}
