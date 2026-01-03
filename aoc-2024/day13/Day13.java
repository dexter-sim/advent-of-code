import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day13/day13-input.txt"));
        List<String> buttonAs = new ArrayList<>();
        List<String> buttonBs = new ArrayList<>();
        List<String> prizes = new ArrayList<>();
        while (scanner.hasNext()) {
            buttonAs.add(scanner.nextLine());
            buttonBs.add(scanner.nextLine());
            prizes.add(scanner.nextLine());
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }

        part1(buttonAs, buttonBs, prizes);
        part2(buttonAs, buttonBs, prizes);
    }

    public static void part1(List<String> buttonAs, List<String> buttonBs, List<String> prizes) {
        long totalTokens = 0L;

        for (int i = 0; i < prizes.size(); i++) {
            // a = Button A (X), b = Button B (X), c = Prize (X)
            long a = Long.parseLong(buttonAs.get(i).split(",")[0].replaceAll("[^0-9]", ""));
            long b = Long.parseLong(buttonBs.get(i).split(",")[0].replaceAll("[^0-9]", ""));
            long c = Long.parseLong(prizes.get(i).split(",")[0].replaceAll("[^0-9]", ""));

            // d = Button A (Y), e = Button B (Y), f = Prize (Y)
            long d = Long.parseLong(buttonAs.get(i).split(",")[1].replaceAll("[^0-9]", ""));
            long e = Long.parseLong(buttonBs.get(i).split(",")[1].replaceAll("[^0-9]", ""));
            long f = Long.parseLong(prizes.get(i).split(",")[1].replaceAll("[^0-9]", ""));

            // Determinant of the matrix [[a, b], [d, e]]
            long det = a * e - b * d;

            if (det == 0) {
                long minTokens = Long.MAX_VALUE;
                for (long xIdx = 0; xIdx <= 100; xIdx++) {
                    for (long yIdx = 0; yIdx <= 100; yIdx++) {
                        if (xIdx * a + yIdx * b == c && xIdx * d + yIdx * e == f) {
                            minTokens = Math.min(minTokens, xIdx * 3 + yIdx);
                        }
                    }
                }
                if (minTokens != Long.MAX_VALUE) {
                    totalTokens += minTokens;
                }
            } else {
                /*
                   Cramer's Rule:
                   x = detX / det where detX is matrix with prize column [[c, b], [f, e]]
                   y = detY / det where detY is matrix with prize column [[a, c], [d, f]]
                */
                long detX = c * e - f * b;
                long detY = a * f - d * c;

                if (detX % det == 0 && detY % det == 0) {
                    long x = detX / det;
                    long y = detY / det;

                    // Check boundaries for Part 1
                    if (x >= 0 && y >= 0 && x <= 100 && y <= 100) {
                        totalTokens += (x * 3 + y);
                    }
                }
            }
        }
        System.out.println(totalTokens);

    }

    public static void part2(List<String> buttonAs, List<String> buttonBs, List<String> prizes) {
        long totalTokens = 0L;
        long offset = 10000000000000L;

        for (int i = 0; i < prizes.size(); i++) {
            // Equation 1: ax + by = c
            long a = Long.parseLong(buttonAs.get(i).split(",")[0].replaceAll("[^0-9]", ""));
            long b = Long.parseLong(buttonBs.get(i).split(",")[0].replaceAll("[^0-9]", ""));
            long c = Long.parseLong(prizes.get(i).split(",")[0].replaceAll("[^0-9]", "")) + offset;

            // Equation 2: dx + ey = f
            long d = Long.parseLong(buttonAs.get(i).split(",")[1].replaceAll("[^0-9]", ""));
            long e = Long.parseLong(buttonBs.get(i).split(",")[1].replaceAll("[^0-9]", ""));
            long f = Long.parseLong(prizes.get(i).split(",")[1].replaceAll("[^0-9]", "")) + offset;

            // Determinant of the coefficient matrix [[a, b], [d, e]]
            long det = a * e - b * d;

            // Cramer's Rule: detX is for variable x, detY is for variable y
            if (det != 0) {
                // Note: det is always zero for part 2
                long detX = c * e - f * b;
                long detY = a * f - d * c;

                // Check if solutions are integers (no remainder)
                if (detX % det == 0 && detY % det == 0) {
                    long x = detX / det;
                    long y = detY / det;

                    // Ensure the number of presses is non-negative
                    if (x >= 0 && y >= 0) {
                        totalTokens += (x * 3 + y);
                    }
                }
            }
        }
        System.out.println(totalTokens);
    }
}
