package edu.berkeley.cs.succinct.buffers;

import edu.berkeley.cs.succinct.SuccinctFile;
import edu.berkeley.cs.succinct.SuccinctFileTest;

import java.io.*;
import java.util.Arrays;

public class SuccinctFileBufferTest extends SuccinctFileTest {

  private String testFileRaw = this.getClass().getResource("/test_file").getFile();
  private String testFileSuccinct =
    this.getClass().getResource("/test_file").getFile() + ".buf.succinct";

  /**
   * Set up test.
   *
   * @throws Exception
   */
  public void setUp() throws Exception {
    super.setUp();

    File inputFile = new File(testFileRaw);

    fileData = new byte[(int) inputFile.length()];
    DataInputStream dis = new DataInputStream(new FileInputStream(inputFile));
    dis.readFully(fileData);
    sFile = new SuccinctFileBuffer(fileData);
  }

  public void testSerializeDeserialize() throws Exception {
    System.out.println("serializeDeserialize");

    // Serialize data
    FileOutputStream fOut = new FileOutputStream(testFileSuccinct);
    ObjectOutputStream oos = new ObjectOutputStream(fOut);
    oos.writeObject(sFile);
    oos.close();

    // Deserialize data
    FileInputStream fIn = new FileInputStream(testFileSuccinct);
    ObjectInputStream ois = new ObjectInputStream(fIn);
    SuccinctFile sBufRead = (SuccinctFileBuffer) ois.readObject();
    ois.close();

    assertNotNull(sBufRead);
    assertEquals(sFile.getSize(), sBufRead.getSize());
    assertTrue(Arrays.equals(sFile.extract(0, sFile.getSize()),
      sBufRead.extract(0, sBufRead.getSize())));
  }
}
