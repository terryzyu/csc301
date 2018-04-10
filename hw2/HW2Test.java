package hw2;
import static org.junit.Assert.*;

import org.junit.Test;

public class HW2Test {

	
	// create a tree from a string
	public static MyIntSET fromString (String ints) {
		MyIntSET set = new MyIntSET ();
		for (String s : ints.split (" "))
			set.put (Integer.parseInt (s));
		return set;
	}
        
	/**
	 * A toy test function.  Tests that size behaves correctly on the empty
	 * tree and on a tree with only one node.  Use this only as a template
	 * for your more thorough tests.
	 */
	@Test
	public void testSingleKey() {
		MyIntSET set = new MyIntSET();
		assertEquals(0,  set.size());
		set.put(42);
		//set.prettyPrint();
		assertEquals(1, set.size());
		//System.out.println("Height " + set.height());
	}
	
	@Test
	public void testLargerTree() {
		MyIntSET set = fromString("5 3 7 4");
		//set.prettyPrint();
		assertEquals(4,  set.size());
		set.put(42);
		assertEquals(5, set.size());
		//System.out.println("Height = " + set.height());
	}
	
	@Test
	public void testSizeOdd() {
		MyIntSET set = fromString("50 25 100 12 37 150 127");
		//set.prettyPrint();
		//System.out.println(set.sizeOdd());
		
	}
	
	@Test
	public void testSizeAtDepth() {
		MyIntSET set = fromString("50 25 100 12 37 150 127");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Test: " + set.sizeAtDepth(0));
		assertEquals(1, set.sizeAtDepth(0));
		assertEquals(2, set.sizeAtDepth(1));
		assertEquals(3, set.sizeAtDepth(2));
		assertEquals(1, set.sizeAtDepth(3));
		assertEquals(0, set.sizeAtDepth(421412));
		assertEquals(0, set.sizeAtDepth(4));
		
	}
	
	
	// Write some more test functions below.
	// Make sure to test all of the functions your wrote on various
	// trees of different shapes and sizes.

}
