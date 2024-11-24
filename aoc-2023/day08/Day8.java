import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day8 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day8-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        String instructions = inputList.get(0);
        Map<String, String> map = new HashMap<>();
        List<String> positions = new ArrayList<>();
        for (int i = 2; i < inputList.size(); i++) {
            String s = inputList.get(i);
            String key = s.substring(0, 3);
            String left = s.substring(s.indexOf('(') + 1, s.indexOf(','));
            String right = s.substring(s.indexOf(',') + 2, s.length() - 1);
            map.put(key, left + "-" + right);
            if (key.charAt(2) == 'A') {
                positions.add(key);
            }
        }

        // Part 1
        System.out.println(numberOfSteps("AAA", map, instructions, true));

        // Part 2
        List<Integer> counts = new ArrayList<>();
        for (String position : positions) {
            counts.add(numberOfSteps(position, map, instructions, false));
        }
        System.out.println(lcm(counts));
    }

    public static int numberOfSteps(String position, Map<String, String> map, String instructions, boolean part1) {
        int count = 0;
        int index = 0;
        while (part1 ? !position.equals("ZZZ") : position.charAt(2) != 'Z') {
            if (instructions.charAt(index) == 'L') {
                position = map.get(position).substring(0, 3);
            } else {
                position = map.get(position).substring(4);
            }
            index++;
            index %= instructions.length();
            count++;
        }
        return count;
    }

    private static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private static long lcm(List<Integer> arr) {
        long lcm = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            long num1 = lcm;
            long num2 = arr.get(i);
            long gcd_val = gcd(num1, num2);
            lcm = (lcm * arr.get(i)) / gcd_val;
        }
        return lcm;
    }

}