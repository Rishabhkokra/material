package com.grab.test;

// Java Program to find Inorder Successor
// in Binary Search Tree

class Node {
	int data;
	Node left, right;

	Node(int x) {
		data = x;
		left = null;
		right = null;
	}
}

class BSTSuccessorSearch {

	// Function to find leftmost node in
	// subtree with given root.
	static Node leftMost(Node node) {
		Node curr = node;
		while (curr.left != null)
			curr = curr.left;
		return curr;
	}

	static Node getSucc(Node root, int target) {

		// Base Case 1: No Inorder Successor
		if (root == null)
			return null;

		// Base Case 2: root is same as target and
		// right child is not empty
		if (root.data == target && root.right != null)
			return leftMost(root.right);

		// Use BST properties to search for
		// target and successor
		Node succ = null;
		Node curr = root;
		while (curr != null) {

			// If curr node is greater, then it
			// is a potential successor
			if (target < curr.data) {
				succ = curr;
				curr = curr.left;
			}

			// If smaller, then successor must
			// be in the right child
			else if (target >= curr.data)
				curr = curr.right;
		}

		return succ;
	}

	public static void main(String[] args) {

		// Construct a BST
		// 20
		// / \
		// 8 22
		// / \
		// 4 12
		// / \
		// 10 14
		Node root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(22);
		root.left.left = new Node(4);
		root.left.right = new Node(12);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(14);

		int target = 4;
		Node succ = getSucc(root, target);
		if (succ != null)
			System.out.println(succ.data);
		else
			System.out.println("null");
	}
}