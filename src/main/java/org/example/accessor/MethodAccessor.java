package org.example.accessor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.example.error.ApplicationException;

public final class MethodAccessor implements IAccessor {

  private final int topN;

  public MethodAccessor(final int topN) {
    this.topN = topN;
  }

  @Override
  public void getDeclared(String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("2a ").append(pckClassName).append(" : ");
    try {
      final Class<?> classObject = Class.forName(pckClassName);
      final List<String> methodNameList = Stream.of(classObject.getDeclaredMethods()).limit(topN).map(Method::getName).toList();
      stringBuilder.append(String.join(", ", methodNameList)).append(System.lineSeparator());
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }

  @Override
  public void getDeclaredAndInherited(List<String> fieldList, String pckClassName) {
    try {
      Class<?> classObject = Class.forName(pckClassName);
      fieldList.addAll(Arrays.stream(classObject.getDeclaredMethods()).limit(topN).map(Method::getName).toList());
      if (classObject.getSuperclass() != null && fieldList.size() < topN) {
        getDeclaredAndInherited(fieldList, classObject.getSuperclass().getTypeName());
      }
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }
}
