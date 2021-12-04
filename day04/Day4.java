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
        ArrayList<boolean[][]> boolList = new ArrayList<>();
        while (scanner.hasNext()){
            scanner.nextLine();
            int[][] bingo = new int[5][5];
            boolean[][] boolBingo = new boolean[5][5];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    bingo[i][j] = scanner.nextInt();
                    boolBingo[i][j] = false;
                }
            }
            bingoList.add(bingo);
            boolList.add(boolBingo);
        }

        int drawNumber = 0;
        int pos = 0;
        for (int i = 0; i < draws.length; i++){
            int curr = Integer.parseInt(draws[i]);
            boolean won = false;
            for (int a = 0; a < bingoList.size(); a++){
                int[][] temp = bingoList.get(a);
                boolean[][] boolTemp = boolList.get(a);
                for (int b = 0; b < 5; b++){
                    for (int c = 0; c < 5; c++){
                        if (curr == temp[b][c]){
                            boolTemp[b][c] = true;
                        }
                    }
                }
                boolList.set(a, boolTemp);
                if (checker(boolTemp)){
                    won = true;
                    pos = a;
                    drawNumber = curr;
                    break;
                }
            }
            if (won){
                break;
            }
        }
        int sum = 0;
        int[][] winner = bingoList.get(pos);
        boolean[][] boolWin = boolList.get(pos);
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (!boolWin[i][j]){
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
        ArrayList<boolean[][]> boolList = new ArrayList<>();
        while (scanner.hasNext()){
            scanner.nextLine();
            int[][] bingo = new int[5][5];
            boolean[][] boolBingo = new boolean[5][5];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    bingo[i][j] = scanner.nextInt();
                    boolBingo[i][j] = false;
                }
            }
            bingoList.add(bingo);
            boolList.add(boolBingo);
        }

        int index = 0;
        int drawNumber = 0;
        for (int i = 0; i < draws.length; i++){
            int curr = Integer.parseInt(draws[i]);
            for (int a = 0; a < bingoList.size(); a++){
                int[][] temp = bingoList.get(a);
                boolean[][] boolTemp = boolList.get(a);
                for (int b = 0; b < 5; b++){
                    for (int c = 0; c < 5; c++){
                        if (curr == temp[b][c]){
                            boolTemp[b][c] = true;
                        }
                    }
                }
                boolList.set(a, boolTemp);
                if (checker(boolTemp)){
                    boolList.remove(a);
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
        boolean[][] boolWinner = boolList.get(0);
        for (int i = index + 1; i < draws.length; i++){
            int curr = Integer.parseInt(draws[i]);
            for (int b = 0; b < 5; b++){
                for (int c = 0; c < 5; c++){
                    if (curr == winner[b][c]){
                        boolWinner[b][c] = true;
                    }
                }
            }
            if (checker(boolWinner)){
                drawNumber = curr;
                break;
            }
        }
        int sum = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (!boolWinner[i][j]){
                    sum += winner[i][j];
                }
            }
        }
        System.out.println(sum * drawNumber);
        scanner.close();
    }

    public static boolean checker(boolean[][] arr){
        for (int i = 0; i < 5; i++){
            if (arr[i][0] && arr[i][1] && arr[i][2] && arr[i][3] && arr[i][4]){
                return true;
            } else if (arr[0][i] && arr[1][i] && arr[2][i] && arr[3][i] && arr[4][i]){
                return true;
            }
        }
        return false;
    }
}