import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day09/day9-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
    }

    public static void part1(List<String> inputList) {
        List<Long> numbers = inputList.stream().map(Long::parseLong).toList();
        for (int i = 25; i < numbers.size(); i++) {
            boolean isValid = false;
            for (int j = i - 25; j < i; j++) {
                for (int k = j + 1; k < i; k++) {
                    if (numbers.get(j) + numbers.get(k) == numbers.get(i)) {
                        isValid = true;
                        break;
                    }
                }
            }
            if (!isValid) {
                System.out.println(numbers.get(i));
                part2(numbers, numbers.get(i));
                return;
            }
        }
    }

    private static void part2(List<Long> numbers, long target) {
        long sum = numbers.get(0);
        int left = 0;
        int right = 0;
        while (sum != target) {
            if (sum < target) {
                sum += numbers.get(++right);
            } else {
                sum -= numbers.get(left++);
            }
        }

        long smallest = numbers.subList(left, right + 1).stream().min(Long::compare).orElseThrow();
        long largest = numbers.subList(left, right + 1).stream().max(Long::compare).orElseThrow();
        System.out.println(smallest + largest);
    }
}
