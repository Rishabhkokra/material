package com.dsa.practise;

public class ReverseAnArrayUsingRecursn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		reverse(new int[]{1,2,3,4,5,6,7}, 6);
	}
	
	private static void reverse(int[] array, int n) {

		if (n < 0)
			return;
		
		System.out.print(array[n]);
		reverse(array, n-1);

		

	}

}
