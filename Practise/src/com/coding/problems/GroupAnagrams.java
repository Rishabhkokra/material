package com.coding.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagrams {

	public static void main(String args[]) {
		String array[] = { "eat", "tea", "tan", "ate", "nat", "bat" };
		List<List<String>> rslt = groupAnagrams(array);

		for (List<String> outerList : rslt) {
			System.out.print("[");
			String output="";
			for (String innerList : outerList) {
				output=	String.join(",", output,innerList);
				
			}
			System.out.print(output+"]");
			System.out.println();
		}
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		
		Map<String, List<String>> map = new HashMap<>();

		for (String word : strs) {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String sortedWord = new String(chars);

			if (!map.containsKey(sortedWord)) {
				map.put(sortedWord, new ArrayList<>());
			}

			map.get(sortedWord).add(word);
		}

		return new ArrayList<>(map.values());
	}
}