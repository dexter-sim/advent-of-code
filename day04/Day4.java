import java.io.*;
import java.util.*;

public class Day4 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day4-input.txt"));
        String[] draws = scanner.nextLine().split(",");
        ArrayList<int[][]> bingoList = new ArrayList<>();
        while (scanner.hasNext()){
            scanner.nextLine();
            int[][] bingo = new int[5][5];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    bingo[i][j] = scanner.nextInt();
                }
            }
            bingoList.add(bingo);
        }

        int drawNumber = 0;
        int pos = 0;
        for (String draw : draws) {
            drawNumber = Integer.parseInt(draw);
            boolean won = false;
            for (int a = 0; a < bingoList.size(); a++) {
                int[][] temp = bingoList.get(a);
                for (int b = 0; b < 5; b++) {
                    for (int c = 0; c < 5; c++) {
                        if (drawNumber == temp[b][c]) {
                            temp[b][c] = -1;
                        }
                    }
                }
                if (checker(temp)) {
                    won = true;
                    pos = a;
                    break;
                }
            }
            if (won) {
                break;
            }
        }
        int sum = 0;
        int[][] winner = bingoList.get(pos);
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (winner[i][j] != -1){
                    sum += winner[i][j];
                }
            }
        }
        System.out.println(sum * drawNumber);
        scanner.close();
    }

    public static void part2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day4-input.txt"));
        String[] draws = scanner.nextLine().split(",");
        ArrayList<int[][]> bingoList = new ArrayList<>();
        while (scanner.hasNext()){
            scanner.nextLine();
            int[][] bingo = new int[5][5];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    bingo[i][j] = scanner.nextInt();
                }
            }
            bingoList.add(bingo);
        }

        int index = 0;
        int drawNumber = 0;
        for (int i = 0; i < draws.length; i++){
            drawNumber = Integer.parseInt(draws[i]);
            for (int a = 0; a < bingoList.size(); a++){
                int[][] temp = bingoList.get(a);
                for (int b = 0; b < 5; b++){
                    for (int c = 0; c < 5; c++){
                        if (drawNumber == temp[b][c]){
                            temp[b][c] = -1;
                        }
                    }
                }
                if (checker(temp)){
                    bingoList.remove(a);
                    a--;
                }
            }
            if (bingoList.size() == 1){
                index = i;
                break;
            }
        }

        int[][] winner = bingoList.get(0);
        for (int i = index + 1; i < draws.length; i++){
            drawNumber = Integer.parseInt(draws[i]);
            for (int b = 0; b < 5; b++){
                for (int c = 0; c < 5; c++){
                    if (drawNumber == winner[b][c]){
                        winner[b][c] = -1;
                    }
                }
            }
            if (checker(winner)){
                break;
            }
        }
        int sum = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (winner[i][j] != -1){
                    sum += winner[i][j];
                }
            }
        }
        System.out.println(sum * drawNumber);
        scanner.close();
    }

    public static boolean checker(int[][] arr){
        for (int i = 0; i < 5; i++){
            if (arr[i][0] == -1 && arr[i][1] == -1 && arr[i][2] == -1 && arr[i][3] == -1 && arr[i][4] == -1){
                return true;
            } else if (arr[0][i] == -1 && arr[1][i] == -1 && arr[2][i] == -1 && arr[3][i] == -1 && arr[4][i] == -1){
                return true;
            }
        }
        return false;
    }
}