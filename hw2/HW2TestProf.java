package hw2;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class HW2TestProf {
	
	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	

	/**
	 * A toy test function.  Tests that size behaves correctly on the empty
	 * tree and on a tree with only one node.  Use this only as a template
	 * for your more thorough tests.
	 *
	@Test
	public void testToyTest() {
		MyIntSET set = new MyIntSET();
		assertEquals(0,  set.size());
		set.put(42);
		assertEquals(1, set.size());
	}
	*/
	

	
	// create a tree from a string
	public static MyIntSET fromString (String ints) {
		MyIntSET set = new MyIntSET ();
		for (String s : ints.split (" "))
			set.put (Integer.parseInt (s));
		return set;
	}
	
	@Test
	public void test20Height() {
		MyIntSET set = new MyIntSET();
		int h = -1;
		assertEquals(h, set.height());
		
		// Make a zig-zag insertion path starting at 1000 and moving left, right, left, right, etc.
		int lo = 0;
		int hi = 1000;
		for(int i = 0; i < 100; i++) {
			set.put(hi);
			h++;
			hi--;
			assertEquals(h, set.height());
			set.put(lo);
			h++;
			assertEquals(h, set.height());
			lo++;
		}
		
		// Make a straight insertion path on the right child of the root that always goes left.
		int key = 10000;
		for(int i = 0; i < 199; i++) {
			set.put(key);
			key -= 10;
			assertEquals(h, set.height());
		}
		
		// Insert one more on the straight path increasing height by 1.
		set.put(10000 - 2000);
		h++;
		assertEquals(h, set.height());
		
		//Insert a sibling to the last insertion, leaving height unchanged.
		set.put(10000 - 1980 + 1);
		assertEquals(h, set.height());		
	}
	
	
	@Test
	public void test20SizeOdd() {
		MyIntSET s1 = new MyIntSET();
		assertEquals(0, s1.sizeOdd());
		MyIntSET s2 = new MyIntSET();
		assertEquals(0, s1.sizeOdd());
		s1.put(21);
		s2.put(42);
		assertEquals(1, s1.sizeOdd());
		assertEquals(0, s2.sizeOdd());
		s1.put(42);
		s2.put(21);
		assertEquals(1, s1.sizeOdd());
		assertEquals(1, s2.sizeOdd());
		s1.put(11);
		s2.put(64);
		assertEquals(2, s1.sizeOdd());
		assertEquals(1, s2.sizeOdd());
		
		// Insert the following ints into an empty set checking oddSize after each insertion.
		int[] a = {100, 55, 160, 22, 66, 103, 195, 67, 190, 191, 2, 1, 98, 201, 200};
		int oddCount = 0;
		MyIntSET s = new MyIntSET();
		for (int i = 0; i < a.length; i++) {
			s.put(a[i]);
			if (a[i] % 2 == 1)
				oddCount++;
			assertEquals(oddCount, s.sizeOdd());
		}
	}
	
	@Test
	public void test20TreeEquals() {
		MyIntSET s1 = new MyIntSET();
		MyIntSET s2 = new MyIntSET();
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		int[] a = {100, 55, 160, 22, 66, 103, 195, 67, 190, 191, 12, 11, 98, 201, 200};
		
		// for each int in a, insert first into s1, check not equal
		// then insert into s2 check equal
		for(int i = 0; i < a.length; i++) {
			s1.put(a[i]);
			assertFalse(s1.treeEquals(s2));
			assertFalse(s2.treeEquals(s1));
			s2.put(a[i]);
			assertTrue(s1.treeEquals(s2));
			assertTrue(s2.treeEquals(s1));
		}
		
		s1.put(202);
		s2.put(4);
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));
		s1.put(4);
		s2.put(202);
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		s1.put(1);
		s2.put(2);
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));
		
		s1 = fromString("50 150 100");
		s2 = fromString("50 100 150");
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));
		
		s1 = fromString("100 50 150 25 75 125 175");
		s2 = fromString("100 50 150 25 75 125 175");
		assertTrue(s1.treeEquals(s2));
		assertTrue(s2.treeEquals(s1));
		s1.put(126);
		s1.put(127);
		s2.put(127);
		s2.put(126);
		assertFalse(s1.treeEquals(s2));
		assertFalse(s2.treeEquals(s1));
	}
	
	@Test
	public void test10SizeAtDepth() {
		int[] keys = {1000, 500, 250, 750, 1500, 125, 1750, 2000, 625, 1250};
		int[] d1 =   {0,    1,   1,   1,   2,    2,   2,    2,    2,   2};
		int[] d2 =   {0,    0,   1,   2,   2,    2,   3,    3,    3,   4};
		int[] d3 =   {0,    0,   0,   0,   0,    1,   1,    2,    3,   3};
		MyIntSET set = new MyIntSET();
		assertEquals(0, set.sizeAtDepth(0));
		assertEquals(0, set.sizeAtDepth(1));
		assertEquals(0, set.sizeAtDepth(2));
		
		for(int i = 0; i < keys.length; i++) {
			set.put(keys[i]);
			assertEquals(1, set.sizeAtDepth(0));
			assertEquals(d1[i], set.sizeAtDepth(1));
			assertEquals(d2[i], set.sizeAtDepth(2));
			assertEquals(d3[i], set.sizeAtDepth(3));
		}
	}
	
	
	@Test
	public void test10SizeAboveDepth() {
		int[] keys = {1000, 500, 250, 750, 1500, 125, 1750, 2000, 625, 1250};
		int[] d2 =   {1,    2,   2,   2,   3,    3,   3,    3,    3,   3};
		int[] d3 =   {1,    2,   3,   4,   5,    5,   6,    6,    6,   7};
		int[] d4 =   {1,    2,   3,   4,   5,    6,   7,    8,    9,   10};		
		
		MyIntSET set = new MyIntSET();
		assertEquals(0, set.sizeAboveDepth(0));
		assertEquals(0, set.sizeAboveDepth(1));
		assertEquals(0, set.sizeAboveDepth(2));
		
		for(int i = 0; i < keys.length; i++) {
			set.put(keys[i]);
			assertEquals(0, set.sizeAboveDepth(0));
			assertEquals(1, set.sizeAboveDepth(1));
			assertEquals(d2[i], set.sizeAboveDepth(2));
			assertEquals(d3[i], set.sizeAboveDepth(3));
			assertEquals(d4[i], set.sizeAboveDepth(4));
		}
	}
	
	@Test
	public void test10IsPerfectlyBalancedS() {
		MyIntSET set = new MyIntSET();
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("100");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("100 50");
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("100 50 150");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("100 150 50");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("150 50 100");
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("50 100 150");
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("50 25 75 12 37 62 87");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("50 25 12 37 75 62 87");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("50 75 62 87 25 12 37");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("50 25 75 12 37 62 87 51");
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("50 25 75 12 22 62 87");
		assertFalse(set.isPerfectlyBalancedS());
		set = fromString("50 25 75 12 37 62 87 5 18 31 42 56 68 81 93");
		assertTrue(set.isPerfectlyBalancedS());
		set = fromString("50 25 75 12 37 62 87 5 18 31 36 56 68 81 93");
		assertFalse(set.isPerfectlyBalancedS());
	}
	
	@Test
	public void test10RemoveOddSubtrees() {
		MyIntSET set = new MyIntSET();
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = fromString("100");
		set.removeOddSubtrees();
		assertEquals("100 ", set.toString());
		set = fromString("101");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = fromString("100 98 101");
		set.removeOddSubtrees();
		assertEquals("100 98 ", set.toString());
		set = fromString("100 91 102");
		set.removeOddSubtrees();
		assertEquals("100 102 ", set.toString());
		set = fromString("101 98 100");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = fromString("100 50 150 20 60 130 180");
		set.removeOddSubtrees();
		assertEquals("100 50 150 20 60 130 180 ", set.toString());
		set = fromString("100 50 150 20 61 130 180");
		set.removeOddSubtrees();
		assertEquals("100 50 150 20 130 180 ", set.toString());
		set = fromString("100 50 150 20 60 130 181");
		set.removeOddSubtrees();
		assertEquals("100 50 150 20 60 130 ", set.toString());
		set = fromString("100 50 150 21 60 131 180");
		set.removeOddSubtrees();
		assertEquals("100 50 150 60 180 ", set.toString());
		set = fromString("100 51 150 20 60 130 180");
		set.removeOddSubtrees();
		assertEquals("100 150 130 180 ", set.toString());
		set = fromString("100 50 151 20 60 130 180");
		set.removeOddSubtrees();
		assertEquals("100 50 20 60 ", set.toString());
		set = fromString("100 57 153 20 60 130 180");
		set.removeOddSubtrees();
		assertEquals("100 ", set.toString());
		set = fromString("97 50 150 20 60 130 180");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
		set = fromString("99 51 153 25 59 131 189");
		set.removeOddSubtrees();
		assertEquals("", set.toString());
	}

}
