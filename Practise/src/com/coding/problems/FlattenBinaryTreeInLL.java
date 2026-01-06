package com.coding.problems;

import java.io.*;
import java.util.Stack;

class Node {
	int key;
	Node left, right;

	Node(int item) {
		key = item;
		left = right = null;
	}
}

public class FlattenBinaryTreeInLL {
	// Function to flatten binary tree into
// linked list
	public static void flatten(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node current = stack.pop();

			if (current.right != null) {
				stack.push(current.right);
			}

			if (current.left != null) {
				stack.push(current.left);
			}

			if (!stack.isEmpty()) {
				current.right = stack.peek();
			}

			current.left = null;
		}
	}

	// Function to perform an inorder traversal and
// print the elements
	public static void inorder(Node root) {
		if (root == null) {
			return;
		}
		inorder(root.left);
		System.out.print(root.key + " ");
		inorder(root.right);
	}

	public static void main(String[] args) {
		/*
		 * Construct the binary tree: 1 / \ 2 5 / \ \ 3 4 6
		 */
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(5);
		root.left.left = new Node(3);
		root.left.right = new Node(4);
		root.right.right = new Node(6);
		// Flatten the binary tree into a linked list
		flatten(root);
		System.out.print("Inorder traversal after flattening binary tree ");
		inorder(root);
	}
}
