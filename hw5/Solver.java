package hw5;

import static org.junit.Assert.fail;

public class Solver {
	public static String solve(char[][] grid) {
		
		//Grid will always be a square
		int vertices = grid.length * grid.length; //Each cell/index is a vertex for the graph
		int size = grid.length; //Graph is a square so length and width are the same. Storing in variable to avoid multiple calls
		Graph graph = new Graph(vertices); //Creates graph with each cell as a vertex
		graph = generateEdges(graph, grid, size); //Populates the graph object with edges
		
		int startVertex = vertex(grid, 's', size); //Gets vertex of start
		int endVertex = vertex(grid, 'f', size); //Gets vertex of finish
		
		BreadthFirstPaths bfp = new BreadthFirstPaths(graph, startVertex); //Generates bfp of start vertex
		int[] pathIntegers = new int[bfp.distTo(endVertex) + 1]; //Stores the path of travel through vertices from s to f
		
		//Populates array with movements
		int iter = 0; 
		for(Integer i: bfp.pathTo(endVertex)) {
			pathIntegers[iter] = i;
			iter++;
		}
		
		//Interprets array and returns solution of shortest path
		String solution = stringPath(pathIntegers, size); 
		
		// TODO
		/*
		 * 1. Construct a graph using grid
		 * 2. Use BFS to find shortest path from start to finish
		 * 3. Return the sequence of moves to get from start to finish
		 */
		
		// Hardcoded solution to toyTest
		//return "RRRDDDDDDDDDRRRRRR";
		return solution;
	}
	
	private static String stringPath(int[] pathIntegers, int size) {
		int num = -1;
		String solution = "";
		for (int x = 0; x < pathIntegers.length - 1; x++) {
			num = pathIntegers[x + 1] - pathIntegers[x];
			switch (num) {
			case -1:
				solution += 'L';
				break;
			case 1:
				solution += 'R';
				break;
			case 10:
				solution += 'D';
				break;
			case -10:
				solution += 'U';
				break;
			default:
				fail("Illegal Movement: " + num);
			}
		}
		return solution;
	} //stringPath()

	private static int vertex(char [][] grid, char letter, int size) {
		int row = -1;
		int col = -1;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if (grid[i][j] == letter) {
					row = i;
					col = j;
				}
			}
		}
		return row * size + col;
	} //vertex()
	
	private static Graph generateEdges(Graph graph, char[][] grid, int size) {
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				if(grid[row][col] != '*') {
					graph = checkUp(grid, row, col, graph, size);
					graph = checkDown(grid, row, col, graph, size);
					graph = checkLeft(grid, row, col, graph, size);
					graph = checkRight(grid, row, col, graph, size);
				}
			}
		}
		
		return graph;
	} //generateEdges
	
	private static Graph checkRight(char[][] grid, int row, int col, Graph graph, int size) {
		try {
			if(grid[row][col+1] != '*')
				graph.addEdge(row * size + col, row * size + col + 1);
		}catch(ArrayIndexOutOfBoundsException e) {};
		return graph;
		
	} //checkRight()

	private static Graph checkLeft(char[][] grid, int row, int col, Graph graph, int size) {
		try {
			if(grid[row][col-1] != '*')
				graph.addEdge(row * size + col, row * size + col - 1);
		}catch(ArrayIndexOutOfBoundsException e) {};
		return graph;
	} //checkLeft()

	private static Graph checkDown(char[][] grid, int row, int col, Graph graph, int size) {
		try {
			if(grid[row+1][col] != '*')
				graph.addEdge(row * size + col, (row + 1) * size + col);
		}catch(ArrayIndexOutOfBoundsException e) {};
		return graph;
	} //checkDown()

	private static Graph checkUp(char[][] grid, int row, int col, Graph graph, int size) {
		try {
			if(grid[row-1][col] != '*')
				graph.addEdge(row * size + col, (row - 1) * size + col);
		}catch(ArrayIndexOutOfBoundsException e) {};
		return graph;
	} //checkUp()
	
}
