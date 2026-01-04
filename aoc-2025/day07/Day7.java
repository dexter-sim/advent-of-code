import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day07/day7-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<List<Character>> grid = inputList.stream()
                .map(s -> s.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();

        int count = 0;
        Set<Integer> beams = new HashSet<>(List.of(inputList.get(0).indexOf('S')));
        for (int i = 1; i < grid.size(); i++) {
            Set<Integer> nextRowBeams = new HashSet<>();
            for (int beam : beams) {
                if (grid.get(i).get(beam) == '^') {
                    count++;
                    nextRowBeams.add(beam - 1);
                    nextRowBeams.add(beam + 1);
                } else {
                    nextRowBeams.add(beam);
                }
            }
            beams = nextRowBeams;
        }
        System.out.println(count);
    }

    // Recursive DFS with memoization
    public static void part2(List<String> inputList) {
        List<List<Character>> grid = inputList.stream()
                .map(s -> s.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();

        long count = helper(0, inputList.get(0).indexOf('S'), grid, new HashMap<>());
        System.out.println(count);
    }

    public static long helper(int x, int y, List<List<Character>> grid, Map<String, Long> memo) {
        if (x == grid.size() - 1) {
            return 1;
        }

        String key = x + "," + y;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (grid.get(x).get(y) == '^') {
            memo.put(key, helper(x + 1, y - 1, grid, memo) + helper(x + 1, y + 1, grid, memo));
        } else {
            memo.put(key, helper(x + 1, y, grid, memo));
        }

        return memo.get(key);
    }
}
