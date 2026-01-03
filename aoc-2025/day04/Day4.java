import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day04/day4-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<List<Character>> grid = new ArrayList<>();
        for (String input : inputList) {
            List<Character> row = new ArrayList<>();
            for (char c : input.toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }

        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) != '@') {
                    continue;
                }

                int adjacentRolls = 0;
                for (int k = Math.max(i - 1, 0); k < Math.min(grid.size(), i + 2); k++) {
                    for (int l = Math.max(j - 1, 0);  l < Math.min(grid.get(i).size(), j + 2); l++) {
                        if (k == i && l == j) {
                            continue;
                        }
                        if (grid.get(k).get(l) == '@') {
                            adjacentRolls++;
                        }
                    }
                }

                if (adjacentRolls < 4) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        List<List<Character>> grid = new ArrayList<>();
        for (String input : inputList) {
            List<Character> row = new ArrayList<>();
            for (char c : input.toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }


        int replaced;
        do {
            replaced = 0;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) != '@') {
                        continue;
                    }

                    int adjacentRolls = 0;
                    for (int k = Math.max(i - 1, 0); k < Math.min(grid.size(), i + 2); k++) {
                        for (int l = Math.max(j - 1, 0); l < Math.min(grid.get(i).size(), j + 2); l++) {
                            if (k == i && l == j) {
                                continue;
                            }
                            if (grid.get(k).get(l) == '@') {
                                adjacentRolls++;
                            }
                        }
                    }

                    if (adjacentRolls < 4) {
                        replaced++;
                        grid.get(i).set(j, 'x');
                    }
                }
            }
        } while (replaced > 0);

        long count = grid.stream()
                .flatMap(List::stream)
                .filter(ch -> ch == 'x')
                .count();
        System.out.println(count);
    }
}
