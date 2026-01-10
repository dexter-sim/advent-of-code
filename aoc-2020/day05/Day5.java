import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2020/day05/day5-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        solve(inputList);
    }

    public static void solve(List<String> inputList) {
        int max = 0;
        Set<Integer> takenSeats = new HashSet<>();
        for (String input : inputList) {
            int row = binarySearch(input.substring(0, 7), 0, 127, 'F');
            int seat = binarySearch(input.substring(7), 0, 7, 'L');
            max = Math.max(max, row * 8 + seat);
            takenSeats.add(row * 8 + seat);
        }
        System.out.println("Part 1: " + max);

        for (int i = 1; i < 127; i++) {
            for (int j = 0; j < 8; j++) {
                int id = i * 8 + j;
                if (!takenSeats.contains(id) && takenSeats.contains(id - 1) && takenSeats.contains(id + 1)) {
                    System.out.println("Part 2: " + id);
                    break;
                }
            }
        }
    }

    public static int binarySearch(String boardingPass, int low, int high, char lowerKey) {
        for (int i = 0; i < boardingPass.length(); i++) {
            // midpoint rounded down
            int mid = low + (high - low) / 2;
            if (boardingPass.charAt(i) == lowerKey) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
