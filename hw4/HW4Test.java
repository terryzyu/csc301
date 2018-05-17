package hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW4Test {

	@Test
	public void toyTest() {
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		/* Arrays should look like:
		 *        0       1       2       3
		 * keys  null    null    null    null
		 * vals  null    null    null    null
		 */
		st.put(7, "seven");
		st.put(2, "two");
		/* Arrays should look like:
		 *        0       1       2       3
		 * keys  null    null     2       7
		 * vals  null    null    two    seven
		 */
		//st.print();
		st.put(6, "six");
		/* Arrays should look like:
		 *        0       1       2       3       4       5       6       7
		 * keys  null    null     2      null    null    null     6       7
		 * vals  null    null    two     null    null    null    six    seven
		 */
		//System.out.println();
		//System.out.println();
		//st.print();
		// verify you can find 7, 2, and 6
		assertEquals("seven", st.get(7));
		assertEquals("two", st.get(2));
		assertEquals("six", st.get(6));
		st.delete(2);
		/* Arrays should look like:
		 *        0       1       2       3       4       5       6       7
		 * keys  null    null     2      null    null    null     6       7
		 * vals  null    null    null    null    null    null    six    seven
		 */
		//System.out.println();
		//System.out.println();
		//st.print();
		// verify box was deleted
		assertFalse(st.contains(2));
		int sevenCount = 0;
		int twoCount = 0;
		int sixCount = 0;
		// loop through the table looking for keys
		// and verifying corresponding values
		for(int i = 0; i < st.tableSize(); i++) {
			Integer k = st.getKeyAt(i);
			if (k != null) {
				if (k.equals(7)) {
					assertEquals("seven", st.getValueAt(i));
					sevenCount++;
				} else if (k.equals(2)) {
					assertNull(st.getValueAt(i));
					twoCount++;
				} else if (k.equals(6)) {
					assertEquals("six", st.getValueAt(i));
					sixCount++;
				} else {
					fail();
				}
			}
		}
		// verify each key appears exactly once.
		assertEquals(1, sevenCount);
		assertEquals(1, twoCount);
		assertEquals(1, sixCount);
		assertTrue(st.isLazyDeleted(2));
	}
	
	@Test
	public void testing() {
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		st.put(1, "one");
		st.put(6, "six");

		//st.put(7, "seven");
		st.delete(1);

		st.put(2, "two");
		st.put(7, "seven");

		st.delete(7);
		assertFalse(st.isLazyDeleted(2));
		st.delete(6);
		st.put(3, "three");
		st.put(0, "zero");
		st.put(1, "one");
		//st.put(15,"5teen");
		//st.print();                     //FIRST PRINT
		//System.out.println();
		//System.out.println();
		//st.put(7, "seven");
		st.put(4, "four");
		st.put(10,"ten");
		st.put(5,"five");
		//st.print();                     //SECOND PRINT
		
		st.delete(2);
		//System.out.println();
		//System.out.println();
		st.delete(3);
		//st.print();                     //THIRD PRINT
		st.put(11, "11elv");
		st.delete(0);
		//System.out.println();
		//System.out.println();
		//st.print();                     //FOURTH PRINT
		st.put(17, "7teen");

		//System.out.println("Size: " + st.size());
		//System.out.println();
		//System.out.println();
		//st.print();
		//System.out.println("Size: " + st.size());
		
		st.put(9, "nine");
		//System.out.println();
		//System.out.println();
		//st.print();
		
		st.put(5, "five");
		//st.put(0, "zero");
		//System.out.println();
		//System.out.println();
		//st.print();
		st.put(5, "5ive");

		//System.out.println();
		//System.out.println();
		//st.print();
	}
	

	@Test
	public void again() {
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		st.put(0, "zero");
		st.put(1, "one");
		st.put(2, "two");
		st.put(3, "three");
		st.put(4, "four");
		st.put(6, "six");
		st.delete(2);
		st.put(2, "newTwo");
		st.put(2, "MewTwo");
		System.out.println();
		System.out.println();
		st.print();
		st.delete(0);
		System.out.println();
		System.out.println();
		st.delete(4);
		st.delete(6);
		st.print();
		System.out.println(st.contains(6));
		System.out.println();
		System.out.println();
		st.print();
		System.out.println();
	System.out.println();
		st.print();
	}
	
	@Test
	public void doubleInsertion() {
		//Tests for when keys are double inserted e.g. **Z*YZ** and if properly removed
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		st.put(1, "X"); //Hash to same
		st.put(17, "Y"); //Hash to same
		st.put(33, "Z"); //Hash to same
		st.put(49, "A"); //Hash to same
		st.put(65, "B"); //Hash to same
		
		st.print();
		
		st.delete(1);
		st.delete(49);
		
		System.out.println();
		System.out.println();
		st.print();

		st.put(81, "FAte");
		System.out.println();
		System.out.println();
		st.print();
		st.put(65, "Update");

		st.put(17, "Up17");
		st.put(8, "scan");
		
		System.out.println();
		System.out.println();
		st.print();
		System.out.println();
		System.out.println(st.contains(8));
		
	}
	
	@Test
	public void keysIterator() {
		//Tests the iterator function. Should not include lazy keys
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		st.put(1, "X"); //Hash to same
		st.put(17, "Y"); //Hash to same
		st.put(33, "Z"); //Hash to same
		st.put(49, "A"); //Hash to same
		st.put(65, "B"); //Hash to same
		
		for(Integer key: st.keys()) {
			System.out.println(key);
		}
		
		st.delete(1);
		st.delete(49);
		
		System.out.println();
		for(Integer key: st.keys()) {
			System.out.println(key);
		}
		
		st.put(81, "FAte"); //Hash to same
		st.put(65, "Update");
		st.put(17, "Up17");
		st.put(8, "scan");
		
		System.out.println();
		for(Integer key: st.keys()) {
			System.out.println(key);
		}
		
	}
	
	@Test
	public void empty() {
		LinearProbingHashST<Integer, String> st = new LinearProbingHashST<Integer, String>(4);
		assertTrue(st.isEmpty());
		st.put(1, "not empty");
		assertFalse(st.isEmpty());
	}
	
}
