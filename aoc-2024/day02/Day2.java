import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day02/day2-input.txt"));
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
            String[] split = input.split(" ");
            List<Integer> report = new ArrayList<>();
            for (String s : split) {
                report.add(Integer.parseInt(s));
            }

            if (checkSafe(report)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void part2(List<String> inputList) {
        int count = 0;
        for (String input : inputList) {
            String[] split = input.split(" ");
            List<Integer> report = new ArrayList<>();
            for (String s : split) {
                report.add(Integer.parseInt(s));
            }

            for (int i = 0; i < report.size(); i++) {
                List<Integer> newReport = new ArrayList<>(report);
                newReport.remove(i);
                if (checkSafe(newReport)) {
                    count++;
                    break;
                }
            }

        }
        System.out.println(count);
    }

    public static boolean checkSafe(List<Integer> report) {
        boolean ascending = true;
        boolean descending = true;

        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) - report.get(i - 1) < 1 || report.get(i) - report.get(i - 1) > 3) {
                ascending = false;
            }

            if (report.get(i - 1) - report.get(i) < 1 || report.get(i - 1) - report.get(i) > 3) {
                descending = false;
            }
        }

        return ascending || descending;
    }
}
