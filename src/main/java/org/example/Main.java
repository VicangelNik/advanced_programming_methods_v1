package org.example;

import java.io.File;
import java.util.List;

import org.example.accessor.AccessorInheritedWrapper;
import org.example.accessor.FieldAccessorClass;
import org.example.accessor.AccessorClass;
import org.example.accessor.MethodAccessorClass;
import org.example.error.ApplicationException;
import org.example.file.FileWriterHandler;
import org.example.file.ReadAllFilesFileReader;
import org.example.type.Graph;
import org.example.type.SubTypeClassFinding;
import org.example.type.SuperTypeClassFinding;
import org.example.type.TypeClassFinding;

/**
 * Reflection is a feature in the Java programming language. It allows an executing Java program to examine or "introspect" upon itself, and manipulate internal properties of the program.
 * For example, it's possible for a Java class to obtain the names of all its members and display them.
 */
public class Main {

  private static int topN = 10;
  public static List<String> packagesInputList;

  public static void main(String[] args) {
    checkInputParameters(args);
    final var fileHandler = new ReadAllFilesFileReader(args[0]);
    packagesInputList = fileHandler.readFile();
    final var fileWriterHandler = new FileWriterHandler(args[1]);
    final var fieldAccessor = getAccessor("field");
    final var methodAccessor = getAccessor("method");
    final var fieldAccessorInheritedWrapper = new AccessorInheritedWrapper(fieldAccessor);
    final var methodAccessorInheritedWrapper = new AccessorInheritedWrapper(methodAccessor);
    final var superTypeClassFinding = getTypeClassFinding("super");
    final var subTypeClassFinding = getTypeClassFinding("sub");

    final Graph<String> graph = initGraph();

    final var stringBuilder = new StringBuilder();

    packagesInputList.forEach(pckClassName -> {

      fieldAccessor.getDeclared(pckClassName, stringBuilder);
      fieldAccessorInheritedWrapper.wrapCallGetDeclaredAndInherited(pckClassName, "1b ", stringBuilder);
      methodAccessor.getDeclared(pckClassName, stringBuilder);
      methodAccessorInheritedWrapper.wrapCallGetDeclaredAndInherited(pckClassName, "2b ", stringBuilder);
      subTypeClassFinding.findTypeFromClass(graph, pckClassName, stringBuilder);
      superTypeClassFinding.findTypeFromClass(graph, pckClassName, stringBuilder);
    });
    fileWriterHandler.writeToFile(stringBuilder.toString());
  }

  private static void checkInputParameters(final String[] args) {

    if (args.length != 3) {
      throw new ApplicationException("Invalid number of input parameters");
    }

    if (args[0] == null || !new File(args[0]).exists()) {
      throw new ApplicationException("input file does not exist");
    }

    if (args[1] == null || args[1].isBlank()) {
      throw new ApplicationException("output file does not exist");
    }

    try {
      topN = Integer.parseInt(args[2]);
    } catch (NumberFormatException ex) {
      throw new ApplicationException(ex);
    }
  }

  private static Graph<String> initGraph() {
    final Graph<String> graph = new Graph<>();
    packagesInputList.forEach(pckClassName -> Graph.createGraph(graph, pckClassName));
    return graph;
  }

  private static AccessorClass getAccessor(final String accessor) {
    if ("field".equals(accessor)) {
      return new FieldAccessorClass(topN);
    } else if ("method".equals(accessor)) {
      return new MethodAccessorClass(topN);
    } else {
      throw new ApplicationException("Given accessor could not be recognized");
    }
  }

  private static TypeClassFinding getTypeClassFinding(final String type) {
    if ("super".equals(type)) {
      return new SuperTypeClassFinding(topN);
    } else if ("sub".equals(type)) {
      return new SubTypeClassFinding(topN);
    } else {
      throw new ApplicationException("Given type could not be recognized");
    }
  }
}
