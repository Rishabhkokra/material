package com.examples;
// Java implementation of a producer and consumer 

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

// that use semaphores to control synchronization. 

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

//Driver class 
public class ProducerConsumerProblemMain {
	public static void main(String args[]) {
		// creating buffer queue
		ProducerConsumerProblem q = new ProducerConsumerProblem();

		// starting consumer thread
		Thread consumer = new Thread(new Consumer(q));

		// starting producer thread
		Thread producer = new Thread(new Producer(q));
		consumer.start();
		producer.start();

	}
}

class ProducerConsumerProblem {
	// an item
	long item;

	// semCon initialized with 0 permits
	// to ensure put() executes first
	static Semaphore semCon = new Semaphore(0);

	static Semaphore semProd = new Semaphore(1);

	ReentrantLock lock = new ReentrantLock();
	Queue<Integer> queue = new ArrayDeque<>();

	public long getItem() {
		return item;
	}

	public void setItem(long item) {
		this.item = item;
	}

	public static Semaphore getSemCon() {
		return semCon;
	}

	public static void setSemCon(Semaphore semCon) {
		ProducerConsumerProblem.semCon = semCon;
	}

	public static Semaphore getSemProd() {
		return semProd;
	}

	public static void setSemProd(Semaphore semProd) {
		ProducerConsumerProblem.semProd = semProd;
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public Queue<Integer> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Integer> queue) {
		this.queue = queue;
	}

}


//
//// Producer class 
class Producer implements Runnable {
	ProducerConsumerProblem q;

	Producer(ProducerConsumerProblem q) {
		this.q = q;
	}

	public void run() {
		while (true) {
			try {
				Random rand = new Random();
				q.getSemProd().acquire();
				q.getLock().lock();
				int item = rand.nextInt(100);
				System.out.println("produced item: " + item);
				q.getQueue().offer(item);
				q.getLock().unlock();
				q.getSemCon().release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

//
// Consumer class 
class Consumer implements Runnable {
	ProducerConsumerProblem q;

	Consumer(ProducerConsumerProblem q) {
		this.q = q;
	}

	public void run() {
		while (true) {
			try {
				q.getSemCon().acquire();
				q.getLock().lock();
				System.out.println("consumed item: " + q.getQueue().poll());
				q.getLock().unlock();
				q.getSemProd().release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
