package hw5;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW5Test {
	
	private void checkSol(char[][] grid, String path, int distance) {
		int size = grid.length;
		int row = -1;
		int col = -1;
		
		// Start at s;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if (grid[i][j] == 's') {
					row = i;
					col = j;
				}
			}
		}
		
		// Move according to path
		for(char c : path.toCharArray()) {
			switch(c) {
			case 'U':
				row -= 1;
				break;
			case 'D':
				row += 1;
				break;
			case 'R':
				col += 1;
				break;
			case 'L':
				col -= 1;
				break;
			default:
				fail("Illegal character in solution: " + c);
			}
			// Make sure you haven't moved outside the grid.
			assertTrue(row >= 0 && row < size && col >= 0 && col < size);
			// Make sure you haven't moved into a '*'
			assertTrue(grid[row][col] != '*');
		}
		// Make sure you end at 'f'
		assertTrue(grid[row][col] == 'f');
		
		// Make sure it is not longer than shortest distance.
		assertTrue(path.length() <= distance);
	}

	@Test
	public void toyTest() {
		String[] data = 
			{
					"s         ",
					"          ",
					"  *       ",
					"  *** ****",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *      f"
			};
		char[][] grid;
		
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
	} //toyTest()
	
	@Test
	public void failTest() { //No solution possible
		String[] data = 
			{
					"s         ",
					"          ",
					"  *       ",
					"  ********",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *      f"
			};
		
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		assertNull(solution);

		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		assertNull(solution);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		assertNull(solution);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		assertNull(solution);
	} //failTest()
	
	@Test
	public void middleS() { //s and f are elsewhere
		String[] data = 
			{
					"          ",
					"          ",
					"  *       ",
					"  *       ",
					" f*       ",
					"  *  s    ",
					"  ********",
					"  *       ",
					"  *       ",
					"          "
			};
		
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 11);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 11);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 11);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 11);
	} //middleS()
	
	
	@Test
	public void oneSolution() { //one possible solution
		String[] data = 
			{
					"**********",
					"**********",
					"*     ****",
					"* *** ****",
					"*f*** ****",
					"*****s****",
					"**********",
					"**********",
					"**********",
					"**********"
			};
		
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 9);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 9);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 9);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 9);
	} //oneSolution()

	
	@Test
	public void scaleUp() { //Includes loop, larger maze, oddly placed spaces
		String[] data = 
			{
					"******           *******  ",
					"****   ********   ******  ",
					"*     ********** *******  ",
					"* *** ********** *******  ",
					"*f* * ********** *******  ",
					"***     ******** *******  ",
					"** **** *****    *******  ",
					"** **** ******** *******  ",
					"**      ********  s*****  ",
					"******* ********* ******  ",
					"** **** ********* ******  ",
					"** **** ********* ******  ",
					"**  *   ********   *****  ",
					"************************  ",
					"******* *****       ****  ",
					"** **** ****************  ",
					"** **** ****************  ",
					"**      ********   *****  ",
					"******* ******** *******  ",
					"******* ****************  ",
					"** **** ****************  ",
					"** **** ****************  ",
					"**   *  *** ****  ******  ",
					"***************** ******  ",
					"******* ********* ******  ",
					"******* ***       ******  "
			};
		
		char[][] grid;
		grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 29);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 29);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 29);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 29);
	} //oneSolution()

	
}
