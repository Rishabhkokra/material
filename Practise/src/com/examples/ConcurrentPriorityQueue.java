package com.examples;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConcurrentPriorityQueue {

	private PriorityQueue<PriorityItem> priorityQueue;
	private Lock lock;

	public ConcurrentPriorityQueue() {
		this.priorityQueue = new PriorityQueue<>();
		this.lock = new ReentrantLock(true);
	}

	public void insert(String element, int priority) {
		lock.lock();
		try {
			priorityQueue.add(new PriorityItem(element, priority));
		} finally {
			lock.unlock();
		}
	}

	public String extract() {
		lock.lock();
		try {
			PriorityItem item = priorityQueue.poll();
			return item.getElement();
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		lock.lock();
		try {
			return priorityQueue.size();
		} finally {
			lock.unlock();
		}
	}

	private static class PriorityItem implements Comparable<PriorityItem> {
		private String element;
		private int priority;

		public PriorityItem(String element, int priority) {
			this.element = element;
			this.priority = priority;
		}

		public String getElement() {
			return element;
		}

		@Override
		public int compareTo(PriorityItem other) {
			return Integer.compare(this.priority, other.priority);
		}
	}

	public static void main(String[] args) {
		final ConcurrentPriorityQueue priorityQueue = new ConcurrentPriorityQueue();

		Thread producerThread1 = new Thread(() -> {
			priorityQueue.insert("Task 1", 3);
			priorityQueue.insert("Task 2", 1);
		});

		Thread producerThread2 = new Thread(() -> {
			priorityQueue.insert("Task 3", 2);
			priorityQueue.insert("Task 4", 5);
		});

		Thread consumerThread = new Thread(() -> {
			while (priorityQueue.size() > 0) {
				String task = priorityQueue.extract();
				System.out.println("Processed: " + task);
			}
		});

		producerThread1.start();
		producerThread2.start();
		consumerThread.start();

		try {
			producerThread1.join();
			producerThread2.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
