package com.grab.test;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	private class Node {
		String key;
		String value;
		Node prev;
		Node next;

		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	private final int capacity;
	private final Map<String, Node> cache;
	private final Node head;
	private final Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>();
		head = new Node(null, null);
		tail = new Node(null, null);
		head.next = tail;
		tail.prev = head;
	}

	// Remove node from linked list
	private void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	// Add node to front of linked list (mark as recently used)
	private void addToFront(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	public String GET(String key) {
		Node node = cache.get(key);
		if (node == null) {
			return null;
		}

		// Move accessed node to front
		remove(node);
		addToFront(node);
		return node.value;
	}

	public void SET(String key, String value) {
		Node node = cache.get(key);

		if (node != null) {
			// Update existing node
			node.value = value;
			remove(node);
			addToFront(node);
		} else {
			// Create new node
			node = new Node(key, value);
			cache.put(key, node);
			addToFront(node);

			// Check capacity
			if (cache.size() > capacity) {
				// Remove least recently used node (tail prev)
				Node lruNode = tail.prev;
				remove(lruNode);
				cache.remove(lruNode.key);
			}
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);

		// Test 1: SET('a', '1')
		cache.SET("a", "1");
		System.out.println(cache.cache); // {a=1}

		// Test 2: SET('b', '2')
		cache.SET("b", "2");
		System.out.println(cache.cache); // {a=1, b=2}

		// Test 3: SET('c', '3')
		cache.SET("c", "3");
		System.out.println(cache.cache); // {a=1, b=2, c=3}

		// Test 4: GET('a')
		String result = cache.GET("a");
		System.out.println(result); // '1'

		// Test 5: SET('d', '4')
		cache.SET("d", "4");
		System.out.println(cache.cache); // {a=1, c=3, d=4}
	}
}