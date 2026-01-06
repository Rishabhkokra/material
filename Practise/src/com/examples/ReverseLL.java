package com.examples;

public class ReverseLL {

	private static class Node {

		int data;
		private Node next;

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

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	public static void main(String[] args) {
		Node nextNode=null;
		for (int i = 1; i < 11; i++) {
			Node node = new Node(i);
			if(i==1) {
				nextNode=node;
			}
			else {
				nextNode.next=node;
				nextNode=node;
			}
			
		}

	}

}
