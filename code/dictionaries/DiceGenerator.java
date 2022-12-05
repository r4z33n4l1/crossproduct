package dictionaries;
import dictionaries.LetterFrequency;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DiceGenerator {
    //create a list of strings from a given txt file, and then create a list of strings from the list of strings
    // use letter frequency to create a list of strings

    //the initializer will take in a txt file and create a list of strings from it

    public DiceGenerator(String fileName, int dices) {
        //create a list of strings from the txt file
        //use letter frequency to create a list of strings

        //get the file with file name
        //create a list of strings from the file

        File file = new File(fileName);
        //create a list of strings from the file
        // iterate through the file and add each line to the list of strings
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                words.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        //use letter frequency to create a list of strings
        LetterFrequency letterFrequency = new LetterFrequency(words, dices);
        List<String> strings = letterFrequency.generateStrings();
        //print the list of strings
        for (String string : strings) {
            System.out.println(string);
        }
    }

    // main function which reads animals.txt and prints out the list of strings
    public static void main(String[] args) {
        DiceGenerator diceGenerator = new DiceGenerator("dictionaries\\animals.txt",2);
    }



}
