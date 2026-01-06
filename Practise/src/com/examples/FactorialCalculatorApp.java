package com.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FactorialCalculator implements Callable<Long> {
	private int number;

	public FactorialCalculator(int number) {
		this.number = number;
	}

	@Override
	public Long call() {
		long result = 1;
		for (int i = 1; i <= number; i++) {
			result *= i;
		}
		return result;
	}
}

public class FactorialCalculatorApp {
	public static void main(String[] args) {
		int[] numbers = { 5, 7, 10 };

		ExecutorService executorService = Executors.newFixedThreadPool(numbers.length);
		// List to store Future objects representing the results of factorial
		// calculations
		List<Future<Long>> futures = new ArrayList<>();

		for (int num : numbers) {
			FactorialCalculator calculator = new FactorialCalculator(num);
			// Submitting tasks to the executor and storing the Future object in the list
			Future<Long> future = executorService.submit(calculator);
			futures.add(future);
		}

		// Retrieving results using the get method of Future
		for (int i = 0; i < numbers.length; i++) {
			try {
				int num = numbers[i];
				long result = futures.get(i).get();
				System.out.println("Factorial of " + num + " is: " + result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		// Shutting down the executor service
		executorService.shutdown();
	}
}
