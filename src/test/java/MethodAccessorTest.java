import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.List;

import org.example.accessor.MethodAccessor;
import org.example.file.ReadAllFilesFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MethodAccessorTest {

  private static final String inputFilePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "input.txt";

  @Test
  void getDeclaredTestCase() {
    System.out.println(inputFilePath);
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var methodAccessor = new MethodAccessor(10);
    fileHandler.readFile().forEach(pckClassName ->
                                     Assertions.assertFalse(methodAccessor.getDeclared(pckClassName).toString().isBlank())
    );
  }

  @Test
  void getDeclaredAndInheritedTestCase() {
    final var fileHandler = new ReadAllFilesFileReader(inputFilePath);
    final var methodAccessor = new MethodAccessor(10);
    final List<? super AccessibleObject> methodList = new LinkedList<>();
    fileHandler.readFile().forEach(pckClassName -> {
      methodAccessor.getDeclaredAndInherited(methodList, pckClassName);
      methodList.forEach(method -> System.out.println("field = " + method));
    });
  }
}
