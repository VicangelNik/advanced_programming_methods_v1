package org.example.file;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.example.error.ApplicationException;

public class FileWriterHandler implements IFileWriterHandler {

  private final String outputFilePath;

  public FileWriterHandler(final String outputFilePath) {
    this.outputFilePath = outputFilePath;
  }

  /**
   * Creates the file if the file does not exist. If it exists, file will be overwritten.
   *
   * @param result of methods
   */
  @Override
  public void writeToFile(final String result) {
    try (final FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
         final FileWriter myWriter = new FileWriter(fileOutputStream.getFD())) {
      myWriter.write(result);
      myWriter.flush();
    } catch (IOException e) {
      throw new ApplicationException(e);
    }
  }
}

