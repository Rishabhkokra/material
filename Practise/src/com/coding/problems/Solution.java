package com.coding.problems;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class HelloWorld {
    public static void main(String[] args) {
       
        
        LinkedList<MyClass> linkedList = new LinkedList<MyClass>(100);

		for (int i = 1; i < 11; i++) {
			MyClass mm = new MyClass(i, (char) ('A' + (i-1)));
		    System.out.print(mm);
		linkedList.put(mm);
	
		}

        System.out.println();
		Node<MyClass> root = linkedList.getRoot();
		Node<MyClass> current = root;

		if (!linkedList.isEmpty()) {
			while (current != null) {
				System.out.println(current);
				if (current.next == root) {
					break;
				}
				current = current.next;
			}
		}
		else
		System.out.println("Empty LL !");
		
    }
    
    private static class Node<E> {

		E data;
		Node<E> next;
		Node<E> prev;
		
		public void setNext(Node<E> next){
		    this.next=next;
		}
			public void setPrev(Node<E> prev){
		    this.prev=prev;
		}
		
		public Node<E> getNext(){
		    return this.next;
		}
		
		public Node<E> getPrev(){
		    return this.prev;
		}
		public E getData(){
		    return this.data;
		}

		Node(E data) {
			this.data = data;
		}
		
		public String toString(){
		    return "Node[prev="+this.getPrev().getData()+",data="+this.getData()+",next="+this.getNext().getData()+"]";
		}

	}

	static class MyClass {

		int i;
		char c;
		
		public int getI(){
		    return this.i;
		}
		
		public char getC(){
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