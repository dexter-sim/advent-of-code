import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day06/day6-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        inputList.add("");
        int sum = 0;
        Set<Character> set = new HashSet<>();
        for (String input : inputList) {
            if (input.isEmpty()) {
                sum += set.size();
                set = new HashSet<>();
                continue;
            }

            for (char c : input.toCharArray()) {
                set.add(c);
            }
        }
        System.out.println(sum);
    }

    public static void part2(List<String> inputList) {
        inputList.add(0, "");
        inputList.add("");
        int sum = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < inputList.size(); i++) {
            String input = inputList.get(i);
            if (input.isEmpty()) {
                sum += set.size();
                set = new HashSet<>();
                continue;
            }

            if (inputList.get(i - 1).isEmpty()) {
                for (char c : input.toCharArray()) {
                    set.add(c);
                }
                continue;
            }

            Set<Character> subset = new HashSet<>();
            for (char c : input.toCharArray()) {
                if (set.contains(c)) {
                    subset.add(c);
                }
            }
            set = subset;
        }
        System.out.println(sum);
    }

}
