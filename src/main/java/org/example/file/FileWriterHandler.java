package org.example.file;

import java.io.FileWriter;
import java.io.IOException;

import org.example.error.ApplicationException;

public class FileWriterHandler implements IFileWriterHandler {

  private final String outputFilePath;

  public FileWriterHandler(final String outputFilePath) {
    this.outputFilePath = outputFilePath;
  }

  @Override
  public void writeToFile(final String result) {
    try (FileWriter myWriter = new FileWriter(outputFilePath)) {
      myWriter.write(result);
      myWriter.flush();
    } catch (IOException e) {
      throw new ApplicationException(e);
    }
  }
}

