package histogramtest;

import org.junit.Test;

import java.util.Map;

import utils.IOUtils;
import view.Histogram;
import view.HistogramImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * A class containing tests for the HistogramImpl class.
 */
public class HistogramTest {

  @Test
  public void testHistogramConstructor() {
    assertThrows("Image cannot be null.", IllegalArgumentException.class,
        () -> new HistogramImpl(null));
  }

  @Test
  public void testHistogram() {
    Histogram histogram = new HistogramImpl(IOUtils.load("test/modeltest/testImage.ppm"));
    Map<Integer, int[]> componentCounts = histogram.getContents();
    assertEquals(256, componentCounts.size());
    assertEquals(1, componentCounts.get(243)[0]);
    assertEquals(1, componentCounts.get(32)[1]);
    assertEquals(1, componentCounts.get(45)[2]);
    assertEquals(1, componentCounts.get(106)[3]);

    assertEquals(1, componentCounts.get(78)[0]);
    assertEquals(1, componentCounts.get(132)[1]);
    assertEquals(1, componentCounts.get(90)[2]);
    assertEquals(1, componentCounts.get(100)[3]);

    assertEquals(1, componentCounts.get(20)[0]);
    assertEquals(1, componentCounts.get(240)[1]);
    assertEquals(1, componentCounts.get(18)[2]);
    assertEquals(1, componentCounts.get(92)[3]);

    assertEquals(1, componentCounts.get(54)[0]);
    assertEquals(1, componentCounts.get(54)[1]);
    assertEquals(1, componentCounts.get(255)[2]);
    assertEquals(1, componentCounts.get(121)[3]);

    //all the other spots in the map should still be 0 - we will not test every one (obviously), but
    //we will test a few
    assertEquals(0, componentCounts.get(0)[0]);
    assertEquals(0, componentCounts.get(0)[1]);
    assertEquals(0, componentCounts.get(0)[2]);
    assertEquals(0, componentCounts.get(0)[3]);

    assertEquals(0, componentCounts.get(122)[0]);
    assertEquals(0, componentCounts.get(122)[1]);
    assertEquals(0, componentCounts.get(122)[2]);
    assertEquals(0, componentCounts.get(122)[3]);
  }
}
