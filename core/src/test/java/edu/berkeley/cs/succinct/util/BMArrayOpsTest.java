package edu.berkeley.cs.succinct.util;

import edu.berkeley.cs.succinct.bitmap.BMArray;
import edu.berkeley.cs.succinct.util.buffers.SerializedOperations;
import junit.framework.TestCase;

import java.nio.LongBuffer;

public class BMArrayOpsTest extends TestCase {

  /**
   * Set up test.
   *
   * @throws Exception
   */
  public void setUp() throws Exception {
    super.setUp();
  }

  /**
   * Test method: long getVal(LongBuffer B, int i, int bits)
   *
   * @throws Exception
   */
  public void testGetVal() throws Exception {
    System.out.println("getVal");

    BMArray bmArray = new BMArray(1000, 64);
    for (int i = 0; i < 1000; i++) {
      bmArray.setVal(i, i);
    }

    LongBuffer bBuf = bmArray.getLongBuffer();
    for (int i = 0; i < 1000; i++) {
      assertEquals(SerializedOperations.BMArrayOps.getVal(bBuf, i, 64), i);
    }

  }
}
