package com.grab.test;

import java.util.*;

public class DomainAnalytics {
    
    /**
     * Optimal Solution: O(N) time complexity where N is total characters in all domains
     * Space: O(K) where K is number of unique domains generated
     */
    public static List<String> analyzeDomains(String[] input) {
        // LinkedHashMap to maintain insertion order for original domains
        Map<String, Integer> domainHits = new LinkedHashMap<>();
        // Set to track original domains to maintain their order
        Set<String> originalDomains = new LinkedHashSet<>();
        
        // Process each input entry
        for (String entry : input) {
            String[] parts = entry.split(" ", 2);
            int hits = Integer.parseInt(parts[0]);
            String domain = parts[1];
            
            // Track original domain
            originalDomains.add(domain);
            
            // Add hits to current domain
            domainHits.put(domain, domainHits.getOrDefault(domain, 0) + hits);
            
            // Generate and update parent domains
            generateParentDomains(domain, hits, domainHits);
        }
        
        List<String> result = new ArrayList<>();
        
        // First, add all original domains in order they appeared
        for (String domain : originalDomains) {
            result.add(domainHits.get(domain) + " " + domain);
        }
        
        // Then add parent domains that weren't in original input
        for (Map.Entry<String, Integer> entry : domainHits.entrySet()) {
            String domain = entry.getKey();
            if (!originalDomains.contains(domain)) {
                result.add(entry.getValue() + " " + domain);
            }
        }
        
        return result;
    }
    
    /**
     * Generate all parent domains for a given domain
     * E.g., "insurance.grab.com" -> ["grab.com", "com"]
     */
    private static void generateParentDomains(String domain, int hits, Map<String, Integer> domainHits) {
        int dotIndex = domain.indexOf('.');
        
        while (dotIndex != -1) {
            // Get parent domain (everything after first dot)
            String parentDomain = domain.substring(dotIndex + 1);
            
            // Add hits to parent domain
            domainHits.put(parentDomain, domainHits.getOrDefault(parentDomain, 0) + hits);
            
            // Move to next level
            domain = parentDomain;
            dotIndex = domain.indexOf('.');
        }
    }
    
    /**
     * Alternative implementation with cleaner separation of concerns
     */
    public static List<String> analyzeDomainsV2(String[] input) {
        Map<String, Integer> allDomains = new HashMap<>();
        List<String> originalDomains = new ArrayList<>();
        
        // Process input and collect all domain hits
        for (String entry : input) {
            ProcessedEntry processed = parseEntry(entry);
            originalDomains.add(processed.domain);
            
            // Add hits for current domain and all its parents
            addHitsForDomainHierarchy(processed.domain, processed.hits, allDomains);
        }
        
        return buildResult(originalDomains, allDomains);
    }
    
    private static class ProcessedEntry {
        final int hits;
        final String domain;
        
        ProcessedEntry(int hits, String domain) {
            this.hits = hits;
            this.domain = domain;
        }
    }
    
    private static ProcessedEntry parseEntry(String entry) {
        int spaceIndex = entry.indexOf(' ');
        int hits = Integer.parseInt(entry.substring(0, spaceIndex));
        String domain = entry.substring(spaceIndex + 1);
        return new ProcessedEntry(hits, domain);
    }
    
    private static void addHitsForDomainHierarchy(String domain, int hits, Map<String, Integer> domainHits) {
        String currentDomain = domain;
        
        // Add hits to current domain and all parent domains
        while (currentDomain != null && !currentDomain.isEmpty()) {
            domainHits.put(currentDomain, domainHits.getOrDefault(currentDomain, 0) + hits);
            
            // Get next parent domain
            int dotIndex = currentDomain.indexOf('.');
            if (dotIndex == -1) break;
            
            currentDomain = currentDomain.substring(dotIndex + 1);
        }
    }
    
    private static List<String> buildResult(List<String> originalDomains, Map<String, Integer> allDomains) {
        List<String> result = new ArrayList<>();
        Set<String> processed = new HashSet<>();
        
        // Add original domains first (maintaining order)
        for (String domain : originalDomains) {
            if (!processed.contains(domain)) {
                result.add(allDomains.get(domain) + " " + domain);
                processed.add(domain);
            }
        }
        
        // Add parent domains in consistent order (lexicographical by domain length, then alphabetically)
        List<String> parentDomains = new ArrayList<>();
        for (String domain : allDomains.keySet()) {
            if (!processed.contains(domain)) {
                parentDomains.add(domain);
            }
        }
        
        // Sort parent domains for consistent output
        parentDomains.sort((a, b) -> {
            int levelA = countDots(a);
            int levelB = countDots(b);
            if (levelA != levelB) {
                return Integer.compare(levelB, levelA); // Higher level domains first
            }
            return a.compareTo(b); // Alphabetical for same level
        });
        
        for (String domain : parentDomains) {
            result.add(allDomains.get(domain) + " " + domain);
        }
        
        return result;
    }
    
    private static int countDots(String domain) {
        int count = 0;
        for (char c : domain.toCharArray()) {
            if (c == '.') count++;
        }
        return count;
    }
    
    /**
     * Test method with provided examples
     */
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("Test Case 1:");
        String[] input1 = {"5003 insurance.grab.com"};
        List<String> result1 = analyzeDomains(input1);
        System.out.println("Input: " + Arrays.toString(input1));
        System.out.println("Output: " + result1);
        System.out.println("Expected: [5003 insurance.grab.com, 5003 grab.com]");
        System.out.println();
        
        // Test Case 2
        System.out.println("Test Case 2:");
        String[] input2 = {"500 insurance.grab.com", "50 grab.com", "10 mail.google.com", "5 mail.grab.com", "5 wikipedia.org"};
        List<String> result2 = analyzeDomains(input2);
        System.out.println("Input: " + Arrays.toString(input2));
        System.out.println("Output: " + result2);
        System.out.println("Expected: [500 insurance.grab.com, 5 mail.grab.com, 555 grab.com, 10 mail.google.com, 5 wikipedia.org, 10 google.com]");
        System.out.println();
        
        // Additional test cases
        System.out.println("Additional Test Cases:");
        
        // Test Case 3: Multiple levels
        String[] input3 = {"100 a.b.c.d.com", "50 b.c.d.com"};
        List<String> result3 = analyzeDomains(input3);
        System.out.println("Input: " + Arrays.toString(input3));
        System.out.println("Output: " + result3);
        System.out.println();
        
        // Test Case 4: Single domain entries
        String[] input4 = {"200 com", "100 org"};
        List<String> result4 = analyzeDomains(input4);
        System.out.println("Input: " + Arrays.toString(input4));
        System.out.println("Output: " + result4);
        System.out.println();
        
        // Performance test
        testPerformance();
    }
    
    /**
     * Performance testing method
     */
    private static void testPerformance() {
        System.out.println("Performance Test:");
        
        // Generate large test data
        String[] largeInput = new String[10000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = (i + 1) + " subdomain" + i + ".example" + (i % 100) + ".com";
        }
        
        long startTime = System.currentTimeMillis();
        List<String> result = analyzeDomains(largeInput);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Processed " + largeInput.length + " entries in " + (endTime - startTime) + "ms");
        System.out.println("Generated " + result.size() + " total domain entries");
        
        // Verify time complexity is indeed O(N)
        System.out.println("Average time per entry: " + ((endTime - startTime) / (double) largeInput.length) + "ms");
    }
}