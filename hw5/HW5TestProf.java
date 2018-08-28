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
		
		// Make sure it is shortest.
		assertTrue(path.length() <= distance);
	}

	@Test
	public void toyTest() {
		String[] data = 
			{
					"s         ",
					"          ",
					"  *       ",
					"  *  *****",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *       ",
					"  *      f"
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
		
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 18);
	}
	
	@Test
	public void test10Empty() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                  f ",
					"                    ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
	}
	
	@Test
	public void test10KeyHole() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"  ******** ******** ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                  f ",
					"                    ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 32);
	}
	
	@Test
	public void test10singleObstacle() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" **************     ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"    f               ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
	}
	
	@Test
	public void test10DoubleObstacle() {
		String[] data =
			{
					"                    ",
					"  s                 ",
					"                    ",
					"                    ",
					" **************     ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" **************     ",
					"                    ",
					"                    ",
					"                    ",
					"    f               ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 24);
	}
	
	@Test
	public void test10ZigZag() {
		String[] data =
			{
					"       s            ",
					"                    ",
					"                    ",
					"                    ",
					"******************* ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" *******************",
					"                    ",
					"                    ",
					"                    ",
					"       f            ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 57);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 57);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 57);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 57);
	}
	
	@Test
	public void test10ZigZagZig() {
		String[] data =
			{
					"       s            ",
					"                    ",
					"                    ",
					"                    ",
					"******************* ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					" *******************",
					"                    ",
					"******************* ",
					"                    ",
					"       f            ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 81);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 81);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 81);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 81);
	}
	
	@Test
	public void test10L() {
		String[] data =
			{
					"                    ",
					"      *             ",
					"      *             ",
					"      *             ",
					"   s  *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *             ",
					"      *           f ",
					"      **************",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 36);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 36);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 36);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 36);
	}
	
	@Test
	public void test10T1() {
		String[] data =
			{
					"                    ",
					"                    ",
					"                    ",
					"   **************   ",
					"         *          ",
					"       s * f        ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 20);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 20);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 20);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 20);
	}
	
	@Test
	public void test10T2() {
		String[] data =
			{
					"                    ",
					"                    ",
					"                    ",
					"     *********      ",
					"         *          ",
					"       s * f        ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"         *          ",
					"                    ",
			};
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		checkSol(grid, solution, 22);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 22);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 22);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 22);
	}
	
	@Test
	public void test10Maze() {
		String[] data =
			{
					"             *       ",
					" *** *** * * * *** **",
					"  s*   * * * *   *   ",
					" ******* ******* ****",
					"   *   * *           ",
					" *** * * * ***** *** ",
					" * * *   * *   * * * ",
					" * * * * * * * * * * ",
					"   * * *   * * * * * ",
					"** * *** *** * * * * ",
					"   * * *     * * * * ",
					" * * * ******* * * * ",
					" * *           *   * ",
					" * ************* ****",
					" *     *   *         ",
					" ******* * * ******* ",
					"   *     *   *     * ",
					" * *** * ***** *** **",
					" *     *   * *   * *f",
					" ********* * *** * * ",
					"         *       *   "
			};
		
		char[][] grid = GridUtilities.fromStringArray(data);
		String solution = Solver.solve(grid);
		System.out.println(solution);
		checkSol(grid, solution, 62);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 62);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 62);
		grid = GridUtilities.rotateClockwise(grid);
		solution = Solver.solve(grid);
		checkSol(grid, solution, 62);
	}

}
