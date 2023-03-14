package controller;

import command.BlueGreyscaleCommand;
import command.BlurCommand;
import command.BrightenCommand;
import command.GreenGreyscaleCommand;
import command.GreyscaleColorTransformationCommand;
import command.HorizontalFlipCommand;
import command.IMECommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

import command.IntensityGreyscaleCommand;
import command.LumaGreyscaleCommand;
import command.MosaicCommand;
import command.RedGreyscaleCommand;
import command.SepiaColorTransformationCommand;
import command.SharpenCommand;
import command.ValueGreyscaleCommand;
import command.VerticalFlipCommand;
import model.IMEModel;
import model.Image;
import utils.IOUtils;
import utils.Utils;

/**
 * Implementation of the IMEController interface. Runs the application using commands from a
 * Readable.
 */
public class IMEControllerImpl implements IMEController {
  protected final IMEModel model;
  private final Readable in;
  protected final Map<String, Function<Scanner, IMECommand>> commandMap;

  /**
   * Constructor for the IMEControllerImpl class.
   *
   * @param model the model used by the controller
   * @param in the user input
   * @param random random for mosaic seeding
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public IMEControllerImpl(IMEModel model, Readable in, Random random)
          throws IllegalArgumentException {
    if (model == null || in == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    this.model = model;
    this.in = in;
    this.commandMap = new HashMap<>();
    this.commandMap.put("red-component", s -> new RedGreyscaleCommand());
    this.commandMap.put("green-component", s -> new GreenGreyscaleCommand());
    this.commandMap.put("blue-component", s -> new BlueGreyscaleCommand());
    this.commandMap.put("value-component", s -> new ValueGreyscaleCommand());
    this.commandMap.put("intensity-component", s -> new IntensityGreyscaleCommand());
    this.commandMap.put("luma-component", s -> new LumaGreyscaleCommand());
    this.commandMap.put("horizontal-flip", s -> new HorizontalFlipCommand());
    this.commandMap.put("vertical-flip", s -> new VerticalFlipCommand());
    this.commandMap.put("brighten", s -> new BrightenCommand(Utils.getScannerNextInt(s)));
    this.commandMap.put("blur", s -> new BlurCommand());
    this.commandMap.put("sharpen", s -> new SharpenCommand());
    this.commandMap.put("greyscale", s -> new GreyscaleColorTransformationCommand());
    this.commandMap.put("sepia", s -> new SepiaColorTransformationCommand());
    this.commandMap.put("mosaic", s -> new MosaicCommand(Utils.getScannerNextInt(s), random));
  }

  /**
   * Constructor for the IMEControllerImpl class.
   *
   * @param model the model used by the controller
   * @param in the user input
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public IMEControllerImpl(IMEModel model, Readable in)
          throws IllegalArgumentException {
    this(model, in, new Random());
  }

  /**
   * Runs the image processing application. Uses a scanner to take in user inputs, and delegates
   * commands to the model and view.
   *
   * @throws IllegalArgumentException if an invalid command is given to the controller
   * @throws IllegalStateException if the scanner runs out of values
   */
  @Override
  public void run() throws IllegalArgumentException, IllegalStateException {
    Scanner scanner = new Scanner(this.in);
    while (true) {
      String operation = Utils.getScannerNext(scanner);

      switch (operation) {
        case "quit":
          return;
        case "load":
          this.load(Utils.getScannerNext(scanner), Utils.getScannerNext(scanner));
          break;
        case "save":
          this.save(Utils.getScannerNext(scanner), Utils.getScannerNext(scanner));
          break;
        default:
          Function<Scanner, IMECommand> function = this.commandMap.get(operation);
          if (function == null) {
            throw new IllegalArgumentException("Invalid command.");
          }
          IMECommand command = function.apply(scanner);
          this.model.process(command, Utils.getScannerNext(scanner), Utils.getScannerNext(scanner));
      }
    }
  }

  /**
   * Creates an Image from the file found at the given path, then adds the image to the model
   * using the given image name. The accepted file types in this implementation are ppm, png, bmp,
   * jpg and jpeg.
   *
   * @param fileName the path to find the image at
   * @param imageName the name to give to the Image
   * @throws IllegalArgumentException if the fileName does not exist or the file is not of a
   *                                  supported type
   * @throws IllegalStateException if the file is corrupted in some way
   */
  @Override
  public void load(String fileName, String imageName) throws IllegalArgumentException,
      IllegalStateException {

    Image image = IOUtils.load(fileName);
    this.model.addToModel(imageName, image);
  }

  /**
   * Takes the given image name and saves the corresponding Image as a file under the given
   * file name. Supports saving to ppm, bmp, png, jpg and jpeg files.
   *
   * @param imageName the name of the Image to save
   * @param fileName  the file name for the saved file
   * @throws IllegalArgumentException if the given name does not correspond to an Image in the model
   *                                  or the file type is invalid
   * @throws IllegalStateException    if creating or writing to the file fails
   */
  @Override
  public void save(String imageName, String fileName) throws IllegalArgumentException,
          IllegalStateException {

    Image image = this.model.getImageCopy(imageName);
    if (image == null) {
      throw new IllegalArgumentException("File not found.");
    }

    IOUtils.save(image, fileName);
  }
}
