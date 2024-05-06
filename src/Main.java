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
        System.out.println("Masukkan Destination Word: ");
        String end = scanner.next();

        switch (choice) {
            case 1:
                runAStar(start, end);
                break;
            case 2:
                runGBFS(start, end);
                break;
            case 3:
                runUCS(start, end);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static void runAStar(String start, String end) {
        String filePath = "word.txt";
        Set<String> wordList = print.readWordListFromFile(filePath, start);
    
        List<String> path = WordLadderAStar.findPath(start, end, wordList);
        if (path != null) {
            System.out.println("Shortest path from " + start + " to " + end + ": " + path);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }

    public static void runGBFS(String start, String end) {
        String filePath = "word.txt";
        Set<String> wordList = print.readWordListFromFile(filePath, start);
        
        List<String> ladder = WordLadderGBFS.greedyBestFirstSearch(start, end, wordList);
        
        if (!ladder.isEmpty()) {
            System.out.println("Word ladder from " + start + " to " + end + ":");
            for (String word : ladder) {
                System.out.println(word);
            }
        } else {
            System.out.println("No word ladder found from " + start + " to " + end + ".");
        }
    }

    public static void runUCS(String start, String end) {
        String filePath = "word.txt";
        Set<String> wordList = print.readWordListFromFile(filePath, start);
        
        List<String> wordLadder = WordLadderUCS.findWordLadder(start, end, wordList);
        if (wordLadder != null) {
            System.out.println("Shortest word ladder:");
            for (String word : wordLadder) {
                System.out.print(word + " ");
            }
        } else {
            System.out.println("No word ladder found.");
        }
    }

}

