import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day1/day1-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<Integer> leftNumbers = new ArrayList<>();
        List<Integer> rightNumbers = new ArrayList<>();
        for (String input : inputList) {
            String[] split = input.split("\\s+");
            leftNumbers.add(Integer.parseInt(split[0]));
            rightNumbers.add(Integer.parseInt(split[1]));
        }
        Collections.sort(leftNumbers);
        Collections.sort(rightNumbers);

        int sum = 0;
        for (int i = 0; i < leftNumbers.size(); i++) {
            sum += Math.abs(rightNumbers.get(i) - leftNumbers.get(i));
        }
        System.out.println(sum);
    }

    public static void part2(List<String> inputList) {
        List<Integer> leftNumbers = new ArrayList<>();
        Map<Integer, Integer> rightNumbersMap = new HashMap<>();
        for (String input : inputList) {
            String[] split = input.split("\\s+");
            leftNumbers.add(Integer.parseInt(split[0]));
            rightNumbersMap.put(Integer.parseInt(split[1]), rightNumbersMap.getOrDefault(Integer.parseInt(split[1]), 0) + 1);
        }

        int similarityScore = 0;
        for (Integer leftNumber : leftNumbers) {
            similarityScore += leftNumber * rightNumbersMap.getOrDefault(leftNumber, 0);
        }
        System.out.println(similarityScore);
    }
}
