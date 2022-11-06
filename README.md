# Advanced Programming Methods V1

It is an exercise that finds via reflection declared methods, fields, super types and subtypes of a class.

## Description
The application expects 3 parameters the input file, the output file and the number of results in this order.
output file can be created if it does not exist.

The home/1stProject.pdf file has the full description of this exercise.

## Installation
You can download the code via Github in your local directory
```bash
git clone https://github.com/VicangelNik/advanced_programming_methods_v1.git
```

Through you IDE you can run the application or with maven you can create the executable jar file.
```bash
mvn clean install
```

## Usage

Input file should have been created. and have values like the following:
```text
java.lang.String
java.lang.Integer
```
To execute the jar produced run:

```bash
java -jar advanced_programming_methods_v1-1.0-SNAPSHOT.jar ./input.txt ./output.txt 10
```