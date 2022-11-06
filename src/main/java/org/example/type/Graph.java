package org.example.type;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.example.error.ApplicationException;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class Graph {

  private Map<String, Set<String>> edgesMap = new HashMap<>();

  private List<String> packagesInputList = new ArrayList<>();

  public Graph(List<String> packagesInputList) {
    this.packagesInputList = packagesInputList;
    packagesInputList.forEach(this::fillGraphFromPackage);
    getClassInfoSet().stream().map(ClassInfo::getName).forEach(this::fillGraphFromPackage);
  }

  private Set<ClassInfo> getClassInfoSet() {
    ClassPath classPath = null;
    final Set<ClassInfo> classInfoSet = new HashSet<>();
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      while (classLoader != null) {
        classPath = ClassPath.from(classLoader);
        classInfoSet.addAll(classPath.getAllClasses().stream()
                              .filter(classInfo -> isPackageUnderTestValid(classInfo.getPackageName())).collect(Collectors.toSet()));
        classLoader = classLoader.getParent();
      }
    } catch (IOException e) {
      throw new ApplicationException(e);
    }
    return classInfoSet;
  }

  public void addNewVertex(String s) {
    edgesMap.put(s, new HashSet<>());
  }

  public void addNewEdge(String source, String destination) {
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
  public Set<String> getVertex(String key) {
    return edgesMap.getOrDefault(key, new HashSet<>());
  }

  public Map<String, Set<String>> getEdgesMap() {
    return edgesMap;
  }

  /**
   * @apiNote getInterfaces, getSuperclass are used instead of getGenericInterfaces, getGenericSuperclass
   * because the latter return the implementation (java.lang.Comparable<java.lang.Integer>)
   * but we want just the java.lang.Comparable in order to be recognized by Class.forName
   */
  private void fillGraphFromPackage(String pckClassName) {
    if (isPackageUnderTestValid(pckClassName)) {
      try {
        Class<?> classObject = Class.forName(pckClassName);

        // the class that our input/given class extends (supertype of our class)
        final Class<?> finalClassObject = classObject;
        Optional.ofNullable(classObject.getSuperclass())
          .ifPresent(superClass -> addNewEdge(finalClassObject.getTypeName(), superClass.getTypeName()));

        // the interfaces that our input/given class implements (supertypes of our class)
        final Class<?> finalClassObject1 = classObject;
        Arrays.stream(classObject.getInterfaces())
          .map(Type::getTypeName)
          .forEach(interfaceTypeName -> addNewEdge(finalClassObject1.getTypeName(), interfaceTypeName));

        // add to graph super and sub-types of given class' interfaces
        Arrays.stream(classObject.getInterfaces()).map(Type::getTypeName)
          .forEach(this::fillGraphFromPackage);

        while (classObject.getSuperclass() != null) {
          // add to graph class' superclass
          fillGraphFromPackage(classObject.getSuperclass().getTypeName());
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
  private boolean isPackageUnderTestValid(final String pckClassName) {
    return pckClassName.startsWith("sun.") || pckClassName.startsWith("com.sun.") || pckClassName.startsWith("java.")
           || pckClassName.startsWith("javax.") || pckClassName.startsWith("jdk.internal.")
           || pckClassName.startsWith("org.example.") || packagesInputList.contains(pckClassName);
  }

  @Override
  public String toString() {
    final var builder = new StringBuilder();
    //foreach loop that iterates over the keys
    edgesMap.forEach((String key, Set<String> value) -> {
                       builder.append(key).append(": ");
                       //foreach loop for getting the vertices
                       value.forEach((String w) -> builder.append(w).append(" "));
                       builder.append("\n");
                     }
    );
    return builder.toString();
  }
}
