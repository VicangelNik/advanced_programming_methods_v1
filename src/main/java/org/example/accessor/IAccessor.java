package org.example.accessor;

import java.lang.reflect.AccessibleObject;
import java.util.List;

public interface IAccessor {

  StringBuilder getDeclared(final String pckClassName, StringBuilder stringBuilder);

  void getDeclaredAndInherited(final List<? super AccessibleObject> fieldList, final String pckClassName);
}
