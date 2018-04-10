package hw2;

import java.util.LinkedList;

/* ***********************************************************************
 * A simple BST with int keys and no values
 *
 * Complete each function below.
 * Write each function as a separate recursive definition (do not use more than one helper per function).
 * Depth of root==0.
 * Height of leaf==0.
 * Size of empty tree==0.
 * Height of empty tree=-1.
 *
 * TODO: complete the functions in this file.
 * DO NOT change the Node class.
 * DO NOT change the name or type of any function (the first line of the function)
 *
 * Restrictions:
 *  - no loops (you cannot use "while" "for" etc...)
 *  - only one helper function per definition
 *  - no fields (static or non static).  Only local variables
 *************************************************************************/
public class MyIntSET {
	private Node root;

	private static class Node {
		public final int key;
		public Node left, right;

		public Node(int key) {
			this.key = key;
		}
	}

	public void printInOrder() {
		printInOrder(root);
	}

	private void printInOrder(Node n) {
		if (n == null)
			return;
		printInOrder(n.left);
		System.out.println(n.key);
		printInOrder(n.right);
	}

	// the number of nodes in the tree
	public int size() {
		return sizeHelper(root);
	} //size()

	private int sizeHelper(Node n) {
		if (n == null) //Either root or no leaf
			return 0;
		return 1 + sizeHelper(n.left) + sizeHelper(n.right); //Increments and looks at other branches
	} //sizeHelper()

	// Recall the definitions of height and depth.
	// in the BST with level order traversal "41 21 61 11 31",
	// node 41 has depth 0, height 2
	// node 21 has depth 1, height 1
	// node 61 has depth 1, height 0
	// node 11 has depth 2, height 0
	// node 31 has depth 2, height 0
	// height of the whole tree is the height of the root

	// 20 points
	/*
	 * Returns the height of the tree. For example, the BST with level order
	 * traversal 50 25 100 12 37 150 127 should return 3.
	 * 
	 * Note that the height of the empty tree is defined to be -1.
	 */
	public int height() {
		if (root == null) //Empty tree
			return -1;
		return heightHelper(root);
	} //height()

	private int heightHelper(Node n) {
		if (n == null) //No leaf 
			return 0;
		return 1 + Math.max(heightHelper(n.left), heightHelper(n.right)); //Determines the longest branch and adds one for root
	} //heightHelper()

	// 20 points
	/*
	 * Returns the number of nodes with odd keys. For example, the BST with level
	 * order traversal 50 25 100 12 37 150 127 should return 3 (25, 37, and 127).
	 */
	public int sizeOdd() {
		if (root == null) //Empty tree
			return 0;
		return sizeOddHelper(root);
	} //sizeOdd()

	private int sizeOddHelper(Node n) {
		if (n == null) //No leaf
			return 0;

		int count = 0; //Keeps track of number of odds
		if (n.key % 2 == 1)
			count = 1;
		
		return count + sizeOddHelper(n.left) + sizeOddHelper(n.right); //Adds 1 if there is an odd, otherwise adds 0 and continues searching
	} //sizeOddHelper()

	// 20 points
	/*
	 * Returns true if this tree and that tree "look the same." (i.e. They have the
	 * same keys in the same locations in the tree.) Note that just having the same
	 * keys is NOT enough. They must also be in the same positions in the tree.
	 */
	public boolean treeEquals(MyIntSET that) {
		return treeEqualsHelper(root, that.root);
	} //treeEquals()

	
	/*
	 * Example: tree1.treeEquals(tree2)
	 * orig refers to tree1
	 * other refers to tree2
	 */
	private boolean treeEqualsHelper(Node orig, Node other) {
		if (orig == null && other == null) //Either roots or leafs are null
			return true;
		if (orig != null && other != null) { //Contains data
			return (orig.key == other.key //Compares key
					&& treeEqualsHelper(orig.left, other.left) //Compares left node
					&& treeEqualsHelper(orig.right, other.right)); //Compares right node
		}
		return false;
	} //treeEqualsHelper()

	
	// 10 points
	/* Returns the number of nodes in the tree, at exactly depth k.
	 * For example, given BST with level order traversal 50 25 100 12 37 150 127
	 * the following values should be returned
	 * t.sizeAtDepth(0) == 1		[50]
	 * t.sizeAtDepth(1) == 2		[25, 100]
	 * t.sizeAtDepth(2) == 3		[12, 37, 150]
	 * t.sizeAtDepth(3) == 1		[127]
	 * t.sizeAtDepth(k) == 0 for k >= 4
	 */
	public int sizeAtDepth(int k) {
		return sizeAtDepthHelper(root, 0, k); //Starts at root, hence 0
	} //sizeAtDepth()

	private int sizeAtDepthHelper(Node n, int current, int depth) {
		if (n == null || current > depth) return 0; //Out of bounds or no leaf
		if (depth == 0)				 	  return 1; //Root
		if (depth == current)			  return 1; //Max depth

		return sizeAtDepthHelper(n.right, current + 1, depth) 
				+ sizeAtDepthHelper(n.left, current + 1, depth);

	} //sizeAtDepthHelper()

	// 10 points
	/*
	 * Returns the number of nodes in the tree "above" depth k. 
	 * Do not include nodes at depth k.
	 * For example, given BST with level order traversal 50 25 100 12 37 150 127
	 * the following values should be returned 
	 * t.sizeAboveDepth(0) == 0
	 * t.sizeAboveDepth(1) == 1					[50]
	 * t.sizeAboveDepth(2) == 3					[50, 25, 100]		
	 * t.sizeAboveDepth(3) == 6					[50, 25, 100, 12, 37, 150] 
	 * t.sizeAboveDepth(k) == 7 for k >= 4		[50, 25, 100, 12, 37, 150, 127] 
	 */
	public int sizeAboveDepth(int k) {
		return sizeAboveDepthHelper(root, 0, k);//Starts at root, hence 0
	} //sizeAboveDepth()

	private int sizeAboveDepthHelper(Node n, int current, int depth) {
		//Similar to sizeAtDepth, but you're counting nodes as you travel down, hence the 1+ on the return. 
		if (n != null && current < depth) {
			return 1 + sizeAboveDepthHelper(n.left, current + 1, depth)
					 + sizeAboveDepthHelper(n.right, current + 1, depth);
		}
		else
			return 0;
	} //sizeAboveDepthHelper()

	// A tree is perfect if for every node, size of left == size of right
	// hint: in the helper, return -1 if the tree is not perfect, otherwise return
	// the size
	// 10 points
	/*
	 * Returns true if for every node in the tree has the same number of nodes to
	 * its left as to its right.
	 */

	// MUST EDIT CODE
	public boolean isPerfectlyBalancedS() {
		int result = isPerfectlyBalancedSHelper(root, -1); // Assumes unbalanced to begin with, hence -1
		return result != -1;
	} //isPerfectlyBalancedS()

	// MUST EDIT CODE
	private int isPerfectlyBalancedSHelper(Node n, int balanceNum) {
		if (n == null) { //Root or no leaf
			return 0;
		}

		int leftBranch = isPerfectlyBalancedSHelper(n.left, balanceNum); //Determines number of nodes in left branch
		
		if (leftBranch != balanceNum) { //Should be 0 when perfectly balanced, otherwise -1
			int rightBranch = isPerfectlyBalancedSHelper(n.right, balanceNum); //Same procedure as leftBranch
			
			if (rightBranch != balanceNum) 
				if ((leftBranch - rightBranch) == 0) //Perfectly balanced if left size and right size are 0
					return 1 + Math.max(leftBranch, rightBranch);
		}

		return balanceNum;
	} //isPerfectlyBalancedSHelper()

	/*
	 * Mutator functions
	 * HINT: This is easier to write if the helper function returns Node, rather than void
	 * similar to recursive mutator methods on lists.
	 */

	// 10 points
	/* Removes all subtrees with odd roots (if node is odd, remove it and its descendants)
	 * A node is odd if it has an odd key.
	 * If the root is odd, then you should end up with the empty tree.
	 * For example, when called on a BST
	 * with level order traversal 50 25 100 12 37 150 127
	 * the tree will be changed to have level order traversal 50 100 150
	 */
	public void removeOddSubtrees() {
		root = removeOddSubtreesHelper(root);
	} //removeOddSubtrees()

	private Node removeOddSubtreesHelper(Node n) {
		if (n == null || n.key % 2 == 1) //Does comparison on empty tree, no leaf, or odd. Sets branch to null i.e. deleting branch
			return null;
		n.left = removeOddSubtreesHelper(n.left); //Checks left
		n.right = removeOddSubtreesHelper(n.right); //Checks right
		
		return n; //Returns modified tree
		
	} //removeOddSubtreesHelper()

	/*
	 * ***************************************************************************
	 * Some methods to create and display trees
	 *****************************************************************************/

	// Do not modify "put"
	public void put(int key) {
		root = put(root, key);
	}

	private Node put(Node n, int key) {
		if (n == null)
			return new Node(key);
		if (key < n.key)
			n.left = put(n.left, key);
		else if (key > n.key)
			n.right = put(n.right, key);
		return n;
	}

	// This is what contains looks like
	public boolean contains(int key) {
		return contains(root, key);
	}

	private boolean contains(Node n, int key) {
		if (n == null)
			return false;
		if (key < n.key)
			return contains(n.left, key);
		else if (key > n.key)
			return contains(n.right, key);
		return true;
	}

	// Do not modify "copy"
	public MyIntSET copy() {
		MyIntSET that = new MyIntSET();
		for (int key : levelOrder())
			that.put(key);
		return that;
	}

	// Do not modify "levelOrder"
	public Iterable<Integer> levelOrder() {
		LinkedList<Integer> keys = new LinkedList<Integer>();
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node n = queue.remove();
			if (n == null)
				continue;
			keys.add(n.key);
			queue.add(n.left);
			queue.add(n.right);
		}
		return keys;
	}

	// Do not modify "toString"
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int key : levelOrder())
			sb.append(key + " ");
		return sb.toString();
	}

	public void prettyPrint() {
		if (root == null)
			System.out.println("<EMPTY>");
		else
			prettyPrintHelper(root, "");
	}

	private void prettyPrintHelper(Node n, String indent) {
		if (n != null) {
			System.out.println(indent + n.key);
			indent += "    ";
			prettyPrintHelper(n.left, indent);
			prettyPrintHelper(n.right, indent);
		}
	}

	public static void main(String[] args) {
		MyIntSET s = new MyIntSET();
		s.put(15);
		s.put(11);
		s.put(21);
		s.put(8);
		s.put(13);
		s.put(16);
		s.put(18);
		s.printInOrder();
	}
}
