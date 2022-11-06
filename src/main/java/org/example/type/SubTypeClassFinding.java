package org.example.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SubTypeClassFinding extends TypeClassFinding {

  public SubTypeClassFinding(final int topN) {
    super(topN);
  }

  public void findTypeFromClass(final Graph graph,
                                final String pckClassName,
                                final StringBuilder stringBuilder) {
    stringBuilder.append("3 ").append(pckClassName).append(" : ");
    final List<String> subTypeList = new ArrayList<>();
    graph.getEdgesMap().forEach((String key, Set<String> valueSet) -> {
      if (valueSet.contains(pckClassName)) {
        subTypeList.add(key);
      }
    });
    stringBuilder.append(subTypeList.stream().limit(topN).collect(Collectors.joining(", "))).append(System.lineSeparator());
  }
}
