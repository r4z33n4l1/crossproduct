package dictionaries;

import java.util.*;

public class LetterFrequency {
    private final int dices;
    private Map<Character, Integer> letterCounts;

    public LetterFrequency(List<String> words, int dices) {
        // Initialize the letter counts map
        letterCounts = new HashMap<>();
        this.dices = dices;

        // Count the frequency of each letter in the words
        for (String word : words) {
            for (char c : word.toCharArray()) {
                int count = letterCounts.getOrDefault(c, 0);
                letterCounts.put(c, count + 1);
            }
        }
    }

    public List<String> generateStrings() {
        // Create a list of strings to hold the output
        List<String> strings = new ArrayList<>();

        // Generate 25 strings of length 6
        for (int i = 0; i < dices*dices; i++) {
            StringBuilder sb = new StringBuilder();

            // Select the letters for the string based on their frequency
            for (int j = 0; j < 6; j++) {
                char c = selectLetter();
                sb.append(c);
            }

            strings.add(sb.toString());
        }

        return strings;
    }

    private char selectLetter() {
        // Create a list of letters and their corresponding weights
        List<Character> letters = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : letterCounts.entrySet()) {
            letters.add(entry.getKey());
            weights.add(entry.getValue());
        }

        // Use the weights to select a letter from the list
        return selectRandomElement(letters, weights);
    }

    private char selectRandomElement(List<Character> elements, List<Integer> weights) {
        // Calculate the total weight of all elements
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        // Select a random index based on the weights
        int randomIndex = -1;
        double random = Math.random() * totalWeight;
        for (int i = 0; i < elements.size(); i++) {
            random -= weights.get(i);
            if (random <= 0.0d) {
                randomIndex = i;
                break;
            }
        }

        // Return the element at the randomly selected index
        return elements.get(randomIndex);
    }
}