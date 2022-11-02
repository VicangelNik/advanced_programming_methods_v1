package org.example.accessor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.example.error.ApplicationException;

public final class FieldAccessor implements IAccessor {

  private final int topN;

  public FieldAccessor(final int topN) {
    this.topN = topN;
  }

  @Override
  public void getDeclared(String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("1a ").append(pckClassName).append(" : ");
    try {
      final Class<?> classObject = Class.forName(pckClassName);
      final List<String> fieldNameList = Stream.of(classObject.getDeclaredFields()).limit(topN).map(Field::getName).toList();
      stringBuilder.append(String.join(", ", fieldNameList)).append(System.lineSeparator());
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }

  @Override
  public void getDeclaredAndInherited(List<String> fieldList, String pckClassName) {
    try {
      Class<?> classObject = Class.forName(pckClassName);
      fieldList.addAll(Arrays.stream(classObject.getDeclaredFields()).map(Field::getName).limit(topN).toList());
      if (classObject.getSuperclass() != null && fieldList.size() < topN) {
        getDeclaredAndInherited(fieldList, classObject.getSuperclass().getTypeName());
      }
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }
}

