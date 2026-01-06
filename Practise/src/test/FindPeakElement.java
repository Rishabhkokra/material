package test;

public class FindPeakElement {
// wrong use biary search
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 1, 2, 1, 3, 5, 6, 4 };
		int maxPeak = 0, max = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
				maxPeak = i;
			}
		}
		System.out.println(maxPeak);
	}

}
