
package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VectorTest {
	private double tolerance = 0.00001;

	// Constructor tests
	@Test
	public void testVectorInitialization() {
		Vector vec = new Vector(5.0, 6.0, 7.0);
		assertArrayEquals(new double[] { 5.0, 6.0, 7.0 }, vec.getEntries(), tolerance);
	}

	@Test
	public void testEmptyVector() {
		Vector emptyVec = new Vector();
		assertEquals(0, emptyVec.length());
	}

	@Test
	public void testVectorCopy() {
		Vector original = new Vector(4.0, 5.0, 6.0);
		Vector clone = new Vector(original);
		assertArrayEquals(original.getEntries(), clone.getEntries(), tolerance);
	}

    	// Arithmetic operations
	@Test
	public void testAddVectors() {
		Vector v1 = new Vector(1.0, 2.0);
		Vector v2 = new Vector(3.0, 4.0);
		Vector result = v1.add(v2);
		assertArrayEquals(new double[] { 4.0, 6.0 }, result.getEntries(), tolerance);
	}

	@Test
	public void testSubtractVectors() {
		Vector v1 = new Vector(5.0, 6.0);
		Vector v2 = new Vector(3.0, 2.0);
		Vector result = v1.subtract(v2);
		assertArrayEquals(new double[] { 2.0, 4.0 }, result.getEntries(), tolerance);
	}

	@Test
	public void testMultiplyVectorByScalar() {
		Vector v = new Vector(1.0, 2.0);
		Vector result = v.multiply(3.0);
		assertArrayEquals(new double[] { 3.0, 6.0 }, result.getEntries(), tolerance);
	}

	// Dot product
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(2.0, 3.0);
		Vector v2 = new Vector(4.0, 5.0);
		assertEquals(23.0, v1.dot(v2), tolerance);
	}

	// Cross product
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1.0, 0.0, 0.0);
		Vector v2 = new Vector(0.0, 1.0, 0.0);
		Vector result = v1.cross(v2);
		assertArrayEquals(new double[] { 0.0, 0.0, 1.0 }, result.getEntries(), tolerance);
	}

	// Normalize
	@Test
	public void testNormalizeVector() {
		Vector v = new Vector(3.0, 4.0);
		Vector normalized = v.normalize();
		assertArrayEquals(new double[] { 0.6, 0.8 }, normalized.getEntries(), tolerance);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNormalizeZeroVector() {
		Vector v = new Vector(0.0, 0.0);
		v.normalize(); // Should throw exception
	}

	// Angle between vectors
	@Test
	public void testAngleBetweenVectorsDegrees() {
		Vector v1 = new Vector(1.0, 0.0);
		Vector v2 = new Vector(0.0, 1.0);
		assertEquals(90.0, Vector.angleDegrees(v1, v2), tolerance);
	}

	@Test
	public void testAngleBetweenVectorsRadians() {
		Vector v1 = new Vector(1.0, 0.0);
		Vector v2 = new Vector(0.0, 1.0);
		assertEquals(Math.PI / 2, Vector.angleRadians(v1, v2), tolerance);
	}

	// Linear combination of vectors
	@Test
	public void testLinearCombination() {
		Vector[] vectors = { new Vector(2.0, 3.0), new Vector(4.0, 5.0) };
		double[] weights = { 1.0, -1.0 };
		Vector result = Vector.linearCombination(vectors, weights);
		assertArrayEquals(new double[] { -2.0, -2.0 }, result.getEntries(), tolerance);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLinearCombination() {
		Vector[] vectors = { new Vector(2.0, 3.0) };
		double[] weights = { 1.0, -1.0 };
		Vector.linearCombination(vectors, weights); // Should throw exception
	}

	// Outer product
	@Test
	public void testOuterProduct() {
		Vector v1 = new Vector(2.0, 3.0);
		Vector v2 = new Vector(4.0, 5.0);
		Matrix result = v1.outerProduct(v2);
		assertArrayEquals(new double[][] { { 8.0, 10.0 }, { 12.0, 15.0 } }, result.getEntries());
	}

	// Getter tests
	@Test
	public void testGetEntriesOfVector() {
		Vector vec = new Vector(2.0, 4.0, 6.0);
		assertArrayEquals(new double[] { 2.0, 4.0, 6.0 }, vec.getEntries(), tolerance);
	}

	@Test
	public void testGetSpecificElement() {
		Vector vec = new Vector(5.0, 10.0, 15.0);
		assertEquals(10.0, vec.get(1), tolerance);
	}

	// Identity Vector test
	@Test
	public void testIdentityVector3() {
		Vector identityVec = Vector.identityVector(3);
		assertArrayEquals(new double[] { 0.0, 0.0, 0.0 }, identityVec.getEntries(), tolerance);
	}

	// Setters and mutation tests
	@Test
	public void testUpdateVectorElement() {
		Vector vec = new Vector(1.0, 2.0, 3.0);
		Vector updated = vec.set(1, 5.0);
		assertArrayEquals(new double[] { 1.0, 5.0, 3.0 }, updated.getEntries(), tolerance);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSetIndex() {
		Vector vec = new Vector(1.0, 2.0, 3.0);
		vec.set(5, 10.0); // Should throw exception
	}

	// Magnitude and length tests
	@Test
	public void testMagnitudeOfVector() {
		Vector vec = new Vector(3.0, 4.0);
		assertEquals(5.0, vec.magnitude(), tolerance);
	}

	@Test
	public void testLengthOfZeroVector() {
		Vector vec = new Vector(0.0, 0.0, 0.0);
		assertEquals(0.0, vec.magnitude(), tolerance);
	}

	// Orthogonal projection
	@Test
	public void testOrthogonalProjection() {
		Vector v1 = new Vector(5.0, 6.0);
		Vector v2 = new Vector(1.0, 1.0);
		Vector projection = v1.orthogonalProjection(v2);
		assertArrayEquals(new double[] { 5.5, 5.5 }, projection.getEntries(), tolerance);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidOrthogonalProjection() {
		Vector v1 = new Vector(5.0, 6.0);
		Vector v2 = new Vector(0.0, 0.0);
		v1.orthogonalProjection(v2); // Should throw exception
	}

	// Scalar triple product
	@Test
	public void testScalarTripleProduct() {
		Vector a = new Vector(1.0, 2.0, 3.0);
		Vector b = new Vector(4.0, 5.0, 6.0);
		Vector c = new Vector(7.0, 8.0, 9.0);
		assertEquals(0.0, Vector.scalarTripleProduct(a, b, c), tolerance);
	}
}
