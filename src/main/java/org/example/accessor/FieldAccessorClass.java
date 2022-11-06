package org.example.accessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.error.ApplicationException;

/**
 * @author Nikiforos Xylogiannopoulos
 * @apiNote classObject.getFields() returns only public fields but classObject.getDeclaredFields() returns all fields
 * Also Field::toString instead of Field::getName is used to write the full signature of a field.
 */
public final class FieldAccessorClass extends AccessorClass {

  public FieldAccessorClass(final int topN) {
    super(topN);
  }

  @Override
  public void getDeclared(String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("1a ").append(pckClassName).append(" : ");
    try {
      final Class<?> classObject = Class.forName(pckClassName);
      final List<String> fieldNameList = Stream.of(classObject.getDeclaredFields()).limit(topN).map(Field::toString)
        .collect(Collectors.toList());
      stringBuilder.append(String.join(", ", fieldNameList)).append(System.lineSeparator());
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }

  @Override
  public void getDeclaredAndInherited(List<String> fieldList, String pckClassName) {
    try {
      Class<?> classObject = Class.forName(pckClassName);
      fieldList.addAll(Arrays.stream(classObject.getDeclaredFields()).map(Field::toString).limit(topN).collect(Collectors.toList()));
      if (classObject.getSuperclass() != null && fieldList.size() < topN) {
        getDeclaredAndInherited(fieldList, classObject.getSuperclass().getTypeName());
      }
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }
}

