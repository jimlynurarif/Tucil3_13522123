package src;
import java.util.*;

public class WordLadderGBFS {

    private List<String> ladder;
    private int visitedNodes;

    public WordLadderGBFS(){
        this.ladder = new ArrayList<>();
        this.visitedNodes = 0;
    }

    public List<String> getLadder() {
        return ladder;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }

    public void setLadder(List<String> ladder) {
        this.ladder = ladder;
    }

    public void addVisitedNodes() {
        this.visitedNodes++;
    }

    static class Node {
        String word;
        int cost;
        Node parent;
        
        Node(String word, int cost) {
            this.word = word;
            this.cost = cost;
        }
        
        Node(String word, int cost, Node parent) {
            this.word = word;
            this.cost = cost;
            this.parent = parent;
        }
    }
    
    static WordLadderGBFS GBFS(String start, String end, Set<String> wordList) {

        WordLadderGBFS wordLadderGBFS = new WordLadderGBFS();
        List<String> ladder = new ArrayList<>();
        wordLadderGBFS.setLadder(ladder);
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        Set<String> visited = new HashSet<>();
        
        queue.offer(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentWord = current.word;
            wordLadderGBFS.addVisitedNodes();
            
            if (currentWord.equals(end)) {
                // ladder.add(end);
                while (current != null) {
                    ladder.add(0, current.word);
                    wordLadderGBFS.setLadder(ladder);
                    current = current.parent;
                }
                return wordLadderGBFS;
            }
            
            visited.add(currentWord);
            
            for (String word : wordList) {
                if (!visited.contains(word) && hamming.hammingDistance(word, currentWord) == 1) {
                    queue.offer(new Node(word, hamming.hammingDistance(word, end), current));
                }
            }
        }
        
        return wordLadderGBFS;
    }
}
