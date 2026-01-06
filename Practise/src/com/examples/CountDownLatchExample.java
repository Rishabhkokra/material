package com.examples;

import java.util.concurrent.CountDownLatch;

//The CountDownLatch class is another important class for concurrent execution.
//It is a synchronization aid that allows one or more than one thread
//to wait until a set of operations being performed in another thread is completed.


// create CountDownLatchExample class to understand how we can use it.  
public class CountDownLatchExample {
	// main() method start
	public static void main(String args[]) throws InterruptedException // latch can throw InterruptedException
	{
		// Now, we create a task which will wait for 4 threads before starting
		CountDownLatch CDLatch = new CountDownLatch(4);

		// Now, we create all 4 threads and starts them
		Worker thread1 = new Worker(1000, CDLatch, "Worker Thread-1");
		Worker thread2 = new Worker(2000, CDLatch, "Worker Thread-2");
		Worker thread3 = new Worker(3000, CDLatch, "Worker Thread-3");
		Worker thread4 = new Worker(4000, CDLatch, "Worker Thread-4");

		// start all 4 threads
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		// Use await() method by which the main task waits for 4 threads
		CDLatch.await();
		

		// The main thread has started
		System.out.println("Thread " + Thread.currentThread().getName() + " has finished");
	}
}

// create WorkerThread class for representing thread and for which main thread will wait.  
class Worker extends Thread {
	// declare delay and CDLatch variables
	private int delay;
	private CountDownLatch CDLatch;

	// parameterized constructor to initialize values to the variables
	public Worker(int delay, CountDownLatch CDLatch, String name) {
		super(name);
		this.delay = delay;
		this.CDLatch = CDLatch;
	}

	// override run() method of the Thread class to execute thread
	@Override
	public void run() {
		// use try block to execute thread and get count value
		try {
			Thread.sleep(delay);
			CDLatch.countDown(); // use countDown() method of the CountDownLatch
			System.out.println(Thread.currentThread().getName() + " has finished");
		}

		// use catch block to handle InterruptedException
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}