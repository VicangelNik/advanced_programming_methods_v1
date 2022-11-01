package org.example.accessor;

import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.List;

public class AccessorInheritedWrapper {

  private final IAccessor iAccessor;

  public AccessorInheritedWrapper(IAccessor iAccessor) {
    this.iAccessor = iAccessor;
  }

  public StringBuilder getStringBuilder(String pckClassName, String exId) {
    final List<? super AccessibleObject> fieldList = new LinkedList<>();
    iAccessor.getDeclaredAndInherited(fieldList, pckClassName);
    final var builder = new StringBuilder(exId);
    return builder.append(String.join(", ", fieldList.stream().map(Object::toString).toList())).append(System.lineSeparator());
  }
}
