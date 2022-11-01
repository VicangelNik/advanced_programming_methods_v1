package org.example.type;

public class SuperTypeClassFinding {

  private final int topN;

  public SuperTypeClassFinding(int topN) {
    this.topN = topN;
  }

  public StringBuilder findSuperTypesFromClass(Graph<String> graph, String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("4 ").append(pckClassName).append(" : ");
    return stringBuilder.append(String.join(", ", graph.getVertex(pckClassName).stream().limit(topN).toList()))
      .append(System.lineSeparator());
  }
}
