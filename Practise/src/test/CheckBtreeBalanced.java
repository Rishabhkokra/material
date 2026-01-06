package test;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/* Java program to determine if binary tree is
height balanced or not */

/* A binary tree node has data, pointer to left child,
and a pointer to right child */

public class CheckBtreeBalanced {
	Node root;

	/*
	 * Returns true if binary tree with root as root is height-balanced
	 */
	boolean isBalanced(Node node) {
		int lh; /* for height of left subtree */

		int rh; /* for height of right subtree */

		/* If tree is empty then return true */
		if (node == null)
			return true;

		/* Get the height of left and right sub trees */
		lh = height(node.left);
		rh = height(node.right);

		if (Math.abs(lh - rh) <= 1 && isBalanced(node.left) && isBalanced(node.right))
			return true;

		/*
		 * If we reach here then tree is not height-balanced
		 */
		return false;
	}

	/* UTILITY FUNCTIONS TO TEST isBalanced() FUNCTION */
	/*
	 * The function Compute the "height" of a tree. Height is the number of nodes
	 * along the longest path from the root node down to the farthest leaf node.
	 */
	int height(Node node) {
		/* base case tree is empty */
		if (node == null)
			return 0;

		/*
		 * If tree is not empty then height = 1 + max of left height and right heights
		 */
		return 1 + Math.max(height(node.left), height(node.right));
	}

	int diameter(Node root) {
		// base case if tree is empty
		if (root == null)
			return 0;

		// get the height of left and right sub-trees
		int lheight = height(root.left);
		int rheight = height(root.right);

		// get the diameter of left and right sub-trees
		int ldiameter = diameter(root.left);
		int rdiameter = diameter(root.right);

		/*
		 * Return max of following three 1) Diameter of left subtree 2) Diameter of
		 * right subtree 3) Height of left subtree + height of right subtree + 1
		 */
		return Math.max(lheight + rheight + 1, Math.max(ldiameter, rdiameter));
	}

	// A wrapper over diameter(Node root)
	int diameter() {
		return diameter(root);
	}

	int findMaxUtil(Node node, Res res) {

		// Base Case
		if (node == null)
			return 0;

		// l and r store maximum path sum going through left
		// and right child of root respectively
		int l = findMaxUtil(node.left, res);
		int r = findMaxUtil(node.right, res);

		// Max path for parent call of root. This path must
		// include at-most one child of root
		int max_single = Math.max(Math.max(l, r) + node.data, node.data);

		// Max Top represents the sum when the Node under
		// consideration is the root of the maxsum path and
		// no ancestors of root are there in max sum path
		int max_top = Math.max(max_single, l + r + node.data);

		// Store the Maximum Result.
		res.val = Math.max(res.val, max_top);

		return max_single;
	}

	int findMaxSum() {
		return findMaxSum(root);
	}

	class Res {
		public int val;
	}

	static Stack<Integer> zigZagTraversal(Node root) {
		Queue<Node> q = new LinkedList<Node>();
		Stack<Integer> v = new Stack<Integer>();
		q.add(root);
		v.add(root.data);
		Node temp;

		// set initial level as 1, because root is
		// already been taken care of.
		int l = 1;

		while (!q.isEmpty()) {

			int n = q.size();

			for (int i = 0; i < n; i++) {

				// popping mechanism

				temp = q.poll();

				// pushing mechanism
				if (l % 2 != 0) {

					if (temp.right != null) {
						q.add(temp.right);
						v.add(temp.right.data);
					}
					if (temp.left != null) {
						q.add(temp.left);
						v.add(temp.left.data);
					}
				} else if (l % 2 == 0) {

					if (temp.left != null) {
						q.offer(temp.left);
						v.add(temp.left.data);
					}
					if (temp.right != null) {
						q.offer(temp.right);
						v.add(temp.right.data);
					}
				}
			}
			l++; // level plus one
		}
		return v;
	}

	// Returns maximum path sum in tree with given root
	int findMaxSum(Node node) {

		// Initialize result
		// int res2 = Integer.MIN_VALUE;
		Res res = new Res();
		res.val = Integer.MIN_VALUE;

		// Compute and return result
		findMaxUtil(node, res);
		return res.val;
	}

	// function should print the topView of
	// the binary tree
	private void TopView(Node root) {
		class QueueObj {
			Node node;
			int hd;

			QueueObj(Node node, int hd) {
				this.node = node;
				this.hd = hd;
			}
		}
		Queue<QueueObj> q = new LinkedList<QueueObj>();
		Map<Integer, Node> topViewMap = new TreeMap<Integer, Node>();

		if (root == null) {
			return;
		} else {
			q.add(new QueueObj(root, 0));
		}

		System.out.print("The top view of the tree is : ");

		// count function returns 1 if the container
		// contains an element whose key is equivalent
		// to hd, or returns zero otherwise.
		while (!q.isEmpty()) {
			QueueObj tmpNode = q.poll();
			if (!topViewMap.containsKey(tmpNode.hd)) {
				topViewMap.put(tmpNode.hd, tmpNode.node);
			}

			if (tmpNode.node.left != null) {
				q.add(new QueueObj(tmpNode.node.left, tmpNode.hd - 1));
			}
			if (tmpNode.node.right != null) {
				q.add(new QueueObj(tmpNode.node.right, tmpNode.hd + 1));
			}
		}
		for (Map.Entry<Integer, Node> entry : topViewMap.entrySet()) {
			System.out.print(entry.getValue().data + " ");
		}
	}

	static void flatten(Node root) {
		if (root == null)
			return;
		System.out.println();
		System.out.print("flattened tree ->   ");
		Stack<Node> st = new Stack<>();
		st.push(root);
		while (!st.isEmpty()) {
			Node cur = st.peek();
			System.out.print(st.pop().data + " ");

			if (cur.right != null) {
				st.push(cur.right);
			}
			if (cur.left != null) {
				st.push(cur.left);
			}
//			if (!st.isEmpty()) {
//				cur.right = st.peek();
//			}
//			cur.left = null;
		}
	}

	static int findCeil(Node root, int input) {
		int ceil = -1;

		while (root != null) {
			if (root.data == input) {
				return input; // If input is found in the tree, return it as ceil.
			} else if (root.data < input) {
				root = root.right; // Move to the right subtree if input is greater than current node's data.
			} else {
				ceil = root.data; // Mark ceil to be current node's data.
				root = root.left; // Move to the left subtree to find a closer ceil value.
			}
		}

		return ceil; // Return computed ceil value.
	}

	public static void main(String args[]) {
		CheckBtreeBalanced tree = new CheckBtreeBalanced();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.left.left.left = new Node(8);

		if (tree.isBalanced(tree.root))
			System.out.println("Tree is balanced");
		else
			System.out.println("Tree is not balanced");

		// Function Call
		System.out.println("The diameter of given binary tree is : " + tree.diameter());

		// Function call
		System.out.println("maximum path sum is : " + tree.findMaxSum());

		tree.TopView(tree.root);

		flatten(tree.root);

		System.out.println();
		System.out.println("ceil value: " + findCeil(tree.root, 2));

		System.out.println("----------------------------------");
		
		Stack<Integer> stack = zigZagTraversal(tree.root);
		for (Integer node : stack) {
			System.out.print(node + " ");
		}

	}

	static class Node {
		int data;
		Node left, right;

		Node(int d) {
			data = d;
			left = right = null;
		}
	}
}

// This code has been contributed by Mayank
// Jaiswal(mayank_24)
