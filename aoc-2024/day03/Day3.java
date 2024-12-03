import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day03/day3-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        solve(String.join("", inputList));
    }

    public static void solve(String input) {
        int sum1 = 0;
        int sum2 = 0;
        boolean enabled = true;

        for (int i = 0; i < input.length(); i++) {
            if (input.startsWith("don't()", i)) {
                enabled = false;
            }

            if (input.startsWith("do()", i)) {
                enabled = true;
            }

            if (input.startsWith("mul(", i)) {
                if (input.indexOf(')', i + 4) != -1) {
                    String s = input.substring(i + 4, input.indexOf(')', i + 4));
                    if (s.chars().filter(ch -> ch == ',').count() != 1) {
                        continue;
                    }

                    String[] values = s.split(",");
                    if (values.length != 2) {
                        continue;
                    }

                    if (values[0].matches("-?\\d+") && values[1].matches("-?\\d+")) {
                        sum1 += Integer.parseInt(values[0]) * Integer.parseInt(values[1]);
                        if (enabled) {
                            sum2 += Integer.parseInt(values[0]) * Integer.parseInt(values[1]);
                        }
                    }
                }
            }
        }

        System.out.println("Part 1: " + sum1);
        System.out.println("Part 2: " + sum2);
    }
}
