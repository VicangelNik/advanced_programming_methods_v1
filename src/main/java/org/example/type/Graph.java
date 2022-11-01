package org.example.type;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.example.Main.packagesInputList;

public class Graph<T> {

  private Map<T, List<T>> edgesMap = new HashMap<>();

  public void addNewVertex(T s) {
    edgesMap.put(s, new LinkedList<>());
  }

  public void addNewEdge(T source, T destination, boolean bidirectional) {
    if (!edgesMap.containsKey(source)) {
      addNewVertex(source);
    }
    if (!edgesMap.containsKey(destination)) {
      addNewVertex(destination);
    }
    edgesMap.get(source).add(destination);
    if (bidirectional) {
      edgesMap.get(destination).add(source);
    }
  }

  public void addNewEdge(T source, T destination) {
    if (!edgesMap.containsKey(source)) {
      addNewVertex(source);
    }
    if (!edgesMap.containsKey(destination)) {
      addNewVertex(destination);
    }
    edgesMap.get(source).add(destination);
  }

  public boolean containsVertex(T key) {
    return edgesMap.containsKey(key);
  }

  public static Graph<String> createGraph(Graph<String> graph, String pckClassName) {
    if (isPackageUnderTestValid(pckClassName)) {
      try {
        Class<?> classObject = Class.forName(pckClassName);
       // System.out.println(classObject.getName());
        // the class that our input/given class extends (supertype of our class)
        Class<?> finalClassObject = classObject;
        Optional.ofNullable(classObject.getGenericSuperclass())
          .ifPresent(genericSuperClass -> graph.addNewEdge(finalClassObject.getTypeName(), genericSuperClass.getTypeName()));
        // the interfaces that our input/given class implements (supertypes of our class)
        Class<?> finalClassObject1 = classObject;
        Arrays.stream(classObject.getGenericInterfaces()).map(Type::getTypeName)
          .forEach(genericInterfaceTypeName -> graph.addNewEdge(finalClassObject1.getTypeName(), genericInterfaceTypeName));

        Arrays.stream(classObject.getGenericInterfaces()).map(Type::getTypeName)
          .forEach(genericInterfaceTypeName -> createGraph(graph, genericInterfaceTypeName));

        while (classObject.getGenericSuperclass() != null) {
          createGraph(graph, classObject.getGenericSuperclass().getTypeName());
          classObject = Class.forName(classObject.getGenericSuperclass().getTypeName());
        }
      } catch (ClassNotFoundException e) {
        //  throw new RuntimeException(e);
        System.out.println("ClassNotFoundException In: " + pckClassName);
      }
    }
    return graph;
  }

  /**
   * As the exercise suggests classes that are not included in jdk or input file should be skipped.
   *
   * @return
   */
  private static boolean isPackageUnderTestValid(final String pckClassName) {
    return pckClassName.startsWith("sun.") || pckClassName.startsWith("com.sun.") || pckClassName.startsWith("java.")
           || pckClassName.startsWith("javax.") || pckClassName.startsWith("jdk.internal.")
           || packagesInputList.contains(pckClassName);
  }
}
