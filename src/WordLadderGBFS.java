package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLadderGBFS {
    
    private static int hammingDistance(String word1, String word2) {
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
    
    private static List<String> greedyBestFirstSearch(String start, String end, Set<String> wordList) {
        List<String> ladder = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
        Set<String> visited = new HashSet<>();
        
        queue.offer(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentWord = current.word;
            
            if (currentWord.equals(end)) {
                // ladder.add(end);
                while (current != null) {
                    ladder.add(0, current.word);
                    current = current.parent;
                }
                return ladder;
            }
            
            visited.add(currentWord);
            
            for (String word : wordList) {
                if (!visited.contains(word) && hammingDistance(word, currentWord) == 1) {
                    queue.offer(new Node(word, hammingDistance(word, end), current));
                }
            }
        }
        
        return ladder;
    }
    
    static class Node {
        String word;
        int distance;
        Node parent;
        
        Node(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
        
        Node(String word, int distance, Node parent) {
            this.word = word;
            this.distance = distance;
            this.parent = parent;
        }
    }

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
    
    public static void main(String[] args) {

        String start = "frown";
        String end = "smile";

        String filePath = "word.txt";
        Set<String> wordList = readWordListFromFile(filePath, start);
        
        List<String> ladder = greedyBestFirstSearch(start, end, wordList);
        
        if (!ladder.isEmpty()) {
            System.out.println("Word ladder from " + start + " to " + end + ":");
            for (String word : ladder) {
                System.out.println(word);
            }
        } else {
            System.out.println("No word ladder found from " + start + " to " + end + ".");
        }
    }
}
