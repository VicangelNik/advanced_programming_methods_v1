package org.example;

import java.io.File;
import java.util.List;

import org.example.accessor.AccessorInheritedWrapper;
import org.example.accessor.FieldAccessor;
import org.example.accessor.MethodAccessor;
import org.example.error.ApplicationException;
import org.example.file.FileWriterHandler;
import org.example.file.ReadAllFilesFileReader;
import org.example.type.Graph;
import org.example.type.SuperTypeClassFinding;

public class Main {

  private static int topN = 10;
  public static List<String> packagesInputList;

  public static void main(String[] args) {
    checkInputParameters(args);
    final var fileHandler = new ReadAllFilesFileReader(args[0]);
    packagesInputList = fileHandler.readFile();
    final var fileWriterHandler = new FileWriterHandler(args[1]);
    final var fieldAccessor = new FieldAccessor(topN);
    final var methodAccessor = new MethodAccessor(topN);
    final var fieldAccessorInheritedWrapper = new AccessorInheritedWrapper(fieldAccessor);
    final var methodAccessorInheritedWrapper = new AccessorInheritedWrapper(fieldAccessor);
    final var superTypeClassFinding = new SuperTypeClassFinding(topN);

    Graph<String> graph = new Graph<>();
    packagesInputList.forEach(pckClassName ->
                                Graph.createGraph(graph, pckClassName));
    final var stringBuilder = new StringBuilder();

    packagesInputList.forEach(pckClassName -> {

      fieldAccessor.getDeclared(pckClassName, stringBuilder);
      fieldAccessorInheritedWrapper.getStringBuilder(pckClassName, "1b ", stringBuilder);
      methodAccessor.getDeclared(pckClassName, stringBuilder);
      methodAccessorInheritedWrapper.getStringBuilder(pckClassName, "2b ", stringBuilder);
      superTypeClassFinding.findSuperTypesFromClass(graph, pckClassName, stringBuilder);

      fileWriterHandler.writeToFile(stringBuilder.toString());
    });

//
//    packagesInputList.forEach(pckClassName ->
//
//  );

  }

  private static void checkInputParameters(final String[] args) {

    if (args.length != 3) {
      throw new ApplicationException("Invalid number of input parameters");
    }

    if (args[0] == null || !new File(args[0]).exists()) {
      throw new ApplicationException("input file does not exist");
    }

    if (args[1] == null || !new File(args[1]).exists()) {
      throw new ApplicationException("output file does not exist");
    }
    try {
      topN = Integer.parseInt(args[2]);
    } catch (NumberFormatException ex) {
      throw new ApplicationException(ex);
    }
  }
}
