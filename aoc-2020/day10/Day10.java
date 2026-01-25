import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day10/day10-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<Integer> adapters = inputList.stream()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        int joltage = 0;
        int count1 = 0;
        int count3 = 0;
        for (int adapter : adapters) {
            if (adapter - joltage == 1) {
                count1++;
            }
            if (adapter - joltage == 3) {
                count3++;
            }
            joltage = adapter;
        }
        System.out.println(count1 * count3);
    }

    public static void part2(List<String> inputList) {
        List<Integer> adapters = inputList.stream()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        adapters.add(0, 0);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        System.out.println(helper(adapters, 0, new Long[adapters.size()]));
    }

    private static long helper(List<Integer> adapters, int index, Long[] memo) {
        if (memo[index] != null) {
            return memo[index];
        }

        if (index >= adapters.size() - 1) {
            return 1L;
        }

        int current = adapters.get(index);
        long ways = 0L;
        for (int i = index + 1; i < adapters.size(); i++) {
            int next = adapters.get(i);
            if (next - current <= 3) {
                ways += helper(adapters, i, memo);
            } else {
                break;
            }
        }
        memo[index] = ways;
        return ways;
    }
}
