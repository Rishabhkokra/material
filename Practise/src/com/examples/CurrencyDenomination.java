package com.examples;

// Java program to accept an amount
// and count number of notes
import java.util.*;
import java.lang.*;

public class CurrencyDenomination {

	// function to count and
	// print currency notes
	public static void countCurrency(int amount) {
		int[] notes = new int[] { 2000, 500, 200, 100, 50, 20, 10, 5, 1 };
		int[] noteCounter = new int[9];

		// count notes using Greedy approach
		for (int i = 0; i < notes.length; i++) {
			if (amount >= notes[i]) {
				noteCounter[i] = amount / notes[i];
				amount = amount % notes[i];
			}
		}

		// Print notes
		System.out.println("Currency Count ->");
		for (int i = 0; i < 9; i++) {
			if (noteCounter[i] != 0) {
				System.out.println(notes[i] + " : " + noteCounter[i]);
			}
		}
	}

	// driver function
	public static void main(String argc[]) {
		int amount = 868;
		countCurrency(amount);
	}

	/* This code is contributed by Sagar Shukla */
}
