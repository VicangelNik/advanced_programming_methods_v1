package org.example.type;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

import org.example.error.ApplicationException;

public class SuperTypeClassFinding {

  private final int topN;

  public SuperTypeClassFinding(int topN) {
    this.topN = topN;
  }



  public StringBuilder findSuperClassesFromClass(String pckClassName) {
    final var stringBuilder = new StringBuilder("4: ");
    try {
      Class<?> classObject = Class.forName(pckClassName);
      int counter = topN;
      String separator = "";

      while (classObject.getSuperclass() != null && --counter >= 0) {

        Class<?> superClass = classObject.getSuperclass();

        stringBuilder.append(separator).append(superClass.getName());
        separator = ", ";
        classObject = superClass;
      }
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
    return stringBuilder.append(System.lineSeparator());
  }
}
