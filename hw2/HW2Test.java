package hw2;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW2Test {

	// create a tree from a string
	public static MyIntSET fromString(String ints) {
		MyIntSET set = new MyIntSET();
		for (String s : ints.split(" "))
			set.put(Integer.parseInt(s));
		return set;
	}

	/**
	 * A toy test function. Tests that size behaves correctly on the empty tree and
	 * on a tree with only one node. Use this only as a template for your more
	 * thorough tests.
	 */
	@Test
	public void testSingleKey() {
		MyIntSET set = new MyIntSET();
		assertEquals(0, set.size());
		set.put(42);
		// set.prettyPrint();
		assertEquals(1, set.size());
		// System.out.println("Height " + set.height());
	}

	@Test
	public void testLargerTree() {
		MyIntSET set = fromString("5 3 7 4");
		// set.prettyPrint();
		assertEquals(4, set.size());
		set.put(42);
		assertEquals(5, set.size());
		// System.out.println("Height = " + set.height());
	}

	@Test
	public void testSizeOdd() {
		MyIntSET set = fromString("50 25 100 12 37 150 127");
		// set.prettyPrint();
		// System.out.println(set.sizeOdd());

	}

	@Test
	public void testSizeAtDepth() {
		MyIntSET set = fromString("50 25 100 12 37 150 127");
		// System.out.println("Test: " + set.sizeAtDepth(0));
		assertEquals(1, set.sizeAtDepth(0));
		assertEquals(2, set.sizeAtDepth(1));
		assertEquals(3, set.sizeAtDepth(2));
		assertEquals(1, set.sizeAtDepth(3));
		assertEquals(0, set.sizeAtDepth(421412));
		assertEquals(0, set.sizeAtDepth(4));

	}

	@Test
	public void testSizeAboveDepth() {
		MyIntSET set = fromString("50 25 100 12 37 150 127");
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(1, set.sizeAboveDepth(1));
		assertEquals(3, set.sizeAboveDepth(2));
		assertEquals(6, set.sizeAboveDepth(3));
		assertEquals(7, set.sizeAboveDepth(421412));
		assertEquals(7, set.sizeAboveDepth(4));

	}

	// MUST EDIT CODE. ANYTHING WITH TREES MUST EDIT
	@Test
	public void testPerfectlyBalanced() {
		MyIntSET set = fromString("6 5 4 3 2 1"); //Left skewed
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("1 2 3 4 5 6"); //Right Skewed
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("30 25 40 24 26 35 45"); //Success
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("30 25 40 26 35 45"); //Failure case
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString(" ");//Null tree
		assertTrue(set.isPerfectlyBalancedS());
	}

	@Test
	public void testRemoveOddSubtrees() {
		MyIntSET set = fromString("50 25 100 12 26 27 29 901 502 1239 37 150 127");
		set.removeOddSubtrees();
		assertEquals(0, set.sizeOdd());
		assertEquals(2, set.size());
		assertEquals(2, set.height());
		
	}

	@Test
	public void testTreeEqualsSuccess() {
		MyIntSET set1 = fromString("50 25 100 12 26 27 29 901 502 1239 37 150 127");
		MyIntSET set2 = fromString("50 25 100 12 26 27 29 901 502 1239 37 150 127");
		assertTrue(set1.treeEquals(set2));
	}

	@Test
	public void testTreeEqualsFailure() {
		//1 node removal
		MyIntSET set1 = fromString("50 25 12 26 27 29 901 502 1239 37 150 127");
		MyIntSET set2 = fromString("50 25 100 12 26 27 29 901 502 1239 37 150 127");
		assertFalse(set1.treeEquals(set2));
	}
	

	@Test
	public void testTreeEqualsTotalFailure() {
		//Two completely different trees
		MyIntSET set1 = fromString("64 23 8745 2 46 874 4365 351 56 68 98 12 643 32 54");
		MyIntSET set2 = fromString("50 25 100 12 26 27 29 901 502 1239 37 150 127");
		assertFalse(set1.treeEquals(set2));
	}
	
	

	// Write some more test functions below.
	// Make sure to test all of the functions your wrote on various
	// trees of different shapes and sizes.

}
