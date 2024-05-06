package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLadderUCS {
    
    static class Node {
        String word;
        int cost;
        Node parent;
        
        Node(String word, int cost, Node parent) {
            this.word = word;
            this.cost = cost;
            this.parent = parent;
        }
    }
    
    static List<String> findWordLadder(String startWord, String endWord, Set<String> wordList) {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Set<String> visited = new HashSet<>();
        
        queue.offer(new Node(startWord, 0, null));
        visited.add(startWord);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (current.word.equals(endWord)) {
                return constructPath(current);
            }
            
            List<String> neighbors = getNeighbors(current.word, wordList);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    int cost = current.cost + 1; // Assuming uniform cost
                    queue.offer(new Node(neighbor, cost, current));
                    visited.add(neighbor);
                }
            }
        }
        
        return null; // No ladder found
    }
    
    static List<String> getNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (wordList.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            chars[i] = originalChar;
        }
        
        return neighbors;
    }
    
    static List<String> constructPath(Node endNode) {
        List<String> ladder = new ArrayList<>();
        Node current = endNode;
        while (current != null) {
            ladder.add(0, current.word);
            current = current.parent;
        }
        return ladder;
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

    
    
    // public static void main(String[] args) {
    //     String startWord = "frown";
    //     String endWord = "smile";

    //     String filePath = "word.txt";
    //     Set<String> wordList = readWordListFromFile(filePath, startWord);
        
    //     List<String> wordLadder = findWordLadder(startWord, endWord, wordList);
    //     if (wordLadder != null) {
    //         System.out.println("Shortest word ladder:");
    //         for (String word : wordLadder) {
    //             System.out.print(word + " ");
    //         }
    //     } else {
    //         System.out.println("No word ladder found.");
    //     }
    // }
}
