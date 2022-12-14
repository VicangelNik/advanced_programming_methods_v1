package org.example.type;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.example.Main.packagesInputList;

public class Graph<T> {

  private Map<T, Set<T>> edgesMap = new HashMap<>();

  public void addNewVertex(T s) {
    edgesMap.put(s, new HashSet<>());
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

  /**
   * Vertex in the graph indicates a package
   */
  public Set<T> getVertex(T key) {
    return edgesMap.getOrDefault(key, new HashSet<>());
  }

  public Map<T, Set<T>> getEdgesMap() {
    return edgesMap;
  }

  /**
   * @apiNote getInterfaces, getSuperclass are used instead of getGenericInterfaces, getGenericSuperclass
   * because the latter return the implementation (java.lang.Comparable<java.lang.Integer>)
   * but we want just the java.lang.Comparable in order to be recognized by Class.forName
   */
  public static void createGraph(Graph<String> graph, String pckClassName) {
    if (isPackageUnderTestValid(pckClassName)) {
      try {
        Class<?> classObject = Class.forName(pckClassName);

        // the class that our input/given class extends (supertype of our class)
        final Class<?> finalClassObject = classObject;
        Optional.ofNullable(classObject.getSuperclass())
          .ifPresent(superClass -> graph.addNewEdge(finalClassObject.getTypeName(), superClass.getTypeName()));

        // the interfaces that our input/given class implements (supertypes of our class)
        final Class<?> finalClassObject1 = classObject;
        Arrays.stream(classObject.getInterfaces())
          .map(Type::getTypeName)
          .forEach(interfaceTypeName -> graph.addNewEdge(finalClassObject1.getTypeName(), interfaceTypeName));

        // add to graph super and sub-types of given class' interfaces
        Arrays.stream(classObject.getInterfaces()).map(Type::getTypeName)
          .forEach(interfaceTypeName -> createGraph(graph, interfaceTypeName));

        while (classObject.getSuperclass() != null) {
          // add to graph class' superclass
          createGraph(graph, classObject.getSuperclass().getTypeName());
          classObject = Class.forName(classObject.getSuperclass().getTypeName());
        }
      } catch (ClassNotFoundException e) {
        System.out.println("ClassNotFoundException In: " + pckClassName);
      }
    }
  }

  /**
   * As the exercise suggests classes that are NOT included in jdk or input file should be skipped.
   *
   * @return boolean
   */
  private static boolean isPackageUnderTestValid(final String pckClassName) {
    return pckClassName.startsWith("sun.") || pckClassName.startsWith("com.sun.") || pckClassName.startsWith("java.")
           || pckClassName.startsWith("javax.") || pckClassName.startsWith("jdk.internal.")
           || packagesInputList.contains(pckClassName);
  }

  @Override
  public String toString() {
    final var builder = new StringBuilder();
    //foreach loop that iterates over the keys
    edgesMap.forEach((T key, Set<T> value) -> {
                       builder.append(key.toString()).append(": ");
                       //foreach loop for getting the vertices
                       value.forEach((T w) -> builder.append(w.toString()).append(" "));
                       builder.append("\n");
                     }
    );
    return builder.toString();
  }
}
