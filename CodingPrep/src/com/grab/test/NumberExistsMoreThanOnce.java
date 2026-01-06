package com.grab.test;

public class NumberExistsMoreThanOnce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = { 1, 5, 3, 4, 5, 3 };

		for (int i = 0; i < array.length; i++) {
			int index=Math.abs(array[i]);
			
			if (array[index] < 0) {
				System.out.println("Duplicate exists- "+Math.abs(array[i]));
				break;
			}
			array[index] = -array[index];
		}

	}

}
