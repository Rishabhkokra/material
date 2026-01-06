package test;

import java.util.Stack;
//
//Input: tokens = ["2","1","+","3","*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9

class EvaluateReversePolishNotation {

	public static void main(String[] args) {

		String[] tokens = { "2", "1", "+", "3", "*" };
		System.out.println(evalRPN(tokens));
	}

	public static int evalRPN(String[] tokens) {

		Stack<Integer> st = new Stack<>();

		for (String current : tokens) {

			if ("+".equals(current)) {
				st.push(st.pop() + st.pop());

			} else if ("-".equals(current)) {
				st.push(-(st.pop() - st.pop()));

			} else if ("*".equals(current)) {
				st.push(st.pop() * st.pop());

			} else if ("/".equals(current)) {

				int second = st.pop();
				int first = st.pop();
				st.push(first / second);

			} else {
				st.push(Integer.valueOf(current));
			}
		}
		return st.pop();
	}

}