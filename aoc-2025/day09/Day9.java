import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day09/day9-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
    }

    public static void part1(List<String> inputList) {
        List<List<Integer>> coordinates = inputList.stream()
                .map(s -> Arrays.stream(s.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        long largestArea = 0L;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                largestArea = Math.max(largestArea, calculateArea(coordinates.get(i), coordinates.get(j)));
            }
        }
        System.out.println(largestArea);
    }

    public static long calculateArea(List<Integer> firstPoint, List<Integer> secondPoint) {
        long x1 = firstPoint.get(0);
        long y1 = firstPoint.get(1);
        long x2 = secondPoint.get(0);
        long y2 = secondPoint.get(1);
        return (Math.abs(x1 - x2) + 1) * (Math.abs(y1 - y2) + 1);
    }
}
