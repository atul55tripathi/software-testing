# Description

* This project tests the coverage of PIT mutations 
using test cases written in JUNIT.

* Test cases are written for a Linear Algebra Library

* There are two base classes for this Library, one is Matrix.java and another is Vector.java

* Concepts learned- Unit testing and Mutation Testing

* Tools learned- JUnit, MAVEN and PIT


# Output

![](./src/screenshots/s1.png)

![](./src/screenshots/s2.png)

# Steps to run project

* mvn clean
* mvn compile
* java -cp ./target/project-1.0-SNAPSHOT.jar com/example.Main

* mvn test
* mvn -DwithHistory test-compile org.pitest:pitest-maven:mutationCoverage

# Main Source File

* the main file is written in java
* it can be run saparately, without running the tests on it
* the open source contributer for the file is https://github.com/danhales/linearalgebra


# Test Files

* there are two test files written one for matrix function testing and other for vector function testing.
* the files contain tests written in JUnit.
* futher results of PIT are shown above in screenshots.

# Matrix.java

* contains all the baisc and advanced operations on matrix
* Some unary operations are
determinant, isDiagonal, isLowerTriangular, isSparse, swapColumns, transpose and isSquare.
* some Binary operations are add, subtract and multiply.

# Vector.java

* contains all the basic and advanced opertaions on vector.
* some unary operations on vectors are inversevector, iszero, length, magnitude,
and normalize.
* some binary operations on vectors are add, cross, dot and subtract.

