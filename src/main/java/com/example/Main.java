
package com.example;

public class Main {

    public static void main(String[] args) {

        // Create new matrices
        double[][] matrix1Entries = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        double[][] matrix2Entries = {
                { 9, 8, 7 },
                { 6, 5, 4 },
                { 3, 2, 1 }
        };


        Matrix matrix1 = new Matrix(matrix1Entries);
        Matrix matrix2 = new Matrix(matrix2Entries);

        // printing created matrix through classes
        System.out.println("Matrix 1:");
        System.out.println(matrix1);

        System.out.println("Matrix 2:");
        System.out.println(matrix2);

        // Adding matrix through inbuilt class functions
        System.out.println("Matrix 1 + Matrix 2:");
        System.out.println(matrix1.add(matrix2));

        // Multiplying matrices
        System.out.println("Matrix 1 * Matrix 2:");
        System.out.println(matrix1.multiply(matrix2));

        // calculation of transpose
        System.out.println("Transpose of Matrix 1:");
        System.out.println(matrix1.transpose());

        // finding the determinant
        System.out.println("Determinant of Matrix 1:");
        System.out.println(matrix1.determinant());


        // creating new vector object using another class, and initializing them as follows
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        // printing their values
        System.out.println("Vector v1: " + v1);
        System.out.println("Vector v2: " + v2);

        // their sum
        Vector vAdd = v1.add(v2);
        System.out.println("v1 + v2: " + vAdd);

      
        // whats the magnitude?
        System.out.println("Magnitude of v1: " + v1.magnitude());

        // nomvalized form
        System.out.println("Normalized v1: " + v1.normalize());

        // is the vector a zero vector??
        System.out.println("Is v1 a zero vector? " + v1.isZero());

        // finding the inverse of the vector
        System.out.println("Inverse of v1: " + v1.inverseVector());
    }
}
