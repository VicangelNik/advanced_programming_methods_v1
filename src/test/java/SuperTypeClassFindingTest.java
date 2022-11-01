import java.util.List;

import org.example.type.Graph;
import org.example.type.SuperTypeClassFinding;
import org.junit.jupiter.api.Test;

class SuperTypeClassFindingTest {

  private static List<String> packagesInputList = List.of("java.lang.Number",
                                                          "java.lang.Integer",
                                                          "java.lang.constant.Constable");

  @Test
  void getDeclaredTestCase() {

    Graph<String> graph = new Graph<>();
//
//    packagesInputList.forEach(graph::addNewVertex);

    final var superTypeClassFinding = new SuperTypeClassFinding(10);
    Graph.createGraph(graph, packagesInputList.get(1));
//    packagesInputList.forEach(pckClassName ->
//                                System.out.println(superTypeClassFinding.findSuperClassesFromClass(pckClassName)));

    System.out.println(graph);
  }
}
