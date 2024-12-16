import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day07/day7-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        solve(inputList);
    }

    public static void solve(List<String> inputList) {
        long sum1 = 0L;
        long sum2 = 0L;

        for (String input : inputList) {
            String[] split = input.split(": ");
            long target = Long.parseLong(split[0]);
            List<Long> list = new ArrayList<>();
            for (String s : split[1].split(" ")) {
                list.add(Long.parseLong(s));
            }

            if (helper(list, 1, list.get(0), target)) {
                sum1 += target;
            }

            if (helper2(list, 1, list.get(0), target)) {
                sum2 += target;
            }
        }

        System.out.println("Part 1: " + sum1);
        System.out.println("Part 2: " + sum2);
    }

    public static boolean helper(List<Long> list, int index, long currentValue, long target) {
        if (index >= list.size()) {
            return currentValue == target;
        }

        return helper(list, index + 1, currentValue * list.get(index), target)
                || helper(list, index + 1, currentValue + list.get(index), target);
    }

    public static boolean helper2(List<Long> list, int index, long currentValue, long target) {
        if (index >= list.size()) {
            return currentValue == target;
        }

        return helper2(list, index + 1, currentValue * list.get(index), target)
                || helper2(list, index + 1, currentValue + list.get(index), target)
                || helper2(list, index + 1, Long.parseLong(String.valueOf(currentValue) + list.get(index)), target);
    }
}
