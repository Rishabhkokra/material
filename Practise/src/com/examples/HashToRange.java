package com.examples;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashToRange {

    public static int getHashInRange(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // or SHA-256
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            int hash = 0;

            // Use the first 4 bytes of the hash to form an int
            for (int i = 0; i < 4; i++) {
                hash = (hash << 8) | (hashBytes[i] & 0xFF);
            }

            // Ensure positive and fit into 1–100
            return Math.abs(hash % 100) + 1;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm not available", e);
        }
    }

    public static void main(String[] args) {
        String key = "myKey";
        int value = getHashInRange(key);
        System.out.println("Hashed value for '" + key + "' is: " + value);
    }
}
