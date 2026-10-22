package com.example.practicalfive;

public class TextProcessor {

    public String processMunicipality(String municipality) {

        // Check for null or empty input
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        // Remove spaces at the beginning and end
        String input = municipality.trim();

        // Check municipality names (case-insensitive)
        if (input.equalsIgnoreCase("Beijing")) {
            return "Beijing";
        }

        if (input.equalsIgnoreCase("Shanghai")) {
            return "Shanghai";
        }

        if (input.equalsIgnoreCase("Chongqing")) {
            return "Chongqing";
        }

        if (input.equalsIgnoreCase("Tianjin")) {
            return "Tianjin";
        }

        // Unsupported municipality
        return null;
    }
}