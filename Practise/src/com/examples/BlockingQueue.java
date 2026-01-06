package com.examples;
// Java program that explains the internal

// implementation of BlockingQueue

import java.io.*;
import java.util.*;

class BlockingQueue<E> {

	// BlockingQueue using LinkedList structure
	// with a constraint on capacity
	private Queue<E> queue = new LinkedList<E>();

	// limit variable to define capacity
	private int limit = 10;

	// constructor of BlockingQueue
	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	// enqueue method that throws Exception
	// when you try to insert after the limit
	public synchronized void enqueue(E item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			wait();
		}
		//if (this.queue.size() == 0) {
			notifyAll();
	//	}
		this.queue.offer(item);
	}

	// dequeue methods that throws Exception
	// when you try to remove element from an
	// empty queue
	public synchronized E dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
	//	if (this.queue.size() == this.limit) {
			notifyAll();
	//	}

		return this.queue.poll();
	}

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<Integer> block = new BlockingQueue<Integer>(10);
		for (int i = 1; i < 12; i++) {
			block.enqueue(i);
			System.out.println(block.dequeue());
			
		}
		System.out.println(block.dequeue());
		System.out.println(block.dequeue());
	
	}
}
