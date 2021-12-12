import java.util.Scanner;
import java.io.*;

public class Day11 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day11-input.txt"));
        int[][] arr = new int[10][10];
        for (int i = 0; i < arr.length; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        scanner.close();

        int flashes = 0;
        int steps = 0;
        boolean allFlashed = false;
        for (int i = 1; i <= 100 || !allFlashed; i++) {
            increment(arr);
            int flashesThisStep = flash(arr);
            if (i <= 100) {
                flashes += flashesThisStep;
            }
            if (!allFlashed && flashesThisStep == 100) {
                allFlashed = true;
                steps = i;
            }
        }
        System.out.println(flashes);    // Part 1 Answer
        System.out.println(steps);      // Part 2 Answer
    }

    public static void increment(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j]++;
            }
        }
    }

    public static int flash(int[][] arr) {
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 9) {
                    arr[i][j] = 0;
                    temp++;
                    flag = true;
                    incrementAdjacent(arr, i, j);
                }
            }
        }
        if (flag) {
            temp += flash(arr);
        }
        return temp;
    }

    public static void incrementAdjacent(int[][] arr, int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || j < 0 || i > 9 || j > 9) {
                    continue;
                } else if ((i == x && j == y) || arr[i][j] == 0) {
                    continue;
                } else {
                    arr[i][j]++;
                }
            }
        }
    }

}