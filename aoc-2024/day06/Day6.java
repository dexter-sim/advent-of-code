import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day06/day6-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        char[][] grid = createGrid(inputList);
        int x = 0;
        int y = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    x = i;
                    y = j;
                    grid[i][j] = 'X';
                }
            }
        }

        traverse(grid, x, y);

        int count = 0;
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == 'X') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        char[][] grid = createGrid(inputList);
        int x = 0;
        int y = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    x = i;
                    y = j;
                    grid[i][j] = 'X';
                }
            }
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '#' || grid[i][j] == 'X') {
                    continue;
                }

                char[][] newGrid = new char[grid.length][grid[0].length];
                for (int k = 0; k < grid.length; k++) {
                    for (int l = 0; l < grid[k].length; l++) {
                        newGrid[k][l] = grid[k][l];
                    }
                }
                newGrid[i][j] = '#';

                if (!traverse(newGrid, x, y)) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static char[][] createGrid(List<String> inputList) {
        char[][] grid = new char[inputList.size()][inputList.get(0).length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = inputList.get(i).charAt(j);
            }
        }
        return grid;
    }

    /**
     * Simulate the traversal of the guard in the grid, marking the path with X
     * @param grid Initial grid
     * @param x Starting row of the guard
     * @param y Starting column of the guard
     * @return True if the guard exits the grid, false if the guard is stuck in a loop in the grid
     */
    public static boolean traverse(char[][] grid, int x, int y) {
        int direction = 1;
        int steps = 0;
        while (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            if (steps++ > 20000) {
                return false;
            }

            switch (direction) {
            case 1:
                if (x - 1 >= 0) {
                    if (grid[x - 1][y] == '#') {
                        direction++;
                    } else {
                        x--;
                        grid[x][y] = 'X';
                    }
                } else {
                    x--;
                }
                break;
            case 2:
                if (y + 1 < grid[x].length) {
                    if (grid[x][y + 1] == '#') {
                        direction++;
                    } else {
                        y++;
                        grid[x][y] = 'X';
                    }
                } else {
                    y++;
                }
                break;
            case 3:
                if (x + 1 < grid.length) {
                    if (grid[x + 1][y] == '#') {
                        direction++;
                    } else {
                        x++;
                        grid[x][y] = 'X';
                    }
                } else {
                    x++;
                }
                break;
            case 4:
                if (y - 1 >= 0) {
                    if (grid[x][y - 1] == '#') {
                        direction = 1;
                    } else {
                        y--;
                        grid[x][y] = 'X';
                    }
                } else {
                    y--;
                }
                break;
            }
        }

        return true;
    }
}
