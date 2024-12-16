import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day11 {
    public static Map<String, Long> memo = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day11/day11-input.txt"));
        String input = scanner.nextLine();
        scanner.close();

        part1(input);
        part2(input);
    }

    public static void part1(String input) {
        List<Long> stones = new ArrayList<>();
        for (String stone : input.split(" ")) {
            stones.add(Long.parseLong(stone));
        }

        for (int i = 0; i < 25; i++) {
            List<Long> newStones = new ArrayList<>();
            for (long stone : stones) {
                if (stone == 0L) {
                    newStones.add(1L);
                    continue;
                }

                String stoneString = String.valueOf(stone);
                if (stoneString.length() % 2 == 0) {
                    long left = Long.parseLong(stoneString.substring(0, stoneString.length() / 2));
                    long right = Long.parseLong(stoneString.substring(stoneString.length() / 2));
                    newStones.add(left);
                    newStones.add(right);
                } else {
                    newStones.add(stone * 2024L);
                }
            }
            stones = newStones;
        }

        System.out.println(stones.size());
    }

    public static void part2(String input) {
        long result = 0L;
        for (String stone : input.split(" ")) {
            result += helper(Long.parseLong(stone), 75L);
        }
        System.out.println(result);
    }

    public static long helper(long value, long blinks) {
        String key = value + "-" + blinks;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (blinks == 0) {
            return 1;
        }

        if (value == 0) {
            long result = helper(1L, blinks - 1);
            memo.put(key, result);
            return result;
        }

        String stringValue = String.valueOf(value);

        if (stringValue.length() % 2 == 0) {
            long left = Long.parseLong(stringValue.substring(0, stringValue.length() / 2));
            long right = Long.parseLong(stringValue.substring(stringValue.length() / 2));
            long result = helper(left, blinks - 1) + helper(right, blinks - 1);
            memo.put(key, result);
            return result;
        } else {
            long result = helper(value * 2024L, blinks - 1);
            memo.put(key, result);
            return result;
        }
    }
}
