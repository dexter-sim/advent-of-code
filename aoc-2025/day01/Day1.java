import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day01/day1-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        solve(inputList);
    }

    public static void solve(List<String> inputList) {
        List<Character> directions = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        for (String input : inputList) {
            directions.add(input.charAt(0));
            distances.add(Integer.parseInt(input.substring(1)));
        }

        int dial = 50;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < distances.size(); i++) {
            if (directions.get(i) == 'L') {
                if (dial == 0) {
                    count2--;
                }

                dial -=  distances.get(i);
                while (dial < 0) {
                    dial += 100;
                    count2++;
                }

                if (dial == 0) {
                    count2++;
                }
            } else {
                dial += distances.get(i);
                while (dial >= 100) {
                    dial -= 100;
                    count2++;
                }
            }

            if (dial == 0) {
                count1++;
            }
        }
        System.out.println(count1);
        System.out.println(count2);
    }
}
