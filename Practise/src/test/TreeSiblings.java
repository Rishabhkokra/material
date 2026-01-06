package test;

// Java program to check if two nodes
// are siblings
import java.util.*;

class TreeSiblings {

	// Binary Tree Node
	static class Node {
		int data;
		Node left, right;
	};

	// Utility function to create a
	// new node
	static Node newNode(int data) {
		Node node = new Node();
		node.data = data;
		node.left = node.right = null;

		return (node);
	}

	static Node root = null;

	// Function to find out if two nodes are siblings
	static boolean CheckIfNodesAreSiblings(Node root, int data_one, int data_two) {
		if (root == null)
			return false;

		// Compare the two given nodes with
		// the childrens of current node
		if (root.left != null && root.right != null) {
			int left = root.left.data;
			int right = root.right.data;

			if (left == data_one && right == data_two)
				return true;
			else if (left == data_two && right == data_one)
				return true;
		}

		// Check for left subtree
		if (root.left != null)
			if (CheckIfNodesAreSiblings(root.left, data_one, data_two))
				return true;

		// Check for right subtree
		if (root.right != null)
			if (CheckIfNodesAreSiblings(root.right, data_one, data_two))
				return true;
		return false;
	}

	// Driver code
	public static void main(String[] args) {
		root = newNode(1);
		root.left = newNode(2);
		root.right = newNode(3);
		root.left.left = newNode(4);
		root.right.left = newNode(5);
		root.right.right = newNode(6);
		root.left.left.right = newNode(7);

		int data_one = 5;
		int data_two = 6;

		if (CheckIfNodesAreSiblings(root, data_one, data_two))
			System.out.print("YES");
		else
			System.out.print("NO");
	}
}

// This code is contributed by Rajput-Ji
