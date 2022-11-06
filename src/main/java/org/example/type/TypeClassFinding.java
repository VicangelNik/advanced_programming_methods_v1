package org.example.type;

/**
 * @author Nikiforos Xylogiannopoulos
 */
public abstract class TypeClassFinding {

  protected final int topN;

  protected TypeClassFinding(int topN) {
    this.topN = topN;
  }

  public abstract void findTypeFromClass(final Graph graph,
                                         final String pckClassName,
                                         final StringBuilder stringBuilder);
}
