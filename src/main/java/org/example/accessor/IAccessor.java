package org.example.accessor;

import java.lang.reflect.AccessibleObject;
import java.util.List;

public interface IAccessor {

  void getDeclared(final String pckClassName, StringBuilder stringBuilder);

  void getDeclaredAndInherited(final List<String> fieldList, final String pckClassName);
}
