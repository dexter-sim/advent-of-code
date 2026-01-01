package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./aoc-2025/day03/day3-input.txt"));
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.nextLine());
        }
        scanner.close();

        part1(inputList);
        part2(inputList);
    }

    public static void part1(List<String> inputList) {
        int sum = 0;
        for (String input : inputList) {
            int first =  0;
            int second = 0;
            for (int i = 0; i < input.length(); i++) {
                int currentValue = Character.getNumericValue(input.charAt(i));
                if (currentValue > first && i < input.length() - 1) {
                    first = currentValue;
                    second = Character.getNumericValue(input.charAt(i+1));
                } else if (currentValue > second) {
                    second = currentValue;
                }
            }
            sum += first * 10 + second;
        }
        System.out.println(sum);
    }

    public static void part2(List<String> inputList) {
        int numberOfBatteries = 12;
        long sum = 0L;
        for (String input : inputList) {
            int[] arr = new int[numberOfBatteries];
            for (int i = 0; i < input.length(); i++) {
                int currentValue = Character.getNumericValue(input.charAt(i));
                for (int j = 0; j < arr.length; j++) {
                    if (currentValue > arr[j] && i < input.length() - arr.length + j + 1) {
                        arr[j] = currentValue;
                        for (int k = j + 1; k < arr.length; k++) {
                            arr[k] = 0;
                        }
                        break;
                    }
                }
            }

            for (int i = 0; i < arr.length; i++) {
                sum += (long) (arr[i] * Math.pow(10, arr.length - i - 1));
            }
        }
        System.out.println(sum);
    }
}
