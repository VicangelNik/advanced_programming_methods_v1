package org.example.accessor;

import java.util.List;

public abstract class AccessorClass {

  protected final int topN;

  protected AccessorClass(int topN) {
    this.topN = topN;
  }

  public abstract void getDeclared(final String pckClassName, StringBuilder stringBuilder);

  public abstract void getDeclaredAndInherited(final List<String> fieldList, final String pckClassName);
}
