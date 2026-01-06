package test;

import java.util.*;

class MaxPlatformRequiredForTrains {

	static int findPlatform(int arr[], int dep[], int n) {
		
		Arrays.sort(arr);
		Arrays.sort(dep);
		System.out.print("Arrivals: [");
		print(arr);
		System.out.print("]");
		System.out.println("\n");

		System.out.print("Departure: [");
		print(dep);
		System.out.print("]");

		System.out.println("\n");

		int plat_needed = 1, result = 1;
		int i = 1, j = 0;

		while (i < n && j < n) {

			if (arr[i] <= dep[j]) {
				plat_needed++ ;
				i++;
			}

			else if (arr[i] > dep[j]) {
				plat_needed--;
				j++;
			}

			if (plat_needed > result)
				result = plat_needed;
		}

		return result;
	}

	private static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1)
				System.out.print(array[i] + " ");
		}
	}

	public static void main(String[] args) {

		int[] arr = { 900, 945, 955, 1100, 1500, 1800 };
		int[] dep = { 920, 1200, 1130, 1150, 1900, 2000 };
		int n = arr.length;
		int totalCount = findPlatform(arr, dep, n);
		System.out.println("Minimum number of Platforms required " + totalCount);
	}
}