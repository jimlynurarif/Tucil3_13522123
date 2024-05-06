package src;
import java.util.*;

public class Main {

    static class result{
        public int time;
        public int visitedNodes;
        public List<String> ladder;
    }
    public static void main(String[] args) {

        System.out.println("                       ");
        System.out.println("Welcome to Word Ladder!");
        System.out.println("                       ");
        System.out.println("1. A* Algorithm");
        System.out.println("2. Greedy Best First Search Algorithm");
        System.out.println("3. Uniform Cost Search Algorithm");
        System.out.print("Choose the algorithm NUMBER you want to use: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.print("Input Source Word: ");
        String start = scanner.next();

        String filePath = "word.txt";
        Set<String> wordList = print.readWordListFromFile(filePath, start);

        while (!wordList.contains(start)) {
            System.out.println("Source word not found in word.txt file");
            System.out.print("Input Source Word: ");
            start = scanner.next();
            wordList = print.readWordListFromFile(filePath, start);
        }

        System.out.print("Input Destination Word: ");
        String end = scanner.next();

        while (!wordList.contains(end)) {
            if (end.length() != start.length()) {
                System.out.println("Destination word must have the same length as the source word");
            }
            System.out.println("Destination word not found in word.txt file");
            System.out.print("Input Destination Word: ");
            end = scanner.next();
        }

        result res;
        switch (choice) {
            case 1:
                System.out.println("                               ");
                System.out.println("You have selected A* Algorithm.");
                res = runAStar(start, end, wordList);
                break;
            case 2:
                System.out.println("                                 ");
                System.out.println("You have selected GBFS Algorithm.");
                res = runGBFS(start, end, wordList);
                break;
            case 3:
                System.out.println("                                ");
                System.out.println("You have selected UCS Algorithm.");
                res = runUCS(start, end, wordList);
                break;
            default:
                System.out.println("Invalid choice.");
                res = new result();
        }
        scanner.close();

        if (res.ladder != null) {
            System.out.println("                               ");
            System.out.println("The shortest path from " + start + " to " + end + " is: ");
            System.out.println(res.ladder);
            System.out.println("                               ");
            System.out.println("Number of visited nodes: " + res.visitedNodes);
            System.out.println("Time taken: " + res.time + "ms");
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }

    public static result runAStar(String start, String end, Set<String> wordList) {
        result res = new result();

        int startTime = (int) System.currentTimeMillis();
        WordLadderAStar wordLadderAStar = WordLadderAStar.Astar(start, end, wordList);
        int endTime = (int) System.currentTimeMillis();

        res.time = endTime - startTime;
        res.ladder = wordLadderAStar.getLadder();
        res.visitedNodes = wordLadderAStar.getVisitedNodes();

        return res;
    }

    public static result runGBFS(String start, String end, Set<String> wordList) {
        result res = new result();

        int startTime = (int) System.currentTimeMillis();
        WordLadderGBFS wordLadderGBFS = WordLadderGBFS.GBFS(start, end, wordList);
        int endTime = (int) System.currentTimeMillis();

        res.time = endTime - startTime;
        res.ladder = wordLadderGBFS.getLadder();
        res.visitedNodes = wordLadderGBFS.getVisitedNodes();

        return res;
    }

    public static result runUCS(String start, String end, Set<String> wordList) {
        result res = new result();

        int startTime = (int) System.currentTimeMillis();
        WordLadderUCS wordLadderUCS = WordLadderUCS.UCS(start, end, wordList);
        int endTime = (int) System.currentTimeMillis();

        res.time = endTime - startTime;
        res.ladder = wordLadderUCS.getLadder();
        res.visitedNodes = wordLadderUCS.getVisitedNodes();

        return res;
    }
}

