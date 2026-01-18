import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day11/day11-input.txt"));
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
                .map(input -> input.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();

        int seatsChanged;
        do {
            seatsChanged = 0;
            List<List<Character>> newGrid = grid.stream()
                    .map(ArrayList::new)
                    .collect(Collectors.toList());
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    char seat = grid.get(i).get(j);
                    if (seat == '.') {
                        newGrid.get(i).set(j, seat);
                        continue;
                    }

                    int adjacentOccupiedSeats = countAdjacentOccupiedSeats(grid, i, j);

                    if (adjacentOccupiedSeats == 0 && seat == 'L') {
                        newGrid.get(i).set(j, '#');
                        seatsChanged++;
                        continue;
                    }

                    if (adjacentOccupiedSeats >= 4 && seat == '#') {
                        newGrid.get(i).set(j, 'L');
                        seatsChanged++;
                        continue;
                    }

                    newGrid.get(i).set(j, seat);
                }
            }
            grid = newGrid;
        } while (seatsChanged > 0);

        long count = grid.stream()
                .flatMap(List::stream)
                .filter(c -> c == '#')
                .count();
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        List<List<Character>> grid = inputList.stream()
                .map(input -> input.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();

        int seatsChanged;
        do {
            seatsChanged = 0;
            List<List<Character>> newGrid = grid.stream()
                    .map(ArrayList::new)
                    .collect(Collectors.toList());
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    char seat = grid.get(i).get(j);
                    if (seat == '.') {
                        newGrid.get(i).set(j, seat);
                        continue;
                    }

                    int visibleOccupiedSeats = countVisibleOccupiedSeats(grid, i, j);

                    if (visibleOccupiedSeats == 0 && seat == 'L') {
                        newGrid.get(i).set(j, '#');
                        seatsChanged++;
                        continue;
                    }

                    if (visibleOccupiedSeats >= 5 && seat == '#') {
                        newGrid.get(i).set(j, 'L');
                        seatsChanged++;
                        continue;
                    }

                    newGrid.get(i).set(j, seat);
                }
            }
            grid = newGrid;
        } while (seatsChanged > 0);

        long count = grid.stream()
                .flatMap(List::stream)
                .filter(c -> c == '#')
                .count();
        System.out.println(count);
    }

    private static int countAdjacentOccupiedSeats(List<List<Character>> grid, int row, int column) {
        int count = 0;
        for (int i = Math.max(0, row - 1); i < Math.min(grid.size(), row + 2); i++) {
            for (int j = Math.max(0, column - 1); j < Math.min(grid.get(i).size(), column + 2); j++) {
                if (i == row && j == column) {
                    continue;
                }
                if (grid.get(i).get(j) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countVisibleOccupiedSeats(List<List<Character>> grid, int row, int column) {
        int count = 0;

        // North
        for (int i = row - 1; i >= 0; i--) {
            char seat = grid.get(i).get(column);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // South
        for (int i = row + 1; i < grid.size(); i++) {
            char seat = grid.get(i).get(column);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // West
        for (int j = column - 1; j >= 0; j--) {
            char seat = grid.get(row).get(j);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // East
        for (int j = column + 1; j < grid.get(row).size(); j++) {
            char seat = grid.get(row).get(j);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // Northwest
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            char seat = grid.get(i).get(j);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // Southeast
        for (int i = row + 1, j = column + 1; i < grid.size() && j < grid.get(i).size(); i++, j++) {
            char seat = grid.get(i).get(j);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // Southwest
        for (int i = row + 1, j = column - 1; i < grid.size() && j >= 0; i++, j--) {
            char seat = grid.get(i).get(j);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        // Northeast
        for (int i = row - 1, j = column + 1; i >= 0 && j < grid.get(i).size(); i--, j++) {
            char seat = grid.get(i).get(j);
            if (seat == '#') {
                count++;
                break;
            }
            if (seat == 'L') {
                break;
            }
        }

        return count;
    }
}
