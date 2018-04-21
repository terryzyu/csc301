package hw3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

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
	
	@Test
	public void insertlol() {
		TwoThreeIntSet s = new TwoThreeIntSet();
		for(int i = -1000; i < 1000; i = i + 2) {
			s.put(i);
		}
		for(int i = -999; i < 999; i = i + 2) {
			s.put(i);
		}
		for(int i = -2000; i < -1001; i = i + 2) {
			s.put(i);
		}
		for(int i = 2000; i < 3001; i++) {
			s.put(i);
		}
		System.out.print(s.levelOrder());
	}
}
