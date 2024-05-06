package src;
import java.util.*;

public class WordLadderGBFS {
    
    static List<String> greedyBestFirstSearch(String start, String end, Set<String> wordList) {
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
                if (!visited.contains(word) && hamming.hammingDistance(word, currentWord) == 1) {
                    queue.offer(new Node(word, hamming.hammingDistance(word, end), current));
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
}
