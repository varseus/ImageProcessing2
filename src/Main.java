import controller.GUIController;
import controller.Features;
import controller.IMEControllerImpl;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.IMEController;
import model.IMEModel;
import model.IMEModelImpl;
import view.IMEView;
import view.JFrameView;

/**
 * Class for running the image processing application.
 */
public class Main {

  /**
   * Main method for running the image processing application.
   *
   * @param args the command line arguments for the method
   */
  public static void main(String[] args) {
    IMEModel model = new IMEModelImpl();

    if (args.length == 0) {
      IMEView view = new JFrameView();
      Features controller = new GUIController(model, view);
      return;
    }

    IMEController controller;
    if (args.length == 1 && args[0].equals("-text")) {
      controller = new IMEControllerImpl(model, new InputStreamReader(System.in));
    }
    else if (args.length == 2 && args[0].equals("-file") && args[1].endsWith(".txt")) {
      try {
        controller = new IMEControllerImpl(model, new FileReader(args[1]));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Invalid script file name.");
      }
    } else {
      throw new IllegalArgumentException("Invalid command-line arguments.");
    }
    controller.run();
  }
}