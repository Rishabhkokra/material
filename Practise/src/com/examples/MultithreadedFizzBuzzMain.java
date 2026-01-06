package com.examples;


//
////FizzBuzz is a common interview problem in which a program prints a number series from 1 to n such that for every number 
//that is a multiple of 3 it prints "fizz", for every number that is a multiple of 5 it prints "buzz" 
//and for every number that is a multiple of both 3 and 5 it prints "fizzbuzz". We will be creating a multi-threaded
//solution for this problem

public class MultithreadedFizzBuzzMain {
	public static void main(String[] args) {
		
		MultithreadedFizzBuzz obj = new MultithreadedFizzBuzz(15);

		Thread t1 = new FizzBuzzThread(obj, "Fizz");
		Thread t2 = new FizzBuzzThread(obj, "Buzz");
		Thread t3 = new FizzBuzzThread(obj, "FizzBuzz");
		Thread t4 = new FizzBuzzThread(obj, "Number");

		t2.start();
		t1.start();
		t4.start();
		t3.start();
	}
}

class MultithreadedFizzBuzz {
	private int n;
	private int num = 1;

	public MultithreadedFizzBuzz(int n) {
		this.n = n;
	}

	public synchronized void fizz() throws InterruptedException {
		while (num <= n) {
			if (num % 3 == 0 && num % 5 != 0) {
				System.out.println("Fizz");
				num++;
				notifyAll();
			} else {
				wait();
			}
		}
	}

	public synchronized void buzz() throws InterruptedException {
		while (num <= n) {
			if (num % 3 != 0 && num % 5 == 0) {
				System.out.println("Buzz");
				num++;
				notifyAll();
			} else {
				wait();
			}
		}
	}

	public synchronized void fizzbuzz() throws InterruptedException {
		while (num <= n) {
			if (num % 15 == 0) {
				System.out.println("FizzBuzz");
				num++;
				notifyAll();
			} else {
				wait();
			}
		}
	}

	public synchronized void number() throws InterruptedException {
		while (num <= n) {
			if (num % 3 != 0 && num % 5 != 0) {
				System.out.println(num);
				num++;
				notifyAll();
			} else {
				wait();
			}
		}
	}
}

class FizzBuzzThread extends Thread {

	MultithreadedFizzBuzz obj;
	String method;

	public FizzBuzzThread(MultithreadedFizzBuzz obj, String method) {
		this.obj = obj;
		this.method = method;
	}

	public void run() {
		if ("Fizz".equals(method)) {
			try {
				obj.fizz();
			} catch (Exception e) {
			}
		} else if ("Buzz".equals(method)) {
			try {
				obj.buzz();
			} catch (Exception e) {
			}
		} else if ("FizzBuzz".equals(method)) {
			try {
				obj.fizzbuzz();
			} catch (Exception e) {
			}
		} else if ("Number".equals(method)) {
			try {
				obj.number();
			} catch (Exception e) {
			}
		}

	}
}
