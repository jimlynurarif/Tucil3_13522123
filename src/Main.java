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
            System.out.println("Source word not found in word.txt file");
            System.out.println("Input Source Word: ");
            start = scanner.next();
            wordList = print.readWordListFromFile(filePath, start);
        }

        System.out.println("Input Destination Word: ");
        String end = scanner.next();

        while (!wordList.contains(end)) {
            if (end.length() != start.length()) {
                System.out.println("Destination word must have the same length as the source word");
            }
            System.out.println("Destination word not found in word.txt file");
            System.out.println("Input Destination Word: ");
            end = scanner.next();
        }

        int time;
        switch (choice) {
            case 1:
                System.out.println("You have selected A* Algorithm.");
                time = runAStar(start, end, wordList);
                break;
            case 2:
                System.out.println("You have selected GBFS Algorithm.");
                time = runGBFS(start, end, wordList);
                break;
            case 3:
                System.out.println("You have selected UCS Algorithm.");
                time = runUCS(start, end, wordList);
                break;
            default:
                System.out.println("Invalid choice.");
                time = -1;
        }
        scanner.close();

        System.out.println("Time taken: " + time + " ms");
    }

    public static int runAStar(String start, String end, Set<String> wordList) {
        int startTime = (int) System.currentTimeMillis();
        WordLadderAStar wordLadderAStar = WordLadderAStar.Astar(start, end, wordList);
        List<String> ladder = wordLadderAStar.getLadder();
        int visitedNodes = wordLadderAStar.getVisitedNodes();

        if (ladder != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + ladder);
            System.out.println("Visited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
        int endTime = (int) System.currentTimeMillis();
        return endTime - startTime;
    }

    public static int runGBFS(String start, String end, Set<String> wordList) {
        int startTime = (int) System.currentTimeMillis();
        WordLadderGBFS wordLadderGBFS = WordLadderGBFS.greedyBestFirstSearch(start, end, wordList);
        List<String> ladder = wordLadderGBFS.getLadder();
        int visitedNodes = wordLadderGBFS.getVisitedNodes();
        
        if (ladder != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + ladder);
            System.out.println("Visited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
        int endTime = (int) System.currentTimeMillis();
        return endTime - startTime;
    }

    public static int runUCS(String start, String end, Set<String> wordList) {
        int startTime = (int) System.currentTimeMillis();
        WordLadderUCS UCS = WordLadderUCS.findWordLadder(start, end, wordList);
        List<String> ladder = UCS.getLadder();
        int visitedNodes = UCS.getVisitedNodes();
        
        if (ladder != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + ladder);
            System.out.println("Visited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
        int endTime = (int) System.currentTimeMillis();
        return endTime - startTime;
    }
}

