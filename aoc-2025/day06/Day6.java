import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day06/day6-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<Character> ops = Arrays.stream(inputList.get(inputList.size() - 1).split("\\s+"))
                .map(s -> s.charAt(0))
                .toList();

        List<List<Integer>> rows = new ArrayList<>();
        for (int i = 0; i < inputList.size() - 1; i++) {
            rows.add(Arrays.stream(inputList.get(i).trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList());
        }

        long sum = 0L;
        for (int i = 0; i < rows.get(0).size(); i++) {
            long initial = ops.get(i) == '+' ? 0L : 1L;
            for (List<Integer> row : rows) {
                if (ops.get(i) == '+') {
                    initial += row.get(i);
                } else {
                    initial *= row.get(i);
                }
            }
            sum += initial;
        }
        System.out.println(sum);
    }

    public static void part2(List<String> inputList) {
        // Pad every line with whitespace at the end to make them same length
        // and an additional whitespace character at the end of every line
        int maxLength = inputList.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        inputList.replaceAll(s -> s + " ".repeat(Math.max(0, maxLength + 1 - s.length())));

        String ops = inputList.get(inputList.size() - 1);
        long sum = 0L;
        long current = 0L;
        char currentOp = '+';
        for (int i = 0; i < ops.length(); i++) {
            if (ops.charAt(i) == '+' || ops.charAt(i) == '*') {
                currentOp = ops.charAt(i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < inputList.size() - 1; j++) {
                    if (inputList.get(j).charAt(i) != ' ') {
                        sb.append(inputList.get(j).charAt(i));
                    }
                }
                current = Long.parseLong(sb.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < inputList.size() - 1; j++) {
                    if (inputList.get(j).charAt(i) != ' ') {
                        sb.append(inputList.get(j).charAt(i));
                    }
                }

                if (sb.isEmpty()) {
                    sum += current;
                } else if (currentOp == '+') {
                    current += Long.parseLong(sb.toString());
                } else {
                    current *= Long.parseLong(sb.toString());
                }
            }
        }

        System.out.println(sum);
    }

}
