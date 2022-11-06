package org.example.type;

import java.util.stream.Collectors;

public class SuperTypeClassFinding extends TypeClassFinding {

  public SuperTypeClassFinding(final int topN) {
    super(topN);
  }

  public void findTypeFromClass(Graph<String> graph, String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("4 ").append(pckClassName).append(" : ")
      .append(graph.getVertex(pckClassName).stream().limit(topN).collect(Collectors.joining(", ")))
      .append(System.lineSeparator());
  }
}
