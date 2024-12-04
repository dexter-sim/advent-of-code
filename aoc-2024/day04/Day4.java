import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day04/day4-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<List<Character>> grid = createGrid(inputList);
        int count = 0;

        int[] rowDirections = new int[]{0, 1, 0, -1, 1, 1, -1, -1};
        int[] colDirections = new int[]{1, 0, -1, 0, 1, -1, 1, -1};

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (isXMAS(grid, i, j, rowDirections[dir], colDirections[dir])) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        List<List<Character>> grid = createGrid(inputList);
        int count = 0;

        for (int i = 1; i < grid.size() - 1; i++) {
            for (int j = 1; j < grid.get(i).size() - 1; j++) {
                if (grid.get(i).get(j) != 'A') {
                    continue;
                }

                if (grid.get(i - 1).get(j - 1) == grid.get(i + 1).get(j - 1) && grid.get(i - 1).get(j + 1) == grid.get(i + 1).get(j + 1)) {
                    if (grid.get(i - 1).get(j - 1) == 'M' && grid.get(i - 1).get(j + 1) == 'S') {
                        count++;
                    }

                    if (grid.get(i - 1).get(j - 1) == 'S' && grid.get(i - 1).get(j + 1) == 'M') {
                        count++;
                    }
                }

                if (grid.get(i - 1).get(j - 1) == grid.get(i - 1).get(j + 1) && grid.get(i + 1).get(j - 1) == grid.get(i + 1).get(j + 1)) {
                    if (grid.get(i - 1).get(j - 1) == 'M' && grid.get(i + 1).get(j - 1) == 'S') {
                        count++;
                    }

                    if (grid.get(i - 1).get(j - 1) == 'S' && grid.get(i + 1).get(j - 1) == 'M') {
                        count++;
                    }
                }

            }
        }
        System.out.println(count);
    }

    public static List<List<Character>> createGrid(List<String> inputList) {
        List<List<Character>> grid = new ArrayList<>();
        for (String input : inputList) {
            List<Character> row = new ArrayList<>();
            for (char c : input.toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }
        return grid;
    }

    public static boolean isXMAS(List<List<Character>> grid, int i, int j, int rowDir, int colDir) {
        String word = "XMAS";
        for (int k = 0; k < word.length(); k++) {
            int newRow = i + k * rowDir;
            int newCol = j + k * colDir;

            if (newRow < 0 || newRow >= grid.size() || newCol < 0 || newCol >= grid.get(i).size()) {
                return false;
            }

            if (grid.get(newRow).get(newCol) != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }
}
