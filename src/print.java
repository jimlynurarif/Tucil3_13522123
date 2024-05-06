package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class print {

    // Function to read word list from file and filter words of same length as start
    static Set<String> readWordListFromFile(String filePath, String start) {
        Set<String> wordList = new HashSet<>();
        int startLength = start.length();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == startLength) {
                    wordList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    // Main method
    public static void main(String[] args) {
        String start = "hit";
        String filePath = "C:/Users/Jimly/Documents/stima3/word.txt";
        Set<String> wordList = readWordListFromFile(filePath, start);
        System.out.println("Word list: " + wordList);
    }
}
