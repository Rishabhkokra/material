package com.grab.test;

import java.util.Stack;

public class FIFOUsingStacks {

	private static Stack stack1 = new Stack();
	private static Stack stack2 = new Stack();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		push(Integer.valueOf(1));
		push(Integer.valueOf(10));
		push(Integer.valueOf(11));
		push(Integer.valueOf(12));

		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
	}

	private static Integer pop() {
		stack2.clear();
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		Integer result = (Integer) stack2.pop();

		// re-filling stack1
		for (int i = stack2.size() - 1; i >= 0; i--) {
			stack1.push(stack2.get(i));
		}

		return result;
	}

	private static void push(Integer input) {
		stack1.push(input);

	}
}
