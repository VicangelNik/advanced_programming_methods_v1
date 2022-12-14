import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.example.accessor.FieldAccessorClass;
import org.example.file.ReadAllFilesFileReader;
import org.junit.jupiter.api.Test;

class FieldAccessorTest {

  private static final String inputFilePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "input.txt";

  @Test
  void getDeclaredTestCase() {
    System.out.println(inputFilePath);
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var fieldAccessor = new FieldAccessorClass(10);
    StringBuilder stringBuilder = new StringBuilder();
//    fileHandler.readFile().forEach(pckClassName ->
//                                     Assertions.assertFalse(fieldAccessor.getDeclared(pckClassName, stringBuilder).toString().isBlank()));
  }

  @Test
  void getDeclaredAndInheritedTestCase() {
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var fieldAccessor = new FieldAccessorClass(30);
    final List<String> fieldList = new LinkedList<>();
    fileHandler.readFile().forEach(pckClassName -> {
      fieldAccessor.getDeclaredAndInherited(fieldList, pckClassName);
      fieldList.forEach(field -> System.out.println("field = " + field));
    });
  }
}
