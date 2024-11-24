import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day1 {
    public static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day1-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        long result = 0L;
        for (String s : inputList) {
            char left = '0';
            char right = '0';
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    left = s.charAt(i);
                    break;
                }
            }
            for (int i = s.length() - 1; i >= 0; i--) {
                if (Character.isDigit(s.charAt(i))) {
                    right = s.charAt(i);
                    break;
                }
            }
            result += Long.parseLong(Character.toString(left) + right);
        }
        System.out.println(result);
    }

    public static void part2(List<String> inputList) {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        List<String> modifiedList = new ArrayList<>();
        for (String s : inputList) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i + 3 <= s.length() && map.containsKey(s.substring(i, i + 3))) {
                    sb.append(map.get(s.substring(i, i + 3)));
                } else if (i + 4 <= s.length() && map.containsKey(s.substring(i, i + 4))) {
                    sb.append(map.get(s.substring(i, i + 4)));
                } else if (i + 5 <= s.length() && map.containsKey(s.substring(i, i + 5))) {
                    sb.append(map.get(s.substring(i, i + 5)));
                } else {
                    sb.append(s.charAt(i));
                }
            }
            modifiedList.add(sb.toString());
        }
        part1(modifiedList);
    }
}
