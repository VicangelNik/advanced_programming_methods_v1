package org.example.type;

public class SuperTypeClassFinding extends TypeClassFinding {

  public SuperTypeClassFinding(final int topN) {
    super(topN);
  }

  public void findTypeFromClass(Graph<String> graph, String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("4 ").append(pckClassName).append(" : ")
      .append(String.join(", ", graph.getVertex(pckClassName).stream().limit(topN).toList()))
      .append(System.lineSeparator());
  }
}
