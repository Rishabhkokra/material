package com.grab.test;
// Java program to convert sorted 
// array to BST.
import java.util.ArrayList;



public class TreeFromArray {
	
	static class Node {
	    int data;
	    Node left, right;
	    
	    Node(int data) {
	        this.data = data;
	        this.left = null;
	        this.right = null;
	    }
	}
    
    // Recursive function to construct BST
    static Node sortedArrayToBSTRecur(int[] arr, int start, int end) {
        if (start > end) return null;
    
        // Find the middle element
        int mid = start + (end - start) / 2;
    
        // Create root node
        Node root = new Node(arr[mid]);
    
        // Create left subtree
        root.left = sortedArrayToBSTRecur(arr, start, mid - 1);
    
        // Create right subtree
        root.right = sortedArrayToBSTRecur(arr, mid + 1, end);
    
        return root;
    }
    
    static Node sortedArrayToBST(int[] arr) {
        return sortedArrayToBSTRecur(arr, 0, arr.length - 1);
    }

    static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // Function to insert nodes in level order
    public static Node insertLevelOrder(int[] arr, int i)
    {
          Node root = null;
        // Base case for recursion
        if (i < arr.length) {
            root = new Node(arr[i]);

            // insert left child
            root.left = insertLevelOrder(arr, 2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        Node root = sortedArrayToBST(arr);
        
        insertLevelOrder(arr, 0);
        
        preOrder(root);
    }
}