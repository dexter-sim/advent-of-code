import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day02/day2-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        int count = 0;
        for (String input : inputList) {
            String[] split = input.split(":");
            char letter = split[0].charAt(split[0].length() - 1);

            int[] range = Arrays.stream(split[0].split(" ")[0].split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int min = range[0];
            int max = range[1];

            long letterCount = split[1].chars()
                    .filter(c -> c == letter)
                    .count();

            if (letterCount >= min && letterCount <= max) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        int count = 0;
        for (String input : inputList) {
            String[] split = input.split(":");
            char letter = split[0].charAt(split[0].length() - 1);

            int[] indexes = Arrays.stream(split[0].split(" ")[0].split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int leftIndex = indexes[0] - 1;
            int rightIndex = indexes[1] - 1;

            String password = split[1].trim();
            if (password.charAt(leftIndex) == letter && password.charAt(rightIndex) != letter) {
                count++;
            }
            if (password.charAt(leftIndex) != letter && password.charAt(rightIndex) == letter) {
                count++;
            }
        }
        System.out.println(count);
    }
}
