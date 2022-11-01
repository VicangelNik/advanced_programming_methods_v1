package org.example.type;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.example.error.ApplicationException;

public class SubClassFinding {

  public void sddssd(String pckClassName) {
    try {
      Class<?> classObject = Class.forName(pckClassName);

      Package[] pcks = Package.getPackages();
     // for (int i = 0; i < pcks.length; i++) {
        try {
          List<Class<?>> classes =  getClassesForPackage("java.lang");
          classes.parallelStream().forEach(clazz -> {
            if (clazz.isInstance(classObject)) {
              System.out.println(clazz.getName());
            }
          });
        } catch (IOException e) {
          // nothing to do
        } catch (URISyntaxException e) {
          // nothing to do
        }
     // }
    } catch (ClassNotFoundException e) {
      throw new ApplicationException(e);
    }
  }

  public static List<Class<?>> getClassesForPackage(final String pkgName) throws IOException, URISyntaxException {
    final String pkgPath = pkgName.replace('.', '/');
    final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
    final ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();

    Path root;
    if (pkg.toString().startsWith("jar:")) {
      try {
        root = FileSystems.getFileSystem(pkg).getPath(pkgPath);
      } catch (final FileSystemNotFoundException e) {
        root = FileSystems.newFileSystem(pkg, Collections.emptyMap()).getPath(pkgPath);
      }
    } else {
      root = Paths.get(pkg);
    }

    final String extension = ".class";
    try (final Stream<Path> allPaths = Files.walk(root)) {
      allPaths.filter(Files::isRegularFile).forEach(file -> {
        try {
          final String path = file.toString().replace('/', '.');
          final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
          allClasses.add(Class.forName(name));
        } catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
        }
      });
    }
    return allClasses;
  }
}
