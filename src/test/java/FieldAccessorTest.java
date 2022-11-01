import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.List;

import org.example.accessor.FieldAccessor;
import org.example.file.ReadAllFilesFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FieldAccessorTest {

  private static final String inputFilePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "input.txt";

  @Test
  void getDeclaredTestCase() {
    System.out.println(inputFilePath);
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var fieldAccessor = new FieldAccessor(10);
    fileHandler.readFile().forEach(pckClassName ->
                                     Assertions.assertFalse(fieldAccessor.getDeclared(pckClassName).toString().isBlank()));
  }

  @Test
  void getDeclaredAndInheritedTestCase() {
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var fieldAccessor = new FieldAccessor(30);
    final List<? super AccessibleObject> fieldList = new LinkedList<>();
    fileHandler.readFile().forEach(pckClassName -> {
      fieldAccessor.getDeclaredAndInherited(fieldList, pckClassName);
      fieldList.forEach(field -> System.out.println("field = " + field));
    });
  }
}
