import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day02/day2-input.txt"));
        String input = scanner.nextLine();
        scanner.close();
        solve(input);
    }

    public static void solve(String input) {
        long sum1 = 0L;
        long sum2 = 0L;
        String[] arr = input.split(",");
        for (String range : arr) {
            long lower = Long.parseLong(range.substring(0, range.indexOf('-')));
            long upper = Long.parseLong(range.substring(range.indexOf('-') + 1));
            for (long id = lower; id <= upper; id++) {
                if (isInvalidId(Long.toString(id))) {
                    sum1 += id;
                }
                for (int i = 1; i <= Long.toString(id).length() / 2; i++) {
                    if (isInvalidId(Long.toString(id), i)) {
                        sum2 += id;
                        break;
                    }
                }
            }
        }
        System.out.println(sum1);
        System.out.println(sum2);
    }

    public static boolean isInvalidId(String id) {
        return id.length() % 2 == 0
                && id.substring(0, id.length() / 2).equals(id.substring(id.length() / 2));
    }

    public static boolean isInvalidId(String id, int substringLength) {
        if (id.length() % substringLength != 0) {
            return false;
        }

        String repeatedSubstring = id.substring(0, substringLength);
        for (int i = 0; i < id.length(); i += substringLength) {
            if (!repeatedSubstring.equals(id.substring(i, i + substringLength))) {
                return false;
            }
        }
        return true;
    }
}
