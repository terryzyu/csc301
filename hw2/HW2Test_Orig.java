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
		assertEquals(1, set.size());
	}
	
	@Test
	public void testLargerTree() {
		MyIntSET set = fromString("5 3 7 4");
		assertEquals(4,  set.size());
		set.put(42);
		assertEquals(5, set.size());
	}
	
	
	// Write some more test functions below.
	// Make sure to test all of the functions your wrote on various
	// trees of different shapes and sizes.

}
