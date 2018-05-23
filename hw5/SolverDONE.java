package hw5;

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
		
		if(bfp.hasPathTo(endVertex)) {
			int[] pathIntegers = new int[bfp.distTo(endVertex) + 1]; //Stores the path of travel through vertices from s to f
			
			//Populates array with movements
			int iter = 0; 
			for(Integer i: bfp.pathTo(endVertex)) {
				pathIntegers[iter] = i;
				iter++;
			}
			
			//Generates and stores solution
			String solution = stringPath(pathIntegers, size); 
	
			return solution;
		}
		else
			return null; //If there is no solution i.e. no path from s to f
	}
	
	/**
	 * Takes in path of vertices and interprets it to generate
	 * movement path to get from start to finish
	 * @param pathIntegers
	 * @param size
	 * @return string of movements in readable characters
	 */
	
	private static String stringPath(int[] pathIntegers, int size) {
		int num; //Calculates difference between two vertices
		String solution = ""; //Contains readable movements
		
		//Iterates through and translates movements to characters
		for (int x = 0; x < pathIntegers.length - 1; x++) {
			num = pathIntegers[x + 1] - pathIntegers[x];
			if(num == -1)
				solution += 'L';
			if(num == 1)
				solution += 'R';
			if(num > 1)
				solution += 'D';
			if(num < -1)
				solution += 'U';
		}
		
		return solution;
	} //stringPath()

	
	/**
	 * Converts appropriate index location of letter to vertex stored in Graph object
	 * @param grid
	 * @param letter
	 * @param size
	 * @return respective graph vertex number
	 */
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
	
	
	/**
	 * Generates the appropriate edges between vertices. 
	 * Valid edges are up, down, left, and right with respect to vertex.
	 * @param graph
	 * @param grid
	 * @param size
	 * @return a graph populated with edges
	 */
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
	
	
	/**
	 * The next 4 methods (checkX) determine if there is a valid movement in the X direction
	 * and isn't a *. If so it adds an edge to the graph between the two vertices.
	 * @param grid
	 * @param row
	 * @param col
	 * @param graph
	 * @param size
	 * @return graph with added edge if applicable. 
	 */
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
