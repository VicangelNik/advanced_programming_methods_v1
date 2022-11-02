package org.example.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SubTypeClassFinding {

  private final int topN;

  public SubTypeClassFinding(int topN) {
    this.topN = topN;
  }

  public void findSubTypesFromClass(Graph<String> graph, String pckClassName, StringBuilder stringBuilder) {
    stringBuilder.append("3 ").append(pckClassName).append(" : ");
    final List<String> subTypeList = new ArrayList<>();
    graph.getEdgesMap().forEach((String key, Set<String> valueSet) -> {
      if (valueSet.contains(pckClassName)) {
        subTypeList.add(key);
      }
    });
    stringBuilder.append(String.join(", ", subTypeList.stream().limit(topN).toList())).append(System.lineSeparator());
  }
}
