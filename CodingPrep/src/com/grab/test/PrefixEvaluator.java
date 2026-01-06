package com.grab.test;
import org.json.JSONArray;

public class PrefixEvaluator {
    /**
     * Evaluates a prefix notation expression encoded as a JSON array.
     * @param jsonStr The JSON string containing the prefix expression
     * @return The integer result of the evaluation
     */
    public static int evaluatePrefix(String jsonStr) {
        JSONArray arr = new JSONArray(jsonStr);
        return evaluate(arr);
    }

    private static int evaluate(JSONArray arr) {
        String operator = arr.getString(0);
        
        switch (operator) {
            case "+":
                return evaluate(arr.getJSONArray(1)) + evaluate(arr.getJSONArray(2));
            case "-":
                return evaluate(arr.getJSONArray(1)) - evaluate(arr.getJSONArray(2));
            case "*":
                return evaluate(arr.getJSONArray(1)) * evaluate(arr.getJSONArray(2));
            case "/":
                return (int)(evaluate(arr.getJSONArray(1)) / evaluate(arr.getJSONArray(2)));
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public static void main(String[] args) {
        // Test case 1: Simple addition
        String expr1 = "[\"+\", 2, 3]";
        System.out.println("Test 1: " + expr1);
        System.out.println("Result: " + evaluatePrefix(expr1));

        // Test case 2: Complex expression
        String expr2 = "[\"*\", [\"-\", [\"/\", 39, 3], [\"+\", 3, 4]], 8]";
        System.out.println("\nTest 2: " + expr2);
        System.out.println("Result: " + evaluatePrefix(expr2));
    }
}