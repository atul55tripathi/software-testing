package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class MatrixTest {

    private double EPSILON = 0.00001;

    // yaha constructor test honge
    @Test
    public void verifyConstructor() {
        double[][] data = { { 3, 5 }, { 6, 7 } };
        Matrix matrix = new Matrix(data);
        assertArrayEquals(data, matrix.getEntries());
    }

    // Test for copy constructor
    @Test
    public void verifyCopyConstructor() {
        double[][] data = { { 1, 4 }, { 2, 5 } };
        Matrix original = new Matrix(data);
        Matrix copy = new Matrix(original);
        assertArrayEquals(original.getEntries(), copy.getEntries());
    }

    // Test for minor matrix generation
    @Test
    public void checkMinorMatrix() {
        double[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Matrix matrix = new Matrix(data);
        Matrix minor = matrix.minorMatrix(1, 1);
        assertArrayEquals(new double[][] { { 1, 3 }, { 7, 9 } }, minor.getEntries());
    }

    // Test for invalid arguments in minor matrix
    @Test(expected = IllegalArgumentException.class)
    public void checkInvalidMinorMatrixRow() {
        double[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Matrix matrix = new Matrix(data);
        matrix.minorMatrix(3, 1);
    }

    // Test for matrix creation using column vectors
    @Test
    public void verifyColumnVectorMatrixCreation() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(4, 5, 6);
        Matrix matrix = Matrix.fromColumnVectors(vector1, vector2);
        assertArrayEquals(new double[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } }, matrix.getEntries());
    }

    // Test for matrix creation using row vectors
    @Test
    public void verifyRowVectorMatrixCreation() {
        Vector row1 = new Vector(7, 8);
        Vector row2 = new Vector(9, 10);
        Matrix matrix = Matrix.fromRowVectors(row1, row2);
        assertArrayEquals(new double[][] { { 7, 8 }, { 9, 10 } }, matrix.getEntries());
    }

    // Test for mismatched row vector lengths
    @Test(expected = IllegalArgumentException.class)
    public void checkRowVectorLengthMismatch() {
        Vector v1 = new Vector(1, 2);
        Vector v2 = new Vector(3, 4, 5);
        Matrix.fromRowVectors(v1, v2);
    }

    // Test for dropping a column
    @Test
    public void verifyColumnRemoval() {
        double[][] data = { { 2, 4 }, { 1, 3 } };
        Matrix matrix = new Matrix(data);
        Matrix newMatrix = matrix.dropColumn(0);
        assertArrayEquals(new double[][] { { 4 }, { 3 } }, newMatrix.getEntries());
    }

    // Test for invalid column index
    @Test(expected = IllegalArgumentException.class)
    public void checkInvalidColumnIndex() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        matrix.dropColumn(5);
    }

    // Test for dropping a row
    @Test
    public void verifyRowRemoval() {
        double[][] data = { { 2, 3 }, { 4, 5 } };
        Matrix matrix = new Matrix(data);
        Matrix newMatrix = matrix.dropRow(0);
        assertArrayEquals(new double[][] { { 4, 5 } }, newMatrix.getEntries());
    }

    // Test for invalid row index
    @Test(expected = IllegalArgumentException.class)
    public void checkInvalidRowIndex() {
        double[][] data = { { 2, 3 }, { 4, 5 } };
        Matrix matrix = new Matrix(data);
        matrix.dropRow(3);
    }

    @Test
    public void testRetrieveRow() {
        double[][] values = { { 10, 20 }, { 30, 40 } };
        Matrix matrix = new Matrix(values);
        Vector extractedRow = matrix.getRow(1);
        assertArrayEquals(new double[] { 30, 40 }, extractedRow.getEntries(), EPSILON);
    }

    // Test: Exception for accessing a row index out of bounds
    @Test(expected = IllegalArgumentException.class)
    public void testRetrieveRow_InvalidIndex() {
        double[][] values = { { 1.1, 2.2 }, { 3.3, 4.4 } };
        Matrix matrix = new Matrix(values);
        matrix.getRow(5); // Invalid index
    }

    // Test: Get a specific matrix entry
    @Test
    public void testAccessEntry() {
        double[][] values = { { 7.5, 8.5 }, { 9.5, 10.5 } };
        Matrix matrix = new Matrix(values);
        assertEquals(8.5, matrix.getEntry(0, 1), EPSILON);
    }

    // Test: Exception for accessing entry out of bounds
    @Test(expected = IllegalArgumentException.class)
    public void testAccessEntry_OutOfBounds() {
        double[][] values = { { 2.2, 3.3 }, { 4.4, 5.5 } };
        Matrix matrix = new Matrix(values);
        matrix.getEntry(3, 2); // Invalid indices
    }

    // Test: Verify the number of columns in the matrix
    @Test
    public void testColumnCount() {
        double[][] values = { { 2, 4, 6 }, { 8, 10, 12 } };
        Matrix matrix = new Matrix(values);
        assertEquals(3, matrix.getNumColumns());
    }

    // Test: Validate the creation of an identity matrix
    @Test
    public void testIdentityMatrixCreation() {
        Matrix identity = Matrix.identityMatrix(4);
        double[][] expected = { 
            { 1, 0, 0, 0 }, 
            { 0, 1, 0, 0 }, 
            { 0, 0, 1, 0 }, 
            { 0, 0, 0, 1 } 
        };
        assertArrayEquals(expected, identity.getEntries());
    }

    // Test: Exception for invalid size in identity matrix
    @Test(expected = IllegalArgumentException.class)
    public void testIdentityMatrix_InvalidSize() {
        Matrix.identityMatrix(-1); // Invalid size
    }

    // Test: Replace a column with a given vector
    @Test
    public void testReplaceColumn() {
        double[][] values = { { 10, 20 }, { 30, 40 } };
        Matrix matrix = new Matrix(values);
        Vector newColumn = new Vector(50, 60);
        Matrix updatedMatrix = matrix.setColumn(1, newColumn);

        double[][] expected = { { 10, 50 }, { 30, 60 } };
        assertArrayEquals(expected, updatedMatrix.getEntries());
    }

    // Test: Exception when vector length doesn't match the column size
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceColumn_LengthMismatch() {
        double[][] values = { { 3, 5 }, { 7, 9 } };
        Matrix matrix = new Matrix(values);
        Vector newColumn = new Vector(1, 2, 3); // Length mismatch
        matrix.setColumn(1, newColumn);
    }

    // Test: Replace a row with a new array
    @Test
    public void testReplaceRow() {
        double[][] values = { { 11, 12 }, { 13, 14 } };
        Matrix matrix = new Matrix(values);
        double[] newRow = { 15, 16 };
        Matrix updatedMatrix = matrix.setRow(1, newRow);

        double[][] expected = { { 11, 12 }, { 15, 16 } };
        assertArrayEquals(expected, updatedMatrix.getEntries());
    }

    // Test: Exception for invalid row replacement
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceRow_InvalidInput() {
        double[][] values = { { 8, 9 }, { 10, 11 } };
        Matrix matrix = new Matrix(values);
        double[] newRow = { 12 }; // Invalid row length
        matrix.setRow(0, newRow);
    }

    // Test: Compute determinant for a 2x2 matrix
    @Test
    public void testDeterminant_TwoByTwo() {
        double[][] values = { 
            { 4, 7 }, 
            { 2, 6 } 
        };
        Matrix matrix = new Matrix(values);
        assertEquals(10.0, matrix.determinant(), EPSILON);
    }

    // Test: Verify matrix is not diagonal
    @Test
    public void testIsDiagonal_MatrixNotDiagonal() {
        double[][] values = { 
            { 1, 2, 0 }, 
            { 0, 3, 0 }, 
            { 0, 0, 4 } 
        };
        Matrix matrix = new Matrix(values);
        assertFalse(matrix.isDiagonal());
    }

    // Test: Verify transposition of a square matrix
    @Test
    public void testTranspose_Square() {
        double[][] values = { 
            { 1, 2 }, 
            { 3, 4 } 
        };
        Matrix matrix = new Matrix(values);
        Matrix transposed = matrix.transpose();

        double[][] expected = { 
            { 1, 3 }, 
            { 2, 4 } 
        };
        assertArrayEquals(expected, transposed.getEntries());
    }

    // Test: Check sparsity of a matrix
    @Test
    public void testIsSparse_Matrix() {
        double[][] values = { 
            { 0, 0, 1 }, 
            { 0, 0, 0 }, 
            { 1, 0, 0 } 
        };
        Matrix matrix = new Matrix(values);
        assertTrue(matrix.isSparse(0.7));
    }

    // Test: Update a single matrix entry
    @Test
    public void testUpdateEntry() {
        double[][] values = { 
            { 1, 2 }, 
            { 3, 4 } 
        };
        Matrix matrix = new Matrix(values);
        Matrix updated = Matrix.setEntry(matrix, 9, 0, 1); // Update entry (0,1)

        double[][] expected = { 
            { 1, 9 }, 
            { 3, 4 } 
        };
        assertArrayEquals(expected, updated.getEntries());
    }

    // Test: Exception for out-of-bound entry update
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateEntry_OutOfBounds() {
        double[][] values = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(values);
        Matrix.setEntry(matrix, 10, 2, 2); // Invalid indices
    }

    // Test: Determinant for a single-element matrix
    @Test
    public void testDeterminant_SingleElement() {
        double[][] values = { { 42 } };
        Matrix matrix = new Matrix(values);
        assertEquals(42.0, matrix.determinant(), EPSILON);
    }

    // Test: Verify if matrix is square
    @Test
    public void testIsSquare_NotSquare() {
        double[][] values = { 
            { 1, 2, 3 }, 
            { 4, 5, 6 } 
        };
        Matrix matrix = new Matrix(values);
        assertFalse(matrix.isSquare());
    }

  // Test: Determinant for a 1x1 matrix
  @Test
  public void testDeterminantSingleElement() {
      double[][] values = { { 77 } };
      Matrix matrix = new Matrix(values);
      assertEquals(77.0, matrix.determinant(), EPSILON);
  }

  // Test: Checking if a matrix is square
  @Test
  public void testIsSquareForNonSquareMatrix() {
      double[][] values = {
          { 1, 2, 3 },
          { 4, 5, 6 }
      };
      Matrix matrix = new Matrix(values);
      assertFalse(matrix.isSquare());
  }

  // Test: Identifying a diagonal matrix
  @Test
  public void testIsDiagonalForDiagonalMatrix() {
      double[][] data = {
          { 1, 0, 0 },
          { 0, 2, 0 },
          { 0, 0, 3 }
      };
      Matrix matrix = new Matrix(data);
      assertTrue(matrix.isDiagonal());
  }

  // Test: Transpose of a single-row matrix
  @Test
  public void testTransposeOfSingleRow() {
      double[][] data = { { 10, 20, 30 } };
      Matrix matrix = new Matrix(data);
      Matrix transposed = matrix.transpose();

      double[][] expected = {
          { 10 },
          { 20 },
          { 30 }
      };
      assertArrayEquals(expected, transposed.getEntries());
  }

  // Test: Sparse check for a dense matrix
  @Test
  public void testSparseCheckForDenseMatrix() {
      double[][] data = {
          { 0.3, 0.4 },
          { 0.7, 0.8 }
      };
      Matrix matrix = new Matrix(data);
      assertFalse(matrix.isSparse(0.5));
  }

  // Test: Trace throws exception for non-square matrix
  @Test(expected = IllegalArgumentException.class)
  public void testTraceThrowsForNonSquareMatrix() {
      double[][] data = {
          { 1, 2 },
          { 3, 4 },
          { 5, 6 }
      };
      Matrix matrix = new Matrix(data);
      matrix.trace();
  }

  // Test: Modifying a row in a matrix
  @Test
  public void testSetRowSuccessfully() {
      double[][] data = {
          { 2, 2, 2 },
          { 3, 3, 3 },
          { 4, 4, 4 }
      };
      Matrix matrix = new Matrix(data);
      double[] newRow = { 8, 8, 8 };
      Matrix updatedMatrix = Matrix.setRow(matrix, 1, newRow);

      double[][] expected = {
          { 2, 2, 2 },
          { 8, 8, 8 },
          { 4, 4, 4 }
      };
      assertArrayEquals(expected, updatedMatrix.getEntries());
  }

//   // Test: Invalid row index for setRow
//   @Test(expected = IllegalArgumentException.class)
//   public void testSetRowWithInvalidIndex() {
//       double[][] data = {
//           { 1, 1, 1 },
//           { 2, 2, 2 }
//       };
//       Matrix matrix = new Matrix(data);
//       double[] newRow = { 9, 9, 9 };
//       Matrix.setRow(matrix, 5, newRow);
//   }

//   // Test: Determinant of a 3x3 matrix
//   @Test
//   public void testDeterminantOfThreeByThreeMatrix() {
//       double[][] data = {
//           { 4, 2, 3 },
//           { 3, 5, 7 },
//           { 1, 6, 8 }
//       };
//       Matrix matrix = new Matrix(data);
//       assertEquals(-85.0, matrix.determinant(), EPSILON);
//   }

  // Test: Transpose of a general matrix
  @Test
  public void testTransposeGeneralMatrix() {
      double[][] original = {
          { 3, 4 },
          { 5, 6 },
          { 7, 8 }
      };
      Matrix matrix = new Matrix(original);
      Matrix transposed = matrix.transpose();

      double[][] expected = {
          { 3, 5, 7 },
          { 4, 6, 8 }
      };
      assertArrayEquals(expected, transposed.getEntries());
  }

  // Test: Swap columns of a matrix
  @Test
  public void testSwapColumns() {
      double[][] original = {
          { 1, 2, 3 },
          { 4, 5, 6 },
          { 7, 8, 9 }
      };
      Matrix matrix = new Matrix(original);
      Matrix swapped = matrix.swapColumns(0, 2);

      double[][] expected = {
          { 3, 2, 1 },
          { 6, 5, 4 },
          { 9, 8, 7 }
      };
      assertArrayEquals(expected, swapped.getEntries());
  }

  // Test: Swap columns with invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testSwapColumnsThrowsForInvalidIndex() {
      double[][] data = {
          { 2, 3, 4 },
          { 5, 6, 7 }
      };
      Matrix matrix = new Matrix(data);
      matrix.swapColumns(1, 3);
  }

  // Test: Swap rows of a matrix
  @Test
  public void testSwapRows() {
      double[][] original = {
          { 5, 6, 7 },
          { 8, 9, 10 },
          { 11, 12, 13 }
      };
      Matrix matrix = new Matrix(original);
      Matrix swapped = matrix.swapRows(0, 2);

      double[][] expected = {
          { 11, 12, 13 },
          { 8, 9, 10 },
          { 5, 6, 7 }
      };
      assertArrayEquals(expected, swapped.getEntries());
  }

  // Test: Swap rows with invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testSwapRowsThrowsForInvalidIndex() {
      double[][] data = {
          { 3, 4, 5 },
          { 6, 7, 8 }
      };
      Matrix matrix = new Matrix(data);
      matrix.swapRows(0, 3);
  }

  // Test: Transpose of an identity matrix
  @Test
  public void testTransposeIdentityMatrix() {
      double[][] identity = {
          { 1, 0, 0 },
          { 0, 1, 0 },
          { 0, 0, 1 }
      };
      Matrix matrix = new Matrix(identity);
      Matrix transposed = matrix.transpose();

      assertArrayEquals(identity, transposed.getEntries());
  }

    @Test(expected = IllegalArgumentException.class)
    public void testSwapRows_Case4() {
        double[][] entries4 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
                { 10, 11, 12 }
        };
        Matrix matrix4 = new Matrix(entries4);
        matrix4.swapRows(1, 4); // no 4th row, throw exception
    }

    // Test cases for add method

    @Test
    public void testAdd() {
        double[][] entries1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        double[][] entries2 = {
                { 9, 8, 7 },
                { 6, 5, 4 },
                { 3, 2, 1 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Matrix matrix2 = new Matrix(entries2);
        Matrix result = matrix1.add(matrix2);
        double[][] expected = {
                { 10, 10, 10 },
                { 10, 10, 10 },
                { 10, 10, 10 }
        };
        assertArrayEquals(expected, result.getEntries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_Exception() {
        double[][] entries1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        double[][] entries2 = {
                { 9, 8, 7, 8 },
                { 6, 5, 4, 5 },
                { 3, 2, 1, 2 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Matrix matrix2 = new Matrix(entries2);
        Matrix result = matrix1.add(matrix2); // matrix size don't match, throw exception
    }

    // Test cases for subtract method

    @Test
    public void testSubtract() {
        double[][] entries1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        double[][] entries2 = {
                { 9, 8, 7 },
                { 6, 5, 4 },
                { 3, 2, 1 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Matrix matrix2 = new Matrix(entries2);
        Matrix result = matrix1.subtract(matrix2);
        double[][] expected = {
                { -8, -6, -4 },
                { -2, 0, 2 },
                { 4, 6, 8 }
        };
        assertArrayEquals(expected, result.getEntries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtract_Exception() {
        double[][] entries1 = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };
        double[][] entries2 = {
                { 7, 8 },
                { 9, 10 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Matrix matrix2 = new Matrix(entries2);
        matrix1.subtract(matrix2); // matrix size don't match, throw exception
    }

    // Test cases for multiply methods

    @Test
    public void testMultiplyByScalar() {
        double[][] entries = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix = new Matrix(entries);
        Matrix result = matrix.multiply(2);
        double[][] expected = {
                { 2, 4, 6 },
                { 8, 10, 12 },
                { 14, 16, 18 }
        };
        assertArrayEquals(expected, result.getEntries());
    }

    @Test
    public void testMultiplyByVector() {
        double[][] entries = {
                { 1, 2, 3 },
                { 4, 5, 6 },
        };
        Matrix matrix = new Matrix(entries);
        Vector vector = new Vector(new double[] { 1, 1, 1 });
        Vector result = matrix.multiply(vector);
        assertArrayEquals(new double[] { 6.0, 15.0 }, result.getEntries(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyByVector_Exception() {
        double[][] entries = {
                { 1, 2, 3 },
                { 4, 5, 6 },
        };
        Matrix matrix = new Matrix(entries);
        Vector vector = new Vector(new double[] { 1, 1 }); // Incompatible shapes
        Vector result = matrix.multiply(vector); // This line should throw an exception
    }

    @Test
    public void testMultiplyByMatrix() {
        double[][] entries1 = {
                { 1, 2 },
                { 3, 4 },
        };
        double[][] entries2 = {
                { 5, 6 },
                { 7, 8 },
        };
        Matrix matrix1 = new Matrix(entries1);
        Matrix matrix2 = new Matrix(entries2);
        Matrix result = matrix1.multiply(matrix2);
        double[][] expected = {
                { 19, 22 },
                { 43, 50 }
        };
        assertArrayEquals(expected, result.getEntries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyByMatrix_Exception() {
        double[][] entries1 = {
                { 1, 2 },
                { 3, 4 }
        };
        double[][] entries2 = {
                { 5, 6 },
                { 7, 8 },
                { 1, 2 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Matrix matrix2 = new Matrix(entries2);
        Matrix result = matrix1.multiply(matrix2); // Incompatible shapes, throw exception
    }

    @Test
    public void testAddVectorToColumn_Case1() {
        double[][] entries1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Vector vector1 = new Vector(new double[] { 10, 11, 12 });
        Matrix result1 = matrix1.addVectorToColumn(vector1, 1);
        double[][] expected1 = {
                { 1, 12, 3 },
                { 4, 16, 6 },
                { 7, 20, 9 }
        };
        assertArrayEquals(expected1, result1.getEntries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVectorToColumn_Case2() {
        double[][] entries2 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix2 = new Matrix(entries2);
        Vector vector2 = new Vector(new double[] { 10, 11 });
        matrix2.addVectorToColumn(vector2, 1); // vector length does not match column length, throw exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVectorToColumn_Case3() {
        double[][] entries3 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix3 = new Matrix(entries3);
        Vector vector3 = new Vector(new double[] { 10, 11, 12 });
        matrix3.addVectorToColumn(vector3, 3); // no 3th column, throw exception
    }

    @Test
    public void testAddVectorToRow_Case1() {
        double[][] entries1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix1 = new Matrix(entries1);
        Vector vector1 = new Vector(new double[] { 10, 11, 12 });
        Matrix result1 = matrix1.addVectorToRow(vector1, 1);
        double[][] expected1 = {
                { 1, 2, 3 },
                { 14, 16, 18 },
                { 7, 8, 9 }
        };
        assertArrayEquals(expected1, result1.getEntries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVectorToRow_Case2() {
        double[][] entries2 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix2 = new Matrix(entries2);
        Vector vector2 = new Vector(new double[] { 10, 11 });
        matrix2.addVectorToRow(vector2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVectorToRow_Case3() {
        double[][] entries3 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        Matrix matrix3 = new Matrix(entries3);
        Vector vector3 = new Vector(new double[] { 10, 11, 12 });
        matrix3.addVectorToRow(vector3, 3); // no 3th row, throw exception
    }

}
