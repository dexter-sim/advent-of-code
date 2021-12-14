import java.io.*;
import java.util.*;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day13-input.txt"));
        boolean[][] paper = new boolean[895][1311];
        String s = scanner.nextLine();
        while (s.length() > 0){
            String[] temp = s.split(",");
            paper[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = true;
            s = scanner.nextLine();
        }
        List<String> instructions = new ArrayList<>();
        while (scanner.hasNext()){
            instructions.add(scanner.nextLine());
        }
        scanner.close();

        if (instructions.get(0).contains("x")){
            paper = foldAlongX(paper);
        } else {
            paper = foldAlongY(paper);
        }
        instructions.remove(0);
        int count = 0;
        for (int i = 0; i < paper.length; i++){
            for (int j = 0; j < paper[i].length; j++){
                if (paper[i][j]){
                    count++;
                }
            }
        }
        System.out.println(count);      // Part 1 Answer

        for (String str : instructions){
            if (str.contains("x")){
                paper = foldAlongX(paper);
            } else {
                paper = foldAlongY(paper);
            }
        }
        char[][] result = new char[paper.length][paper[0].length];
        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length; j++){
                result[i][j] = paper[i][j] ? '*' : ' ';
            }
        }
        // Part 2 Answer - HKUJGAJZ
        System.out.println(Arrays.deepToString(result).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    public static boolean[][] foldAlongY(boolean[][] paper) {
        boolean[][] foldedPaper = new boolean[paper.length / 2][paper[0].length];
        for (int i = 0; i < paper.length; i++){
            for (int j = 0; j < paper[i].length; j++){
                if (i >= paper.length / 2){
                    if (paper[i][j]) {
                        foldedPaper[foldedPaper.length * 2 - i][j] = paper[i][j];
                    }
                } else {
                    foldedPaper[i][j] = paper[i][j];
                }
            }
        }
        return foldedPaper;
    }

    public static boolean[][] foldAlongX(boolean[][] paper) {
        boolean[][] foldedPaper = new boolean[paper.length][paper[0].length / 2];
        for (int i = 0; i < paper.length; i++){
            for (int j = 0; j < paper[i].length; j++){
                if (j >= paper[i].length / 2){
                    if (paper[i][j]) {
                        foldedPaper[i][foldedPaper[i].length * 2 - j] = paper[i][j];
                    }
                } else {
                    foldedPaper[i][j] = paper[i][j];
                }
            }
        }
        return foldedPaper;
    }
}