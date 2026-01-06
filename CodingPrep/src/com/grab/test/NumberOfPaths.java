package com.grab.test;

public class NumberOfPaths {
    
    /**
     * Method 1: Dynamic Programming Approach
     * Time: O(n²), Space: O(n²)
     */
    public static int numOfPathsToDest(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        // dp[i][j] = number of ways to reach position (i,j)
        int[][] dp = new int[n][n];
        
        // Base case: one way to reach starting position
        dp[0][0] = 1;
        
        // Fill the DP table
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i && j < n; j++) { // Constraint: j <= i
                if (i == 0 && j == 0) continue;
                
                // Can come from left (i-1, j)
                if (i > 0) {
                    dp[i][j] += dp[i-1][j];
                }
                
                // Can come from below (i, j-1)
                if (j > 0) {
                    dp[i][j] += dp[i][j-1];
                }
            }
        }
        
        return dp[n-1][n-1];
    }
    
    /**
     * Method 2: Space Optimized DP
     * Time: O(n²), Space: O(n)
     */
    public static int numOfPathsToDestOptimized(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        // Only need current and previous row
        int[] prev = new int[n];
        int[] curr = new int[n];
        
        prev[0] = 1; // Base case
        
        for (int i = 1; i < n; i++) {
            curr[0] = prev[0]; // First column always 1
            
            for (int j = 1; j <= i && j < n; j++) { // j <= i constraint
                curr[j] = curr[j-1] + prev[j];
            }
            
            // Swap arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
            
            // Clear curr for next iteration
            for (int k = 0; k < n; k++) {
                curr[k] = 0;
            }
        }
        
        return prev[n-1];
    }
    
    /**
     * Method 3: Mathematical Solution using Catalan Numbers
     * This problem gives us the (n-1)th Catalan number
     * C(k) = (2k)! / ((k+1)! * k!) = (2k choose k) / (k+1)
     * Time: O(n), Space: O(1)
     */
    public static int numOfPathsToDestCatalan(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        // Calculate (n-1)th Catalan number
        // Using the formula: C(n) = (2n choose n) / (n+1)
        return catalanNumber(n - 1);
    }
    
    /**
     * Helper method to calculate kth Catalan number
     * C(k) = (2k)! / ((k+1)! * k!)
     */
    private static int catalanNumber(int k) {
        if (k <= 1) return 1;
        
        // Calculate C(k) = (2k choose k) / (k+1)
        long result = 1;
        
        // Calculate (2k choose k) = (2k)! / (k! * k!)
        for (int i = 0; i < k; i++) {
            result = result * (2 * k - i) / (i + 1);
        }
        
        return (int)(result / (k + 1));
    }
    
    /**
     * Alternative Catalan calculation to avoid overflow issues
     */
    public static int numOfPathsToDestCatalanSafe(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        int k = n - 1;
        long[] dp = new long[k + 1];
        dp[0] = dp[1] = 1;
        
        // C(i) = sum(C(j) * C(i-1-j)) for j from 0 to i-1
        for (int i = 2; i <= k; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        
        return (int)dp[k];
    }
    
    /**
     * Method to verify paths by generating all valid sequences
     * (For testing/debugging purposes only - exponential time)
     */
    public static void printAllValidPaths(int n) {
        if (n <= 0) return;
        
        System.out.println("All valid paths for n = " + n + ":");
        generatePaths("", 0, 0, n - 1, n - 1, 1);
    }
    
    private static void generatePaths(String path, int x, int y, int targetX, int targetY, int pathNum) {
        if (x == targetX && y == targetY) {
            System.out.println(pathNum + ". " + path);
            return;
        }
        
        if (x > targetX || y > targetY || x < y) { // x < y violates constraint
            return;
        }
        
        // Try going East (right)
        if (x < targetX) {
            generatePaths(path + "E", x + 1, y, targetX, targetY, pathNum);
        }
        
        // Try going North (up)
        if (y < targetY) {
            generatePaths(path + "N", x, y + 1, targetX, targetY, pathNum);
        }
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("Testing Path Counting Solutions:");
        System.out.println("n  | DP | Optimized | Catalan | Expected");
        System.out.println("---|----|-----------|---------|---------");
        
        int[] testCases = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 1, 2, 5, 14, 42}; // Catalan numbers: C(0), C(1), C(2), C(3), C(4), C(5)
        
        for (int i = 0; i < testCases.length; i++) {
            int n = testCases[i];
            int dpResult = numOfPathsToDest(n);
            int optResult = numOfPathsToDestOptimized(n);
            int catResult = numOfPathsToDestCatalan(n);
            
            System.out.printf("%2d | %2d | %9d | %7d | %8d%n", 
                             n, dpResult, optResult, catResult, expected[i]);
        }
        
        // Show the specific example for n=4
        System.out.println("\nFor n=4, the valid paths are:");
        String[] paths = {"EEENNN", "EENENN", "ENEENN", "ENENEN", "EENNEN"};
        for (int i = 0; i < paths.length; i++) {
            System.out.println((i+1) + ". " + paths[i]);
        }
        
        // Verify constraint for each path
        System.out.println("\nVerifying constraint i >= j for each path:");
        for (String path : paths) {
            System.out.println(path + " -> " + isValidPath(path));
        }
    }
    
    // Helper method to verify a path satisfies the constraint
    private static boolean isValidPath(String path) {
        int x = 0, y = 0;
        for (char c : path.toCharArray()) {
            if (c == 'E') x++;
            else if (c == 'N') y++;
            
            if (x < y) return false; // Violates constraint
        }
        return true;
    }
}