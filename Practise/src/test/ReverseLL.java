package test;

import test.Sort102LL.Node;

public class ReverseLL {

	static class Node {
		int data;
		Node next;

		// Constructor to initialize the node with data
		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	static Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		node = prev;
		return node;
	}

	// 1->2->3->4->5

	public static void main(String[] args) {
		Node node = new Node(1);
		Node head = node;
		for (int i = 2; i < 11; i++) {
			node.next = new Node(i);
			node = node.next;
		}

//		while (head != null) {
//			System.out.print(head.data + ",");
//			head = head.next;
//		}

		head=reverse(head);
		while (head != null) {
			System.out.print(head.data + ",");
			head = head.next;
		}
//		Node tail = head, last = null;
//		while (tail != null) {
//			last = tail;
//			tail = tail.next;
//		}
//		tail = last;
//
//		Node current = head, prev = null;
//
//		while (current != null) {
//			Node tmp = current.next;
//			current.next = prev;
//			prev = current;
//
//			current = tmp;
//
//		}
//
//		while (tail != null) {
//			System.out.print(tail.data + " ");
//			tail = tail.next;
//		}
	}

}
