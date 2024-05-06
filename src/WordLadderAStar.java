package src;
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

    static List<String> findPath(String start, String end, Set<String> wordList) {
    
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        Set<String> closedList = new HashSet<>();
        Map<String, Node> parentMap = new HashMap<>();
        Node startNode = new Node(start, 0, hamming.hammingDistance(start, end), null);
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
        return null;
    }
}
