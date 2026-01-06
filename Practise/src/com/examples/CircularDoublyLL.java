package com.examples;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class CircularDoublyLL {

	public static void main(String[] args) {

		LinkedList<MyClass> linkedList = new LinkedList<MyClass>(100);

		for (int i = 1; i < 11; i++) {
			MyClass mm = new MyClass(i, (char) ('A' + (i - 1)));
			System.out.print(mm);
			linkedList.put(mm);

		}
		
		
		System.out.println();
		Node<MyClass> root = linkedList.getRoot();
		Node<MyClass> current = root;

		if (!linkedList.isEmpty()) {
			
			linkedList.forEachFrom(i->{
				System.out.println(i);
			}, current);
			
//			while (current != null) {
//				System.out.println(current);
//				if (current.next == root) {
//					break;
//				}
//				current = current.next;
//			}
		} else
			System.out.println("Empty LL !");

	}

	private static class Node<E> {

		E data;
		Node<E> next;
		Node<E> prev;

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return this.next;
		}

		public Node<E> getPrev() {
			return this.prev;
		}

		public E getData() {
			return this.data;
		}

		Node(E data) {
			this.data = data;
		}

		public String toString() {
			return "Node[prev=" + this.getPrev().getData() + ",data=" + this.getData() + ",next="
					+ this.getNext().getData() + "]";
		}

	}

	static class MyClass {

		int i;
		char c;

		public int getI() {
			return this.i;
		}

		public char getC() {
			return this.c;
		}

		MyClass(int i, char c) {
			this.i = i;
			this.c = c;
		}

		public String toString() {
			return "[" + this.i + "," + this.c + "]";
		}

	}

	static class LinkedList<E> {

		int capacity;
		Node<E> root;
		Node<E> tailNode;

		LinkedList(int capacity) {
			this.capacity = capacity;
		}

		 Node<E> succ(Node<E> p) {
		        if (p == (p = p.next))
		            p = root.next;
		        return p;
		    }
		
		public void forEachFrom(Consumer<? super E> action, Node<E> p) {
		        // Extract batches of elements while holding the lock; then
		        // run the action on the elements while not
		        final int batchSize = 64;       // max number of elements per batch
		        Object[] es = null;             // container for batch of elements
		        int n, len = 0;
		        do {
		            try {
		                if (es == null) {
		                    if (p == null) p = root.next;
		                    for (Node<E> q = p; q != null; q = succ(q))
		                        if (q.data != null && ++len == batchSize)
		                            break;
		                    es = new Object[len];
		                }
		                for (n = 0; p != null && n < len; p = succ(p))
		                    if ((es[n] = p.data) != null)
		                        n++;
		            }finally {
		            	
		            }
		            for (int i = 0; i < n; i++) {
		                @SuppressWarnings("unchecked") E e = (E) es[i];
		                action.accept(e);
		            }
		        } while (n > 0 && p != null);
		    }
		 
		public void put(E el) {

			if (root == null) {
				root = new Node<E>(el);
				tailNode = root;
				root.setNext(tailNode);
				root.setPrev(tailNode);
				return;
			}

			Node<E> newNode = new Node<E>(el);
			tailNode.next = newNode;
			newNode.prev = tailNode;
			tailNode = newNode;
			tailNode.next = root;
			root.prev = tailNode;
		}

		public boolean isEmpty() {
			return root == null;
		}

		public Node<E> getRoot() {
			return root;
		}

	}

}
