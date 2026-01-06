package com.examples;

import java.util.concurrent.Semaphore;

//Suppose there are two threads t1 and t2. t1 prints Foo and t2 prints Bar. You are required to write a program which takes a user input n. Then the two threads print Foo and Bar alternately n number of times. The code for the class is as follows:
class FooBar {

	private int n;
	private int flag = 0;

	Semaphore fooSem = new Semaphore(1);
	Semaphore barSem = new Semaphore(0);

	public FooBar(int n) {
		this.n = n;
	}

	public void foo() {

		for (int i = 1; i <= n; i++) {
			synchronized (this) {
				while (flag == 1) {
					try {
						this.wait();
					} catch (Exception e) {

					}
				}
				System.out.print("Foo");
				flag = 1;
				this.notifyAll();
			}
		}
	}

	public void foo2() throws InterruptedException {

		for (int i = 1; i <= n; i++) {
			fooSem.acquire();
			System.out.print("Foo2");
			flag = 1;
			barSem.release();
			// this.notifyAll();
		}
	}

	public void bar2() throws InterruptedException {

		for (int i = 1; i <= n; i++) {
			barSem.acquire();
			System.out.println("Bar2");
			flag = 0;
//				this.notifyAll();
			fooSem.release();
		}
	}
	
	public void bar() {

		for (int i = 1; i <= n; i++) {
			synchronized (this) {
				while (flag == 0) {
					try {
						this.wait();
					} catch (Exception e) {

					}
				}
				System.out.println("Bar");
				flag = 0;
				this.notifyAll();
			}
		}
	}

	
}

class FooBarThread extends Thread {

	FooBar fooBar;
	String method;

	public FooBarThread(FooBar fooBar, String method) {
		this.fooBar = fooBar;
		this.method = method;
	}

	public void run() {
		try {
			if ("foo".equals(method)) {
				fooBar.foo2();
			} else if ("bar".equals(method)) {
				fooBar.bar2();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class FoobarMain {

	public static void main(String[] args) {

		FooBar fooBar = new FooBar(5);

		Thread t1 = new FooBarThread(fooBar, "foo");
		Thread t2 = new FooBarThread(fooBar, "bar");

		t2.start();
		t1.start();

	}
}