import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2024/day05/day5-input.txt"));
        List<String> pageOrderList = new ArrayList<>();
        List<String> updateList = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            pageOrderList.add(line);
        }

        while (scanner.hasNext()) {
            updateList.add(scanner.nextLine());
        }
        scanner.close();

        solve(pageOrderList, updateList);
    }

    public static void solve(List<String> pageOrderList, List<String> updateList) {
        Map<Integer, Set<Integer>> pageOrderMap = createPageOrderMap(pageOrderList);
        int sum1 = 0;
        int sum2 = 0;

        for (String update : updateList) {
            String[] split = update.split(",");
            Integer[] arr = new Integer[split.length];
            Integer[] sortedArr = new Integer[split.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
                sortedArr[i] = Integer.parseInt(split[i]);
            }

            Arrays.sort(sortedArr, (x, y) -> pageOrderMap.get(x).contains(y) ? -1 : 1);

            if (Arrays.equals(arr, sortedArr)) {
                sum1 += arr[arr.length / 2];
            } else {
                sum2 += sortedArr[sortedArr.length / 2];
            }
        }

        System.out.println("Part 1: " + sum1);
        System.out.println("Part 2: " + sum2);
    }

    public static Map<Integer, Set<Integer>> createPageOrderMap(List<String> pageOrderList) {
        Map<Integer, Set<Integer>> pageOrderMap = new HashMap<>();
        for (String pageOrder : pageOrderList) {
            String[] split = pageOrder.split("\\|");
            int left = Integer.parseInt(split[0]);
            int right = Integer.parseInt(split[1]);

            if (!pageOrderMap.containsKey(left)) {
                pageOrderMap.put(left, new HashSet<>());
            }

            if (!pageOrderMap.containsKey(right)) {
                pageOrderMap.put(right, new HashSet<>());
            }

            pageOrderMap.get(left).add(right);
        }
        return pageOrderMap;
    }
}