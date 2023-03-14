package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import command.BlueGreyscaleCommand;
import command.BlurCommand;
import command.BrightenCommand;
import command.GreenGreyscaleCommand;
import command.GreyscaleColorTransformationCommand;
import command.HorizontalFlipCommand;
import command.IMECommand;
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
import view.IMEView;

/**
 * Implementation of the Features interface, which is an asynchronous controller for an image
 * processing application.
 */
public class GUIController implements Features {
  private final IMEModel model;
  private final IMEView view;
  private final Map<String, Supplier<IMECommand>> commandMap;

  /**
   * Constructor for the BetterController class.
   *
   * @param model the model used by the controller
   * @param view  the view used by the controller
   * @param random the random for seeding mosaics
   * @throws IllegalArgumentException if the model or view are null
   */
  public GUIController(IMEModel model, IMEView view, Random random)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.setView();
    this.commandMap = new HashMap<>();
    this.commandMap.put("Red component visualization", () -> new RedGreyscaleCommand());
    this.commandMap.put("Green component visualization", () -> new GreenGreyscaleCommand());
    this.commandMap.put("Blue component visualization", () -> new BlueGreyscaleCommand());
    this.commandMap.put("Value component visualization", () -> new ValueGreyscaleCommand());
    this.commandMap.put("Intensity component visualization", () -> new IntensityGreyscaleCommand());
    this.commandMap.put("Luma component visualization", () -> new LumaGreyscaleCommand());
    this.commandMap.put("Horizontal Flip", () -> new HorizontalFlipCommand());
    this.commandMap.put("Vertical Flip", () -> new VerticalFlipCommand());
    this.commandMap.put("Brighten", () -> new BrightenCommand(this.parseInput(this.view.getInput(
            "Enter a positive integer to brighten by:"))));
    this.commandMap.put("Darken", () -> new BrightenCommand(-this.parseInput(this.view.getInput(
            "Enter a positive integer to darken by:"))));
    this.commandMap.put("Blur", () -> new BlurCommand());
    this.commandMap.put("Sharpen", () -> new SharpenCommand());
    this.commandMap.put("Greyscale", () -> new GreyscaleColorTransformationCommand());
    this.commandMap.put("Sepia", () -> new SepiaColorTransformationCommand());
    this.commandMap.put("Mosaic", () -> new MosaicCommand(this.parseInput(this.view.getInput(
            "Enter a number of seeds for the desired mosaic:")), random));
  }

  /**
   * Constructor for the BetterController class.
   *
   * @param model the model used by the controller
   * @param view  the view used by the controller
   * @throws IllegalArgumentException if the model or view are null
   */
  public GUIController(IMEModel model, IMEView view)
          throws IllegalArgumentException {
    this(model, view, new Random());
  }

  //takes in an input as a String, and returns as an integer if it is a positive integer. Otherwise,
  //throws an IllegalArgumentException
  private int parseInput(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null.");
    }
    int ret;
    try {
      ret = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input.");
    }
    if (ret < 0) {
      throw new IllegalArgumentException("Invalid input.");
    }
    return ret;
  }


  @Override
  public void load(String filePath) {
    if (filePath == null) {
      return;
    }
    Image image;
    try {
      image = IOUtils.load(filePath);
    } catch (IllegalArgumentException | IllegalStateException e) {
      view.displayMessage(e.getMessage());
      return;
    }
    this.model.addToModel("im", image);
    //we don't want to give the same image reference to both the model and view. Since the model
    //already has a method for creating a copy of an image, instead of giving the view a copy of the
    //image, we replace the image given to the model with a copy of itself
    this.model.addToModel("im", this.model.getImageCopy("im"));
    this.view.renderImage(image);
  }

  @Override
  public void save(String filePath) {
    if (filePath == null) {
      return;
    }
    Image image = null;
    try {
      image = this.model.getImageCopy("im");
    } catch (IllegalArgumentException e) {
      this.view.displayMessage("Cannot save without loading an image first.");
      return;
    }
    try {
      IOUtils.save(image, filePath);
    } catch (IllegalArgumentException | IllegalStateException e) {
      this.view.displayMessage(e.getMessage());
    }
  }

  @Override
  public void setView() {
    this.view.addFeatures(this);
  }

  @Override
  public void processCommand(String choice) {
    if (choice.equals("None")) {
      return;
    }
    Supplier<IMECommand> supplier = this.commandMap.get(choice);
    if (supplier == null) {
      this.view.displayMessage("Invalid command.");
      return;
    }
    IMECommand command;
    try {
      command = supplier.get();
    } catch (IllegalArgumentException e) {
      if (e.getMessage().equals("Input cannot be null.")) {
        return;
      }
      this.view.displayMessage("Value must be a positive integer.");
      return;
    }
    try {
      this.model.process(command, "im", "im");
    } catch (IllegalArgumentException e) {
      if (e.getMessage().equals("Cannot make mosaic with more seeds than pixels.")) {
        this.view.displayMessage(e.getMessage());
      }
      return;
    }
    this.view.renderImage(this.model.getImageCopy("im"));
  }
}
