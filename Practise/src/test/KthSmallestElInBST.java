package test;

import java.util.Stack;

public class KthSmallestElInBST {
	static class Node {
		int data;
		Node left, right;

		Node(int key) {
			data = key;
			left = right = null;
		}
	}

	static Node insert(Node root, int key) {
		if (root == null)
			return new Node(key);

		if (key < root.data)
			root.left = insert(root.left, key);
		else if (key > root.data)
			root.right = insert(root.right, key);

		return root;
	}

	static int kthSmallest(Node root, int k) {
		Stack<Node> stack = new Stack<>();

		while (root != null || !stack.empty()) {

			while (root != null) {
				stack.push(root);
				root = root.left;
			}

			root = stack.pop();

			if (--k == 0)
				return root.data;

			root = root.right;
		}

		return -1;
	}

	public static void main(String[] args) {
		Node root = null;
		int[] keys = { 20, 8, 22, 4, 12, 10, 14 };

		for (int x : keys)
			root = insert(root, x);

		int k = 4;
		int kth_smallest = kthSmallest(root, k);
		if (kth_smallest != -1)
			System.out.println("K-th smallest element in BST is: " + kth_smallest);
		else
			System.out.println("Invalid input");
	}
}