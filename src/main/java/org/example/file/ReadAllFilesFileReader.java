package org.example.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.example.error.ApplicationException;

public class ReadAllFilesFileReader implements IFileReaderHandler {

  private final String filePath;

  public ReadAllFilesFileReader(final String filePath) {
    this.filePath = filePath;
  }

  public List<String> readFile() {
    try {
      return Files.readAllLines(Paths.get(filePath));
    } catch (IOException e) {
      throw new ApplicationException(e);
    }
  }
}
