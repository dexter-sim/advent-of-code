import java.io.*;
import java.util.*;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day9-input.txt"));
        List<List<Integer>> heightmap = new ArrayList<>();
        List<List<Boolean>> boolmap = new ArrayList<>();
        while (scanner.hasNext()){
            List<Integer> row = new ArrayList<>();
            List<Boolean> boolrow = new ArrayList<>();
            for (char c : scanner.nextLine().toCharArray()){
                row.add(Character.getNumericValue(c));
                boolrow.add(false);
            }
            heightmap.add(row);
            boolmap.add(boolrow);
        }
        scanner.close();

        int risk = 0;
        int[] arr = new int[3];
        for (int i = 0; i < heightmap.size(); i++){
            for (int j = 0; j < heightmap.get(i).size(); j++){
                if (checkLowPoint(heightmap, i, j)){
                    risk += heightmap.get(i).get(j) + 1;
                    arr[0] = Math.max(arr[0], traverse(heightmap, boolmap, i, j));
                    Arrays.sort(arr);
                }
            }
        }
        System.out.println(risk);                       // Part 1 Answer
        System.out.println(arr[0] * arr[1] * arr[2]);   // Part 2 Answer
    }

    public static int traverse(List<List<Integer>> heightmap, List<List<Boolean>> boolmap, int i, int j) {
        if (i < 0 || i >= heightmap.size() || j < 0 || j >= heightmap.get(i).size()){
            return 0;
        } else if (heightmap.get(i).get(j) == 9){
            return 0;
        } else if (boolmap.get(i).get(j)){
            return 0;
        } else {
            boolmap.get(i).set(j, true);
            return 1 + traverse(heightmap, boolmap, i - 1, j) + traverse(heightmap, boolmap, i + 1, j)
                    + traverse(heightmap, boolmap, i, j - 1) + traverse(heightmap, boolmap, i, j + 1);
        }
    }

    public static boolean checkLowPoint(List<List<Integer>> heightmap, int i, int j) {
        int curr = heightmap.get(i).get(j);
        if (i > 0 && i < heightmap.size() - 1){
            if (j > 0 && j < heightmap.get(i).size() - 1){
                return heightmap.get(i).get(j - 1) > curr && heightmap.get(i).get(j + 1) > curr
                        && heightmap.get(i - 1).get(j) > curr && heightmap.get(i + 1).get(j) > curr;
            } else if (j == 0){
                return heightmap.get(i).get(j + 1) > curr && heightmap.get(i - 1).get(j) > curr && heightmap.get(i + 1).get(j) > curr;
            } else {
                return heightmap.get(i).get(j - 1) > curr && heightmap.get(i - 1).get(j) > curr && heightmap.get(i + 1).get(j) > curr;
            }
        } else if (i == 0){
            if (j > 0 && j < heightmap.get(i).size() - 1){
                return heightmap.get(i).get(j - 1) > curr && heightmap.get(i).get(j + 1) > curr && heightmap.get(i + 1).get(j) > curr;
            } else if (j == 0){
                return heightmap.get(i).get(j + 1) > curr && heightmap.get(i + 1).get(j) > curr;
            } else {
                return heightmap.get(i).get(j - 1) > curr && heightmap.get(i + 1).get(j) > curr;
            }
        } else {
            if (j > 0 && j < heightmap.get(i).size() - 1){
                return heightmap.get(i).get(j - 1) > curr && heightmap.get(i).get(j + 1) > curr && heightmap.get(i - 1).get(j) > curr;
            } else if (j == 0){
                return heightmap.get(i).get(j + 1) > curr && heightmap.get(i - 1).get(j) > curr;
            } else {
                return heightmap.get(i).get(j - 1) > curr && heightmap.get(i - 1).get(j) > curr;
            }
        }
    }
}
