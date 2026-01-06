package com.examples;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class CoffeeShop {

	private Semaphore coffeMachine = new Semaphore(1);
	private Semaphore waiter = new Semaphore(0);
	int count = 1;

	private Queue<String> queue = new LinkedList<>();

	public static void main(String[] s) {
//		CoffeeMachine coffeeMachine = new CoffeeMachine();
//		Waiter waiter = new Waiter();
//		coffeeMachine.start();
//		waiter.start();

		CoffeeShop shop = new CoffeeShop();
		CoffeMachineInner cofferInner = shop.new CoffeMachineInner();
		WaiterInner waiterInner = shop.new WaiterInner();
		Thread coffeMachineT = new Thread(cofferInner);
		Thread waiterT = new Thread(waiterInner);
		coffeMachineT.start();
		waiterT.start();
	}

	private class CoffeMachineInner implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					coffeMachine.acquire();
					Thread.sleep(500);
					System.out.println("prepared coffee no." + count);
					queue.offer("coffee no." + count);
					count++;
					waiter.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private class WaiterInner implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					waiter.acquire();
					Thread.sleep(600);
					System.out.println("serving coffee no." + queue.poll());
					Thread.sleep(1000);
					coffeMachine.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}