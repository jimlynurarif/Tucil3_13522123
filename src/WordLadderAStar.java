package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLadderAStar {

    // Class representing a node in the Word ladder graph
    static class Node {
        String word;
        int f; // f = g + h, where g is the cost so far and h is the heuristic
        int g; // cost so far
        Node parent; // parent node

        Node(String word, int g, int h, Node parent) {
            this.word = word;
            this.g = g;
            this.f = g + h;
            this.parent = parent;
        }
    }

    // Function to calculate Hamming distance between two words
    static int hammingDistance(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    // A* algorithm to find the shortest path between start and end words
    static List<String> findPath(String start, String end, Set<String> wordList) {
        // Priority queue to store nodes with lowest f value on top
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        // Set to store visited nodes
        Set<String> closedList = new HashSet<>();
        // Map to store parent node for each word
        Map<String, Node> parentMap = new HashMap<>();

        // Initialize start node
        Node startNode = new Node(start, 0, hammingDistance(start, end), null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            // Get node with lowest f value from open list
            Node current = openList.poll();
            closedList.add(current.word);

            // If current node is the end word, reconstruct and return path
            if (current.word.equals(end)) {
                List<String> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.word);
                    current = current.parent;
                }
                return path;
            }

            // Generate all neighbors of current word
            for (String neighbor : wordList) {
                if (!closedList.contains(neighbor) && hammingDistance(current.word, neighbor) == 1) {
                    int g = current.g + 1;
                    int h = hammingDistance(neighbor, end);
                    Node newNode = new Node(neighbor, g, h, current);

                    // If neighbor is not in open list or new path is better than previous path, add it to open list
                    if (!openList.contains(newNode) || g < newNode.g) {
                        openList.add(newNode);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }

        // No path found
        return null;
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

        List<String> path = findPath(start, end, wordList);
        if (path != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + path);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }
}
