package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import model.PixelImpl;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Class containing utility methods for Image IO, as well as turning an image into a BufferedImage.
 */
public class IOUtils {

  /**
   * Creates an Image from the file found at the given filePath.
   * The accepted file types for this method are ppm, png, bmp, jpg and jpeg.
   *
   * @param filePath the filePath to find the image at
   * @return an Image made from the image file at the given path
   * @throws IllegalArgumentException if the fileName does not exist or the file is not of a
   *                                  supported type
   * @throws IllegalStateException    if the file is corrupted in some way
   */
  public static Image load(String filePath) throws IllegalArgumentException,
          IllegalStateException {
    if (filePath.endsWith(".ppm")) {
      return IOUtils.loadImageFromPPMPath(filePath);
    }

    if (!(filePath.endsWith(".bmp") || filePath.endsWith(".png") || filePath.endsWith(".jpg")
            || filePath.endsWith(".jpeg"))) {
      throw new IllegalArgumentException("Invalid file type.");
    }

    BufferedImage image;
    try {
      image = ImageIO.read(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file name or filePath.");
    } catch (IOException ioe) {
      throw new IllegalStateException("Unable to create FileInputStream.");
    }

    int width = image.getWidth();
    int height = image.getHeight();

    Pixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgb = image.getRGB(j, i);
        int red = (rgb & 0xff0000) >> 16;
        int green = (rgb & 0xff00) >> 8;
        int blue = rgb & 0xff;
        Pixel pixel = new PixelImpl(red, green, blue, 255);
        pixels[i][j] = pixel;
      }
    }

    return new ImageImpl(width, height, 255, pixels);
  }

  //helper method for loading in a ppm file at the given path. Returns an Image for the ppm file.
  //throws an exception if the file path is invalid, or the file is not a P3 ppm
  private static Image loadImageFromPPMPath(String path) {
    Scanner scanner;

    try {
      scanner = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file name or path.");
    }

    //this case should never be hit with our current usage
    if (path.length() > 4 && !path.endsWith(".ppm")) {
      throw new IllegalArgumentException("File name does not end in \".ppm\"");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scanner.hasNextLine()) {
      String s = scanner.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    scanner = new Scanner(builder.toString());

    String token;

    token = scanner.next();
    if (!token.equals("P3")) {
      throw new IllegalStateException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = Utils.getScannerNextInt(scanner);
    int height = Utils.getScannerNextInt(scanner);
    int maxValue = Utils.getScannerNextInt(scanner);

    Pixel[][] imagePixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = Utils.getScannerNextInt(scanner);
        int g = Utils.getScannerNextInt(scanner);
        int b = Utils.getScannerNextInt(scanner);
        imagePixels[i][j] = new PixelImpl(r, g, b, maxValue);
      }
    }

    return new ImageImpl(width, height, maxValue, imagePixels);
  }

  /**
   * Takes the given Image and saves it as a file under the given file name. Supports saving to ppm,
   * bmp, png, jpg and jpeg files.
   *
   * @param image    the Image to save
   * @param filePath the file path for the saved file
   * @throws IllegalArgumentException if the file type is invalid
   * @throws IllegalStateException    if creating or writing to the file fails
   */
  public static void save(Image image, String filePath) throws IllegalArgumentException,
          IllegalStateException {
    if (filePath.endsWith(".ppm")) {
      IOUtils.saveAsPPM(image, filePath);
      return;
    }

    if (!(filePath.endsWith(".bmp") || filePath.endsWith(".png") || filePath.endsWith(".jpg")
            || filePath.endsWith(".jpeg"))) {
      throw new IllegalArgumentException("Invalid file type.");
    }

    String formatName = filePath.substring(filePath.lastIndexOf(".") + 1);

    if (image == null) {
      throw new IllegalArgumentException("File not found.");
    }

    BufferedImage bufferedImage = IOUtils.getBufferedImage(image);

    try {
      ImageIO.write(bufferedImage, formatName, new File(filePath));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to file."); //this case should never happen
    }
  }

  //saves the given Image as a ppm file to the given file path. Throws an exception if the file
  //path is not for a ppm file, or if the image is null, or if something goes wrong while saving
  //with the file writer
  private static void saveAsPPM(Image image, String filePath) {
    if (image == null) {
      throw new IllegalArgumentException("File not found.");
    }

    FileWriter fileWriter;

    //this case should never be hit with our current usage
    if (!filePath.endsWith(".ppm")) {
      throw new IllegalArgumentException("File name does not end in \".ppm\"");
    }

    try {
      fileWriter = new FileWriter(filePath);
    } catch (IOException e) {
      throw new IllegalStateException("FileWriter could not be created.");
    }

    write(fileWriter, "P3\n");
    write(fileWriter, image.getWidth() + " ");
    write(fileWriter, image.getHeight() + "\n");
    write(fileWriter, image.getMaxValue() + "\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        write(fileWriter, image.getPixel(i, j).getRed() + "\n");
        write(fileWriter, image.getPixel(i, j).getGreen() + "\n");
        write(fileWriter, image.getPixel(i, j).getBlue() + "\n");
      }
    }
    try {
      fileWriter.close(); //flushes the data and indicates that there isn't any more data.
    } catch (IOException e) {
      //this should literally never be hit. The only way it is possible is it write() or flush()
      //get called on fileWriter again
      throw new IllegalStateException("Closing the FileWriter failed.");
    }
  }

  /**
   * Turns the given Image into a BufferedImage.
   *
   * @param image the image used to create the BufferedImage
   * @return a BufferedImage for the supplied Image
   */
  public static BufferedImage getBufferedImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    int width = image.getWidth();
    int height = image.getHeight();

    BufferedImage bufferedImage =
            new BufferedImage(width, height, TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixel(i, j).getRed();
        int green = image.getPixel(i, j).getGreen();
        int blue = image.getPixel(i, j).getBlue();
        int rgb = ((red & 0x0ff) << 16) | ((green & 0x0ff) << 8) | (blue & 0x0ff);
        bufferedImage.setRGB(j, i, rgb);
      }
    }

    return bufferedImage;
  }

  //helper method for writing to an Appendable. Will throw an IllegalStateException if an error
  //occurs when writing
  private static void write(Appendable appendable, String text) throws IllegalStateException {
    try {
      appendable.append(text);
    } catch (IOException e) {
      throw new IllegalStateException("Writing to the appendable failed.");
    }
  }
}
