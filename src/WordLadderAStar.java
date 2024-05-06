package src;
import java.util.*;

public class WordLadderAStar {
    private List<String> ladder;
    private int visitedNodes;

    public WordLadderAStar() {
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

    // g adalah biaya yang keluar untuk menggapai node ini
    // h adalah nilai heuristik dari node ini
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

    static WordLadderAStar Astar(String start, String end, Set<String> wordList) {
        WordLadderAStar wordLadderAStar = new WordLadderAStar();
    
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        Set<String> closedList = new HashSet<>();
        Map<String, Node> parentMap = new HashMap<>();
        Node startNode = new Node(start, 0, hamming.hammingDistance(start, end), null);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            wordLadderAStar.addVisitedNodes();
            Node current = openList.poll();
            closedList.add(current.word);
            if (current.word.equals(end)) {
                List<String> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.word);
                    wordLadderAStar.setLadder(path);
                    current = current.parent;
                }
                return wordLadderAStar;
            }

            for (String neighbor : wordList) {
                if (!closedList.contains(neighbor) && hamming.hammingDistance(current.word, neighbor) == 1) {
                    int g = current.g + 1;
                    int h = hamming.hammingDistance(neighbor, end);
                    Node newNode = new Node(neighbor, g, h, current);

                    if (!openList.contains(newNode) || g < newNode.g) {
                        openList.add(newNode);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }
        return wordLadderAStar;
    }
}
