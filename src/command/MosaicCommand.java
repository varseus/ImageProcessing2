package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * A command for adding a mosaic effect to the image,
 * with a given number of seeds (the number of tiles in the mosiac).
 */
public class MosaicCommand implements IMECommand {
  int seeds;
  Random random;

  /**
   * Creates this Mosaic Command with the given number of seeds.
   * Uses the given random object to select seeds.
   *
   * @param seeds  number of tiles in this mosaic
   * @param random the random object to select seeds
   * @throws IllegalArgumentException if invalid number of seeds
   */
  public MosaicCommand(int seeds, Random random) {
    this.seeds = seeds;
    this.random = Objects.requireNonNull(random);

    if (seeds <= 0) {
      throw new IllegalArgumentException("Cannot make a mosaic with less than 1 seed.");
    }
  }

  /**
   * Creates this Mosaic Command with the given number of seeds.
   * Uses a new random object to select seeds.
   *
   * @param seeds number of tiles in this mosaic
   * @throws IllegalArgumentException if the image does not have enough pixels for the requested
   *                                  amount of seeds
   */
  public MosaicCommand(int seeds) {
    this(seeds, new Random());
  }

  @Override
  public Image execute(Image inputImage) {
    int height = inputImage.getHeight();
    int width = inputImage.getWidth();
    int maxValue = inputImage.getMaxValue();

    if (width * height < this.seeds) {
      throw new IllegalArgumentException("Cannot make mosaic with more seeds than pixels.");
    }

    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Integer> indicesForRandomSelection = new ArrayList<>();
    LinkedList<Node> worklist = new LinkedList<>();

    // create graph of pixels, represented by Nodes
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Node left = null;
        Node top = null;

        if (i > 0) {
          top = nodes.get((i - 1) * width + j);
        }
        if (j > 0) {
          left = nodes.get(i * width + j - 1);
        }

        nodes.add(new Node(inputImage.getPixel(i, j), left, top));
        indicesForRandomSelection.add(i * width + j);
      }
    }


    // get seeds
    Collections.shuffle(indicesForRandomSelection, this.random);
    for (int i = 0; i < this.seeds; i++) {
      Node seed = nodes.get(indicesForRandomSelection.remove(indicesForRandomSelection.size() - 1));
      seed.inheritCounters();
      worklist.add(seed);
    }

    // breadth first search from seeds to create tiles
    while (worklist.size() > 0) {
      worklist.remove().mosaicBFS(worklist);
    }

    // construct image from nodes
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = nodes.get(i * width + j).makePixel(maxValue);
      }
    }

    return new ImageImpl(width, height, maxValue, pixels);
  }
}
