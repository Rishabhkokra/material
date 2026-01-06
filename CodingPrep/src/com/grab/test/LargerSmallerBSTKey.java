package com.grab.test;

public class LargerSmallerBSTKey {

	static class Node {

		int data;
		public Node left;
		public Node right;

		public Node(int data) {
			super();
			this.data = data;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

	}

	public static int findLargestSmallerKey(Node root, int num) {
		// Initialize result as -1 (will be returned if no smaller key found)
		int[] result = new int[] { -1 };

		// Helper function to perform in-order traversal
		inOrder(root, num, result);

		return result[0];
	}

	private static void inOrder(Node node, int num, int[] result) {
		
		if (node == null) {
			return;
		}

		// Traverse left subtree
		inOrder(node.left, num, result);

		// If current node's value is smaller than num, update result
		if (node.data < num) {
			result[0] = node.data;
		}

		// Traverse right subtree
		inOrder(node.right, num, result);
	}

	public static void main(String[] args) {
		Node root = new Node(20);
		root.left = new Node(9);
		root.right = new Node(25);
		root.right = new Node(12);
		root.right.left = new Node(11);
		root.right.right = new Node(14);
		root.left.left = new Node(5);

		System.out.println(findLargestSmallerKey(root, 17));

	}

}
