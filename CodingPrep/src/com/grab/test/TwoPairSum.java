package com.grab.test;

public class TwoPairSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//wrong impl
		int arr[] = { 0, -1, 2, -3, 1 };
		int target = 3;
		int start = 0, end = arr.length - 1;
		int index = 0;
		System.out.println("started...");
		
		while (start != end) {
			int sum = arr[start] + arr[end];
			if (sum == target) {
				System.out.println("Yes");
				break;
			}
			if (target < sum)
				start++;
			else
				end--;
		}

	}

}
