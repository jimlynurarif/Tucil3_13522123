package src;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Word Ladder!");
        System.out.println("Please Select Algorithm: ");
        System.out.println("1. A* Algorithm");
        System.out.println("2. Greedy Best First Search Algorithm");
        System.out.println("3. Uniform Cost Search Algorithm");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Masukkan Source Word: ");
        String start = scanner.next();

        String filePath = "word.txt";
        Set<String> wordList = print.readWordListFromFile(filePath, start);

        while (!wordList.contains(start)) {
            System.out.println("Start word not found in word.txt file");
            System.out.println("Masukkan Start Word: ");
            start = scanner.next();
        }

        System.out.println("Masukkan Destination Word: ");
        String end = scanner.next();

        while (!wordList.contains(end)) {
            System.out.println("Destination word not found in word.txt file");
            System.out.println("Masukkan Destination Word: ");
            end = scanner.next();
        }

        switch (choice) {
            case 1:
                runAStar(start, end, wordList);
                break;
            case 2:
                runGBFS(start, end, wordList);
                break;
            case 3:
                runUCS(start, end, wordList);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static void runAStar(String start, String end, Set<String> wordList) {
        //List<String> ladder, int visitedNodes = WordLadderAStar.Astar(start, end, wordList);
        WordLadderAStar wordLadderAStar = WordLadderAStar.Astar(start, end, wordList);
        List<String> ladder = wordLadderAStar.getLadder();
        int visitedNodes = wordLadderAStar.getVisitedNodes();

        if (ladder != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + ladder);
            System.out.println("Visited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }

    public static void runGBFS(String start, String end, Set<String> wordList) {
        // List<String> ladder = WordLadderGBFS.greedyBestFirstSearch(start, end, wordList);
        WordLadderGBFS wordLadderGBFS = WordLadderGBFS.greedyBestFirstSearch(start, end, wordList);
        List<String> ladder = wordLadderGBFS.getLadder();
        int visitedNodes = wordLadderGBFS.getVisitedNodes();
        
        if (ladder != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + ladder);
            System.out.println("Visited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }

    public static void runUCS(String start, String end, Set<String> wordList) {
        WordLadderUCS UCS = WordLadderUCS.findWordLadder(start, end, wordList);
        List<String> ladder = UCS.getLadder();
        int visitedNodes = UCS.getVisitedNodes();
        
        if (ladder != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + ladder);
            System.out.println("Visited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }
}

