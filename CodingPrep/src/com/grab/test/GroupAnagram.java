package com.grab.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] input = { "apple", "rope", "cat", "tac", "atc", "pore" };
		// option 1
		findAnagram(input);

		// option 2
		List<List<String>> result = groupAnagrams(input);

		for (List<String> group : result) {
			System.out.print(group + ",");
		}
	}

	private static void findAnagram(String[] input) {

		Map<String, List<String>> ans = new HashMap<>();
		int j = 1;
		for (int i = 0; i < input.length; i++) {
			char[] chars = input[i].toCharArray();
			Arrays.sort(chars);
			j = 1;
			ans.put(input[i], new ArrayList<>());
			while (j < input.length) {
				char[] nextChars = input[j].toCharArray();
				Arrays.sort(nextChars);

				if (String.valueOf(chars).equalsIgnoreCase(String.valueOf(nextChars))) {
					ans.get(input[i]).add(String.valueOf(input[j]));
				}
				j++;
			}

		}

		System.out.println(ans);
	}

	private static final int MAX_CHAR = 26;

	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> groups = new HashMap<>();

		for (String str : strs) {
			String key = getFrequencyKey(str);
			groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
		}

		return new ArrayList<>(groups.values());
	}

	private static String getFrequencyKey(String str) {
		int[] freq = new int[MAX_CHAR];
		for (char c : str.toLowerCase().toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				freq[c - 'a']++;
			}
		}

		StringBuilder key = new StringBuilder();
		for (int count : freq) {
			key.append(count).append('#');
		}
		return key.toString();
	}

}
