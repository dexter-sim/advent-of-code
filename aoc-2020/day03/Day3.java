import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day03/day3-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        solve(inputList);
    }

    public static void solve(List<String> inputList) {
        List<List<Character>> grid = inputList.stream()
                .map(s -> s.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();
        int r1d1 = helper(grid, 1, 1);
        int r3d1 = helper(grid, 3, 1);
        int r5d1 = helper(grid, 5, 1);
        int r7d1 = helper(grid, 7, 1);
        int r1d2 = helper(grid, 1, 2);
        int part2 = r1d1 * r3d1 * r5d1 * r7d1 * r1d2;
        System.out.println("Part 1: " + r3d1);
        System.out.println("Part 2: " + part2);
    }

    public static int helper(List<List<Character>> grid, int right, int down) {
        int count = 0;
        for (int i = 0, j = 0; i < grid.size(); i += down) {
            if (grid.get(i).get(j) == '#') {
                count++;
            }
            j += right;
            j %= grid.get(i).size();
        }
        return count;
    }
}
