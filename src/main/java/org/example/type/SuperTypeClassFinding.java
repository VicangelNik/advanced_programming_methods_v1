package org.example.type;

public class SuperTypeClassFinding {

  private final int topN;

  public SuperTypeClassFinding(int topN) {
    this.topN = topN;
  }

  public void findSuperTypesFromClass(Graph<String> graph, String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("4 ").append(pckClassName).append(" : ")
      .append(String.join(", ", graph.getVertex(pckClassName).stream().limit(topN).toList()))
      .append(System.lineSeparator());
  }
}
