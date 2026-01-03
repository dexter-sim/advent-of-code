import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day10/day10-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        solve(inputList);
    }

    public static void solve(List<String> inputList) {
        List<List<Integer>> grid = inputList.stream()
                .map(input -> input.chars()
                        .map(Character::getNumericValue)
                        .boxed()
                        .toList())
                .toList();

        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) != 0) {
                    continue;
                }

                Set<String> visited = new HashSet<>();
                count1 += helper(i, j, 0, visited, grid);
                count2 += helper2(i, j, 0, grid);

            }
        }
        System.out.println(count1);
        System.out.println(count2);
    }

    public static int helper(int i, int j, int height, Set<String> visited, List<List<Integer>> grid) {
        if (height == 9) {
            return visited.add(i + "," + j) ? 1 : 0;
        }

        int count = 0;

        if (i - 1 >= 0 && grid.get(i - 1).get(j) == height + 1) {
            count += helper(i - 1, j, height + 1, visited, grid);
        }

        if (i + 1 < grid.size() && grid.get(i + 1).get(j) == height + 1) {
            count += helper(i + 1, j, height + 1, visited, grid);
        }

        if (j - 1 >= 0 && grid.get(i).get(j - 1) == height + 1) {
            count += helper(i, j - 1, height + 1, visited, grid);
        }

        if (j + 1 < grid.get(i).size() && grid.get(i).get(j + 1) == height + 1) {
            count += helper(i, j + 1, height + 1, visited, grid);
        }

        return count;
    }

    public static int helper2(int i, int j, int height, List<List<Integer>> grid) {
        if (height == 9) {
            return 1;
        }

        int count = 0;

        if (i - 1 >= 0 && grid.get(i - 1).get(j) == height + 1) {
            count += helper2(i - 1, j, height + 1, grid);
        }

        if (i + 1 < grid.size() && grid.get(i + 1).get(j) == height + 1) {
            count += helper2(i + 1, j, height + 1, grid);
        }

        if (j - 1 >= 0 && grid.get(i).get(j - 1) == height + 1) {
            count += helper2(i, j - 1, height + 1, grid);
        }

        if (j + 1 < grid.get(i).size() && grid.get(i).get(j + 1) == height + 1) {
            count += helper2(i, j + 1, height + 1, grid);
        }

        return count;
    }

}
