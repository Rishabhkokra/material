package com.examples.graph;

public class Test {

	public static void main(String[] args) {
		int incr = 29;
		int right = incr % 10;
		int left = incr / 10;
		System.out.println(left + " " + right);

		int array[] = { 4, 3, 2, 9 };

		plusOne(array);

	}

	public static int[] plusOne(int[] arr) {

//		int size = digits.length - 1;
//		int copy[] = new int[digits.length + 3];

		for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] < 9) {
                arr[i]++;
                return arr;
            }
            arr[i] = 0;
        }
        arr = new int[arr.length + 1];
        arr[0] = 1;
        return arr;
    }
}
