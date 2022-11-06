import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.example.accessor.MethodAccessorClass;
import org.example.file.ReadAllFilesFileReader;
import org.junit.jupiter.api.Test;

class MethodAccessorTest {

  private static final String inputFilePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "input.txt";

  @Test
  void getDeclaredTestCase() {
    System.out.println(inputFilePath);
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var methodAccessor = new MethodAccessorClass(10);
    StringBuilder stringBuilder = new StringBuilder();
//    fileHandler.readFile().forEach(pckClassName ->
//                                     Assertions.assertFalse(methodAccessor.getDeclared(pckClassName, stringBuilder).toString().isBlank())
//    );
  }

  @Test
  void getDeclaredAndInheritedTestCase() {
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var methodAccessor = new MethodAccessorClass(10);
    final List<String> methodList = new LinkedList<>();
    fileHandler.readFile().forEach(pckClassName -> {
      methodAccessor.getDeclaredAndInherited(methodList, pckClassName);
      methodList.forEach(method -> System.out.println("field = " + method));
    });
  }
}
