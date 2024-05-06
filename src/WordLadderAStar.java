package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordLadderAStar {


    static class Node {
        String word;
        int f;
        int g; 
        Node parent;
        Node(String word, int g, int h, Node parent) {
            this.word = word;
            this.g = g;
            this.f = g + h;
            this.parent = parent;
        }
    }

   
    static int hammingDistance(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    static List<String> findPath(String start, String end, Set<String> wordList) {
    
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        Set<String> closedList = new HashSet<>();
        Map<String, Node> parentMap = new HashMap<>();
        Node startNode = new Node(start, 0, hammingDistance(start, end), null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closedList.add(current.word);
            if (current.word.equals(end)) {
                List<String> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.word);
                    current = current.parent;
                }
                return path;
            }

            for (String neighbor : wordList) {
                if (!closedList.contains(neighbor) && hammingDistance(current.word, neighbor) == 1) {
                    int g = current.g + 1;
                    int h = hammingDistance(neighbor, end);
                    Node newNode = new Node(neighbor, g, h, current);

                    if (!openList.contains(newNode) || g < newNode.g) {
                        openList.add(newNode);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }
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
