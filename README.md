# Advanced Programming Methods V1

It is an exercise that finds via reflection declared methods, fields, super types and subtypes of a class.

## Description
The application expects 3 parameters· the input file, the output file and the number of results in this order.
output file can be created if it does not exist.

The home/1stProject.pdf file has the full description of this exercise.

## Installation
You can download the code via Github in your local directory
```bash
git clone https://github.com/VicangelNik/advanced_programming_methods_v1.git
```

Through you IDE you can run the application or with maven you can create the executable jar file.
```bash
mvn clean compile assembly:single
```

## Usage

Input file should have been created. and have values like the following:
```text
java.lang.String
java.lang.Integer
```
To execute the jar produced run:

```bash
java -jar advanced_programming_methods_v1-1.0-SNAPSHOT-jar-with-dependencies.jar ./input.txt ./output.txt 10
```

## References
- stackoverflow: https://stackoverflow.com/questions/2548384/java-get-a-list-of-all-classes-loaded-in-the-jvm
- baeldung: https://www.baeldung.com/java-list-classes-class-loader
- javatpoint: https://www.javatpoint.com/java-graph
- refactoring.guru: https://refactoring.guru/design-patterns/abstract-factory/java/example
- udaniweeraratne: https://udaniweeraratne.wordpress.com/2016/01/21/how-to-generate-a-manifest-using-maven/
- docs.oracle.com: https://docs.oracle.com/en/java/javase/14/docs/api/java.instrument/java/lang/instrument/package-summary.html
- docs.oracle.com: https://docs.oracle.com/en/java/javase/14/docs/api/java.instrument/java/lang/instrument/package-summary.html
- https://stackoverflow.com/a/39268946/5671924

https://github.com/eugenp/tutorials/blob/61da4cb26e9e3ac091f53a88b4d3372f79bae8a6/core-java-modules/core-java-jvm/src/main/java/com/baeldung/instrumentation/agent/AtmTransformer.java#L16
https://www.jrebel.com/blog/how-write-javaagent
https://github.com/zeroturnaround/callspy/