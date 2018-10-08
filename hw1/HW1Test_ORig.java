package hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW1Test {

	/**
	 * A toy test function. Tests that symbol table behaves as expected when a
	 * single key/value pair is inserted.
	 */
	@Test
	public void testSinglePutGet() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		assertEquals(0, st.size());
		assertNull(st.get("apple"));
		st.put("apple", 42);
		assertEquals(1, st.size());
		assertEquals(new Integer(42), st.get("apple"));
		assertNull(st.get("a"));
		assertNull(st.get("b"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNull() {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		st.put("a", 1);

		// Null is not a valid key. This should throw an IllegalArgumentException
		st.get(null);
	}

	// TODO
	// Write some more test functions below. Make sure to test:
	// 1. Putting at the beginning, end, and middle of the list.
	// 2. Putting something that is already in the list
	// 3. Size works correctly
	// 4. Get works correctly for keys in beginning, middle and end.
	// 5. Get works correctly for keys that are not in the list but
	// would be located at beginning, middle, end of list.
	// 6. Delete works correctly for keys in beginning, middle, and end.
	// 7. Delete works correctly for keys that are not in the symbol table.
	// 8. You can put a few items, delete them all, and then put more items
	// and all the functions (put, get, delete, iterate) continue to work.
	// 9. The iterator returned by keys() provides the keys in increasing order.
	//10.  Null is not a valid key.  Your code should behave as before your
	//      changes and throw an IllegalArgumentException when null is given as a
	//      key to any method. 

}
