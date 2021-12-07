import java.util.*;
import java.io.*;

public class Day7 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day7-input.txt"));
        String[] temp = scanner.nextLine().split(",");
        int[] arr = new int[temp.length];
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(temp[i]);
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        int fuel1 = Integer.MAX_VALUE;
        int fuel2 = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++){
            int total1 = 0;
            int total2 = 0;
            for (int num : arr){
                total1 += Math.abs(num - i);
                total2 += (Math.abs(num - i) + 1) * Math.abs(num - i) / 2;
            }
            fuel1 = Math.min(total1, fuel1);
            fuel2 = Math.min(total2, fuel2);
        }
        System.out.println(fuel1);  // Part 1 Answer
        System.out.println(fuel2);  // Part 2 Answer
        scanner.close();
    }

}