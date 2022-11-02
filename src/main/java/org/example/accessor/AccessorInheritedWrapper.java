package org.example.accessor;

import java.util.LinkedList;
import java.util.List;

public class AccessorInheritedWrapper {

  private final IAccessor iAccessor;

  public AccessorInheritedWrapper(IAccessor iAccessor) {
    this.iAccessor = iAccessor;
  }

  public void wrapCallGetDeclaredAndInherited(String pckClassName, String exId, StringBuilder stringBuilder) {
    final List<String> fieldList = new LinkedList<>();
    iAccessor.getDeclaredAndInherited(fieldList, pckClassName);
    stringBuilder.append(exId).append(pckClassName).append(" : ").append(String.join(", ", fieldList.stream()
      .map(Object::toString).toList())).append(System.lineSeparator());
  }
}
