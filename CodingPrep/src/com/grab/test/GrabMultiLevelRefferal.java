package com.grab.test;
import java.util.*;

class ReferralGraph {
    private Map<String, List<String>> referrals = new HashMap<>();
    private Set<String> visited = new HashSet<>();

    // Initialize the graph with referral relationships
    public void initializeGraph(Map<String, List<String>> referralMap) {
        this.referrals.clear();
        this.referrals.putAll(referralMap);
    }

    // Calculate total bonus for a user
    public int calculateTotalBonus(String userId) {
        visited.clear();
        return dfs(userId, 1); // Start at level 1
    }

    // Depth-first search to traverse referral tree
    private int dfs(String userId, int level) {
        if (!referrals.containsKey(userId) || visited.contains(userId)) {
            return 0;
        }

        visited.add(userId);
        int totalBonus = 0;

        // Get referred users and calculate bonuses
        for (String referredUser : referrals.get(userId)) {
            // Add bonus for current level
            totalBonus += getBonusForLevel(level);
            
            // Recursively add bonuses for next level
            totalBonus += dfs(referredUser, level + 1);
        }

        return totalBonus;
    }

    // Get bonus amount based on referral level
    private int getBonusForLevel(int level) {
        switch (level) {
            case 1: return 5; // Direct referral
            case 2: return 3; // Indirect referral
            default: return 0; // Beyond supported levels
        }
    }
}

public class GrabMultiLevelRefferal {
    public static void main(String[] args) {
        // Create sample referral data
        Map<String, List<String>> referralData = new HashMap<>();
        referralData.put("john", Arrays.asList("jim", "alex"));
        referralData.put("jim", Collections.singletonList("bob"));
        referralData.put("alex", Arrays.asList("adam", "alice"));

        ReferralGraph graph = new ReferralGraph();
        graph.initializeGraph(referralData);

        // Test cases
        System.out.println("John's total bonus: $" + graph.calculateTotalBonus("john")); // Should be 19$
        System.out.println("Jim's total bonus: $" + graph.calculateTotalBonus("jim"));   // Should be 5$
        System.out.println("Alex's total bonus: $" + graph.calculateTotalBonus("alex")); // Should be 10$
    }
}