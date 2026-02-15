import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day05/day5-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        List<List<Long>> ranges = new ArrayList<>();
        List<Long> ingredients = new ArrayList<>();
        for (String input : inputList) {
            if (input.contains("-")) {
                ranges.add(Arrays.stream(input.split("-")).map(Long::parseLong).toList());
            } else if (!input.isEmpty()) {
                ingredients.add(Long.parseLong(input));
            }
        }

        int count = 0;
        for (Long ingredient : ingredients) {
            for (List<Long> range : ranges) {
                if (ingredient >= range.get(0) && ingredient <= range.get(1)) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        PriorityQueue<Long> starts = new PriorityQueue<>();
        PriorityQueue<Long> ends = new PriorityQueue<>();
        for (String input : inputList) {
            if (input.contains("-")) {
                String[] parts = input.split("-");
                starts.add(Long.parseLong(parts[0]));
                ends.add(Long.parseLong(parts[1]));
            }
        }
        long open = 1;
        long count = 0;
        long prev = starts.poll();
        while (!ends.isEmpty()) {
            if (!starts.isEmpty() && starts.peek() <= 1 + ends.peek()) {
                long curr = starts.poll();
                if (open == 0) {
                    prev = curr;
                }
                open++;
            } else {
                long curr = ends.poll();
                open--;
                if (open == 0) {
                    count += (curr - prev + 1);
                }
            }
        }
        System.out.println(count);
    }
}
