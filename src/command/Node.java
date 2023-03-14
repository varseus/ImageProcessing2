package command;

import java.util.LinkedList;
import java.util.concurrent.atomic.LongAdder;

import model.Pixel;
import model.PixelImpl;

/**
 * Represents a pixel location in an image, for use as a helper object
 * in mosaics.
 */
class Node {
  private final Node top;
  public Node bottom;
  private final Node left;
  public Node right;
  private final Pixel pixel;
  private LongAdder redCounter;
  private LongAdder blueCounter;
  private LongAdder greenCounter;
  private LongAdder pixelsInTile;
  public int i;
  public int j;

  /**
   * Creates this node with a left and top node,
   * and the pixel it represents.
   *
   * @param pixel pixel that his node represents
   * @param left  the left node
   * @param top   the top node
   */
  public Node(Pixel pixel, Node left, Node top) {
    this.left = left;
    this.top = top;
    if (left != null) {
      left.setRight(this);
    }
    if (top != null) {
      top.setBottom(this);
    }
    this.pixel = pixel;
    this.redCounter = null;
    this.greenCounter = null;
    this.blueCounter = null;
    this.pixelsInTile = null;
  }


  /**
   * Sets the node right of this one.
   *
   * @param right node to the right
   */
  private void setRight(Node right) {
    this.right = right;
  }

  /**
   * Sets the node below this one.
   *
   * @param bottom node below this one
   */
  private void setBottom(Node bottom) {
    this.bottom = bottom;
  }

  /**
   * Updates the pixel component counters for this node.
   *
   * @param redCounter   counter of red value of this tile
   * @param greenCounter counter of green value of this tile
   * @param blueCounter  counter of blue value of this tile
   * @return whether this node inherited anything
   */
  public boolean inheritCounters(LongAdder redCounter,
                                 LongAdder greenCounter,
                                 LongAdder blueCounter,
                                 LongAdder pixelsInTile) {
    if (this.redCounter == null && this.greenCounter == null && this.blueCounter == null) {
      redCounter.add(this.pixel.getRed());
      this.redCounter = redCounter;

      greenCounter.add(this.pixel.getGreen());
      this.greenCounter = greenCounter;

      blueCounter.add(this.pixel.getBlue());
      this.blueCounter = blueCounter;

      pixelsInTile.increment();
      this.pixelsInTile = pixelsInTile;

      return true;
    }

    return false;
  }

  public boolean inheritCounters() {
    return this.inheritCounters(new LongAdder(), new LongAdder(), new LongAdder(), new LongAdder());
  }

  public void mosaicBFS(LinkedList<Node> worklist) {
    Node[] neighbors = new Node[]{this.top, this.bottom, this.left, this.right};
    for (Node neighbor : neighbors) {
      if (neighbor != null &&
              neighbor.inheritCounters(
                      this.redCounter, this.greenCounter, this.blueCounter, this.pixelsInTile)) {
        worklist.add(neighbor);
      }
    }
  }

  /**
   * Creates a pixel represented by this node.
   *
   * @return the pixel represented by this node
   */
  public Pixel makePixel(int maxValue) {
    double pixelsInTile = this.pixelsInTile.doubleValue();
    return new PixelImpl((int) (this.redCounter.intValue() / pixelsInTile),
            (int) (this.greenCounter.intValue() / pixelsInTile),
            (int) (this.blueCounter.intValue() / pixelsInTile),
            maxValue);

  }
}