package test;

import java.util.Stack;

public class NextGreaterElementInArray {

	public static void main(String[] args) {
		int[] array = { 13, 7, 6, 12, 10 };
		int[] arrayAns = { 0, 0, 0, 0, 0 };

		Stack<Integer> stack = new Stack<>();

		stack.push(0);

		for (int i = 1; i < array.length; i++) {

			if (array[i] <= array[stack.peek()]) {
				stack.push(i);
			} else {
				arrayAns[stack.pop()] = array[i];
			}
		}

		for (int i = 0; i < arrayAns.length; i++) {
			System.out.print(arrayAns[i] + " ");
		}

	}

}
