package com.grab.test;

public class CheckIfSumTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node root = new Node(26);
		root.left = new Node(10);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(6);

		root.right.left = new Node(3);

		System.out.println(isSumTree(root));

	}

	static int sum(Node root) {
		if (root == null)
			return 0;

		return sum(root.left) + root.data + sum(root.right);
	}

	// Returns true if sum property holds for the
	// given node and both of its children
	static boolean isSumTree(Node root) {
		int ls, rs;

		// If root is null or it's a leaf node
		// then return true
		if (root == null || (root.left == null && root.right == null))
			return true;

		// Get sum of nodes in left and right subtrees
		ls = sum(root.left);
		rs = sum(root.right);

		// If the root and both of its children satisfy
		// the property, return true else false
		return (root.data == ls + rs) && isSumTree(root.left) && isSumTree(root.right);
	}

	static class Node {

		public Node left;
		public Node right;
		public int data;

		public Node(int val) {
			this.data = val;
		}
	}

}
