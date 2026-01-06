package test;

import java.util.HashMap;

public class LongestSubStringWithRepeatChar {

	public static void main(String args[]) {

		String str = "abcabcbb";
//		

		System.out.println(longestSubStringWithRepeatChar(str));

		System.out.println(findUniqeNum(new int[] { 3, 1, 2, 1, 2 }));
	}

	private static int findUniqeNum(int arra[]) {
		int unique = 0;
		for (int i = 0; i < arra.length; i++) {
			unique = unique ^ arra[i];

		}
		return unique;
	}

	static int longestSubStringWithRepeatChar(String s) {
		HashMap<Character, Integer> mpp = new HashMap<Character, Integer>();

		int left = 0, right = 0;
		int n = s.length();
		int len = 0;
		while (right < n) {
			if (mpp.containsKey(s.charAt(right)))
				left = Math.max(mpp.get(s.charAt(right)) + 1, left);

			mpp.put(s.charAt(right), right);

			len = Math.max(len, right - left + 1);
			right++;
		}
		return len;
	}
	
}
