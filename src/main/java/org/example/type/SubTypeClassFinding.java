package org.example.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SubTypeClassFinding extends TypeClassFinding {

  public SubTypeClassFinding(final int topN) {
    super(topN);
  }

  public void findTypeFromClass(final Graph<String> graph,
                                final String pckClassName,
                                final StringBuilder stringBuilder) {
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
