package com.examples;

import java.util.concurrent.TimeUnit;

enum TrafficLightColor {
	RED, YELLOW, GREEN
}


class TrafficLight implements Runnable {
	private TrafficLightColor color;

	public TrafficLight() {
		this.color = TrafficLightColor.RED;
	}

	public void changeColor(TrafficLightColor newColor) {
		this.color = newColor;
		System.out.println(Thread.currentThread().getName() + " Traffic Light: " + color);
	}

	@Override
	public void run() {
		while (true) {
			switch (color) {
			case RED:
				try {
					TimeUnit.SECONDS.sleep(5); // Red light duration
					changeColor(TrafficLightColor.GREEN);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case YELLOW:
				try {
					TimeUnit.SECONDS.sleep(2); // Yellow light duration
					changeColor(TrafficLightColor.RED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case GREEN:
				try {
					TimeUnit.SECONDS.sleep(5); // Green light duration
					changeColor(TrafficLightColor.YELLOW);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}

public class TrafficLightSimulation {
	public static void main(String[] args) {
		TrafficLight northSouth = new TrafficLight();
		TrafficLight eastWest = new TrafficLight();

		Thread northSouthThread = new Thread(northSouth, "North-South");
		Thread eastWestThread = new Thread(eastWest, "East-West");

		northSouthThread.start();
		eastWestThread.start();
	}
}
