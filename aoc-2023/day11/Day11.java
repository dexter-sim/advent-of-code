import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day11 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2023/day11/day11-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<List<Character>> galaxy = createGalaxy(inputList);
        List<List<Character>> expandedGalaxy = expandGalaxy(galaxy);
        List<List<Integer>> coordinates = findCoordinates(expandedGalaxy);

        long sumOfPath = 0L;
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                sumOfPath += Math.abs(coordinates.get(i).get(0) - coordinates.get(j).get(0));
                sumOfPath += Math.abs(coordinates.get(i).get(1) - coordinates.get(j).get(1));
            }
        }

        System.out.println(sumOfPath);
    }

    public static void part2(List<String> inputList) {
        List<List<Character>> galaxy = createGalaxy(inputList);
        List<List<Integer>> coordinates = findCoordinates(galaxy);
        Set<Integer> emptyRowIndices = findEmptyRowsIndices(galaxy);
        Set<Integer> emptyColumnIndices = findEmptyColumnsIndices(galaxy);

        long sumOfPath = 0L;
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                int row1 = coordinates.get(i).get(0);
                int row2 = coordinates.get(j).get(0);
                int column1 = coordinates.get(i).get(1);
                int column2 = coordinates.get(j).get(1);

                for (int k = Math.min(row1, row2); k < Math.max(row1, row2); k++) {
                    if (emptyRowIndices.contains(k)) {
                        sumOfPath += 1000000L;
                    } else {
                        sumOfPath++;
                    }
                }

                for (int k = Math.min(column1, column2); k < Math.max(column1, column2); k++) {
                    if (emptyColumnIndices.contains(k)) {
                        sumOfPath += 1000000L;
                    } else {
                        sumOfPath++;
                    }
                }
            }
        }

        System.out.println(sumOfPath);
    }

    public static List<List<Character>> createGalaxy(List<String> inputList) {
        List<List<Character>> galaxy = new ArrayList<>();
        for (String row : inputList) {
            List<Character> list = new ArrayList<>();
            for (char c : row.toCharArray()) {
                list.add(c);
            }
            galaxy.add(list);
        }
        return galaxy;
    }

    public static List<List<Character>> expandGalaxy(List<List<Character>> galaxy) {
        List<List<Character>> expandedGalaxy = new ArrayList<>();
        for (int i = 0; i < galaxy.size(); i++) {
            List<Character> row = new ArrayList<>(galaxy.get(i));
            expandedGalaxy.add(row);
            if (row.contains('#')) {
                continue;
            }
            expandedGalaxy.add(new ArrayList<>(row));
        }

        for (int j = 0; j < expandedGalaxy.get(0).size(); j++) {
            boolean containsGalaxy = false;
            for (int i = 0; i < expandedGalaxy.size(); i++) {
                if (expandedGalaxy.get(i).get(j) == '#') {
                    containsGalaxy = true;
                    break;
                }
            }

            if (containsGalaxy) {
                continue;
            }

            for (int i = 0; i < expandedGalaxy.size(); i++) {
                expandedGalaxy.get(i).add(j, '.');
            }

            j++;
        }

        return expandedGalaxy;
    }

    public static List<List<Integer>> findCoordinates(List<List<Character>> galaxy) {
        List<List<Integer>> coordinates = new ArrayList<>();
        for (int i = 0; i < galaxy.size(); i++) {
            for (int j = 0; j < galaxy.get(i).size(); j++) {
                if (galaxy.get(i).get(j) == '#') {
                    coordinates.add(new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return coordinates;
    }

    public static Set<Integer> findEmptyRowsIndices(List<List<Character>> galaxy) {
        Set<Integer> emptyRowIndices = new HashSet<>();
        for (int i = 0; i < galaxy.size(); i++) {
            if (galaxy.get(i).contains('#')) {
                continue;
            }
            emptyRowIndices.add(i);
        }
        return emptyRowIndices;
    }

    public static Set<Integer> findEmptyColumnsIndices(List<List<Character>> galaxy) {
        Set<Integer> emptyColumnIndices = new HashSet<>();
        for (int j = 0; j < galaxy.get(0).size(); j++) {
            boolean isEmpty = true;
            for (int i = 0; i < galaxy.size(); i++) {
                if (galaxy.get(i).get(j) == '#') {
                    isEmpty = false;
                    break;
                }
            }

            if (isEmpty) {
                emptyColumnIndices.add(j);
            }
        }
        return emptyColumnIndices;
    }
}
