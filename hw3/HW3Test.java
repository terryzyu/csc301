package hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HW3Test {
	@Test
	public void test5Empty() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		for(int i = -1000; i < 1000; i++)
			assertFalse(s.contains(i));
		assertEquals("", s.levelOrder());
	}
	
	@Test
	public void test5Single() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(-4);
		for(int i = -1000; i < 1000; i++) {
			if (i == -4)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		for(int i = 0; i < 100; i++)
			s.put(-4);
		assertEquals(0, s.getHeight());
		assertEquals("(-4)", s.levelOrder());
	}
	
	@Test
	public void test10LoHi() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(4);
		s.put(222);
		for(int i = -1000; i < 1000; i++) {
			if (i == 4 || i == 222)
				assertTrue(s.contains(i));
			else
				assertFalse(s.contains(i));
		}
		assertEquals("(4|222)", s.levelOrder());
	}
	
	//Everything below is mine
	@Test
	public void largeInsert() { //Handle many insertions without a violation
		TwoThreeIntSet s = new TwoThreeIntSet();
		int insert = 0;
		for(int x = 0; x < 999999; x++) {
			insert = (int)(Math.random() * 999999999);
			if(insert % 2 == 0)
				insert *= -1; //Insert negative if even
			s.put(insert);
		}
	}
	
	@Test
	public void collisionInsert() { //Handle many insertions that overlap without violation
		TwoThreeIntSet s = new TwoThreeIntSet();
		int insert = 0;
		for(int x = 0; x < 999999; x++) {
			insert = (int)(Math.random() * 10);
			s.put(insert);
		}
		System.out.println(s.levelOrder());
	}
	
	@Test
	public void searchxmplTextbook() { //Example from textbook, insert SEARCHXMPL
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(18);
		s.put(4);
		s.put(0);
		s.put(17);
		s.put(2);
		s.put(7);
		s.put(23);
		s.put(12);
		s.put(15);
		s.put(11);
		assertEquals(2, s.getHeight());
		assertEquals("(12),(4),(17),(0|2),(7|11),(15),(18|23)", s.levelOrder());
	}
	
	@Test
	public void acehlmprsxTextbook() { //Example from textbook, insert ACEHLMPRSX
		TwoThreeIntSet s = new TwoThreeIntSet();
		s.put(0);
		s.put(2);
		s.put(4);
		s.put(7);
		s.put(11);
		s.put(12);
		s.put(15);
		s.put(17);
		s.put(18);
		s.put(23);
		assertEquals(2, s.getHeight());
		assertEquals("(7),(2),(12|17),(0),(4),(11),(15),(18|23)", s.levelOrder());
	}
}
