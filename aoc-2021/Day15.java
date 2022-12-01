import java.util.*;
import java.io.*;

class Pair {
    private final int first;
    private final int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}

public class Day15 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day15-input.txt"));
        int[][] arr = new int[100][100];
        int[][] dijkstra = new int[100][100];
        Queue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return dijkstra[o1.getFirst()][o1.getSecond()] < dijkstra[o2.getFirst()][o2.getSecond()] ? -1 : dijkstra[o1.getFirst()][o1.getSecond()] == dijkstra[o2.getFirst()][o2.getSecond()] ? 0 : 1;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Character.getNumericValue(s.charAt(j));
                dijkstra[i][j] = Integer.MAX_VALUE;
            }
        }
        scanner.close();
        dijkstra[0][0] = 0;
        priorityQueue.add(new Pair(0, 0));
        helper(priorityQueue, dijkstra, arr);
        System.out.println(dijkstra[99][99]);
    }

    public static void part2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day15-input.txt"));
        int[][] arr = new int[500][500];
        int[][] dijkstra = new int[500][500];
        Queue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return dijkstra[o1.getFirst()][o1.getSecond()] < dijkstra[o2.getFirst()][o2.getSecond()] ? -1 : dijkstra[o1.getFirst()][o1.getSecond()] == dijkstra[o2.getFirst()][o2.getSecond()] ? 0 : 1;
            }
        });

        for (int i = 0; i < 100; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dijkstra[i + j * 100], Integer.MAX_VALUE);
            }

            for (int j = 0; j < 100; j++) {
                arr[i][j] = Character.getNumericValue(s.charAt(j));

                arr[i + 100][j] = (arr[i][j] + 1) % 9 == 0 ? 9 : (arr[i][j] + 1) % 9;
                arr[i][j + 100] = (arr[i][j] + 1) % 9 == 0 ? 9 : (arr[i][j] + 1) % 9;

                arr[i + 200][j] = (arr[i][j] + 2) % 9 == 0 ? 9 : (arr[i][j] + 2) % 9;
                arr[i][j + 200] = (arr[i][j] + 2) % 9 == 0 ? 9 : (arr[i][j] + 2) % 9;
                arr[i + 100][j + 100] = (arr[i][j] + 2) % 9 == 0 ? 9 : (arr[i][j] + 2) % 9;

                arr[i + 300][j] = (arr[i][j] + 3) % 9 == 0 ? 9 : (arr[i][j] + 3) % 9;
                arr[i][j + 300] = (arr[i][j] + 3) % 9 == 0 ? 9 : (arr[i][j] + 3) % 9;
                arr[i + 200][j + 100] = (arr[i][j] + 3) % 9 == 0 ? 9 : (arr[i][j] + 3) % 9;
                arr[i + 100][j + 200] = (arr[i][j] + 3) % 9 == 0 ? 9 : (arr[i][j] + 3) % 9;

                arr[i + 400][j] = (arr[i][j] + 4) % 9 == 0 ? 9 : (arr[i][j] + 4) % 9;
                arr[i][j + 400] = (arr[i][j] + 4) % 9 == 0 ? 9 : (arr[i][j] + 4) % 9;
                arr[i + 300][j + 100] = (arr[i][j] + 4) % 9 == 0 ? 9 : (arr[i][j] + 4) % 9;
                arr[i + 200][j + 200] = (arr[i][j] + 4) % 9 == 0 ? 9 : (arr[i][j] + 4) % 9;
                arr[i + 100][j + 300] = (arr[i][j] + 4) % 9 == 0 ? 9 : (arr[i][j] + 4) % 9;

                arr[i + 400][j + 100] = (arr[i][j] + 5) % 9 == 0 ? 9 : (arr[i][j] + 5) % 9;
                arr[i + 100][j + 400] = (arr[i][j] + 5) % 9 == 0 ? 9 : (arr[i][j] + 5) % 9;
                arr[i + 200][j + 300] = (arr[i][j] + 5) % 9 == 0 ? 9 : (arr[i][j] + 5) % 9;
                arr[i + 300][j + 200] = (arr[i][j] + 5) % 9 == 0 ? 9 : (arr[i][j] + 5) % 9;

                arr[i + 400][j + 200] = (arr[i][j] + 6) % 9 == 0 ? 9 : (arr[i][j] + 6) % 9;
                arr[i + 200][j + 400] = (arr[i][j] + 6) % 9 == 0 ? 9 : (arr[i][j] + 6) % 9;
                arr[i + 300][j + 300] = (arr[i][j] + 6) % 9 == 0 ? 9 : (arr[i][j] + 6) % 9;

                arr[i + 400][j + 300] = (arr[i][j] + 7) % 9 == 0 ? 9 : (arr[i][j] + 7) % 9;
                arr[i + 300][j + 400] = (arr[i][j] + 7) % 9 == 0 ? 9 : (arr[i][j] + 7) % 9;
                arr[i + 400][j + 400] = (arr[i][j] + 8) % 9 == 0 ? 9 : (arr[i][j] + 8) % 9;
            }
        }
        scanner.close();
        dijkstra[0][0] = 0;
        priorityQueue.add(new Pair(0, 0));
        helper(priorityQueue, dijkstra, arr);
        System.out.println(dijkstra[499][499]);
    }

    public static void helper(Queue<Pair> priorityQueue, int[][] dijkstra, int[][] arr) {
        while (!priorityQueue.isEmpty()) {
            Pair current = priorityQueue.poll();
            int i = current.getFirst();
            int j = current.getSecond();
            int currentValue = dijkstra[i][j];
            updateDijkstra(priorityQueue, dijkstra, arr, i + 1, j, currentValue);
            updateDijkstra(priorityQueue, dijkstra, arr, i - 1, j, currentValue);
            updateDijkstra(priorityQueue, dijkstra, arr, i, j + 1, currentValue);
            updateDijkstra(priorityQueue, dijkstra, arr, i, j - 1, currentValue);
        }
    }

    public static void updateDijkstra(Queue<Pair> priorityQueue, int[][] dijkstra, int[][] arr, int i, int j, int currentValue) {
        if (i >= 0 && j >= 0 && i < arr.length && j < arr.length && currentValue + arr[i][j] < dijkstra[i][j]) {
            dijkstra[i][j] = currentValue + arr[i][j];
            priorityQueue.add(new Pair(i, j));
        }
    }
}