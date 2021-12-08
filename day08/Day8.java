import java.util.*;
import java.io.*;

public class Day8 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day8-input.txt"));
        int count = 0;
        while (scanner.hasNext()){
            String[] arr = scanner.nextLine().split(" \\| ")[1].split(" ");
            for (String s : arr){
                if (s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7){
                    count++;
                }
            }
        }
        System.out.println(count);
        scanner.close();
    }

    public static void part2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day8-input.txt"));
        int sum = 0;
        while (scanner.hasNext()){
            String[] curr = scanner.nextLine().split(" \\| ");
            String[] left = curr[0].split(" ");
            String[] right = curr[1].split(" ");

            String[] actual = new String[10];
            List<String> fives = new ArrayList<>();     // {2, 3, 5}
            List<String> sixes = new ArrayList<>();     // {0, 6, 9}
            for (String s : left){
                if (s.length() == 2){
                    actual[1] = s;
                } else if (s.length() == 3){
                    actual[7] = s;
                } else if (s.length() == 4){
                    actual[4] = s;
                } else if (s.length() == 7){
                    actual[8] = s;
                } else if (s.length() == 5){
                    fives.add(s);
                } else {
                    sixes.add(s);
                }
            }

            boolean flag = true;
            String s = "";

            // To identify 6 using 1 as only 6 from {0, 6, 9} contains 1.
            for (int i = 0; i < sixes.size(); i++){
                s = sixes.get(i);
                flag = true;
                for (char ch : actual[1].toCharArray()){
                    if (!s.contains(Character.toString(ch))){
                        flag = false;
                        break;
                    }
                }
                if (!flag){
                    actual[6] = s;
                    sixes.remove(i);
                    break;
                }
            }

            // 2 remaining elements in sixes, 0 and 9.
            // To use 4 to check as 9 contains 4, 0 does not contain 4.
            s = sixes.get(0);
            flag = true;
            for (char ch : actual[4].toCharArray()){
                if (!s.contains(Character.toString(ch))){
                    flag = false;
                    break;
                }
            }
            if (flag){
                actual[9] = s;
                actual[0] = sixes.get(1);
            } else {
                actual[0] = s;
                actual[9] = sixes.get(1);
            }

            // To identify 5 using 6. Only 5 from {2, 3, 5} is fully contained within 6.
            for (int i = 0; i < fives.size(); i++){
                s = fives.get(i);
                flag = true;
                for (char ch : s.toCharArray()){
                    if (!actual[6].contains(Character.toString(ch))){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    actual[5] = s;
                    fives.remove(i);
                    break;
                }
            }

            // 2 remaining elements in fives, 2 and 3.
            // To use 1 to check as 3 contains 1, 2 does not contain 1.
            s = fives.get(0);
            flag = true;
            for (char ch : actual[1].toCharArray()){
                if (!s.contains(Character.toString(ch))){
                    flag = false;
                    break;
                }
            }
            if (flag){
                actual[3] = s;
                actual[2] = fives.get(1);
            } else {
                actual[2] = s;
                actual[3] = fives.get(1);
            }

            String res = "";
            for (String str : right){
                if (str.length() == 2){
                    res += "1";
                } else if (str.length() == 3){
                    res += "7";
                } else if (str.length() == 4){
                    res += "4";
                } else if (str.length() == 7){
                    res += "8";
                } else if (str.length() == 5){
                    flag = true;
                    for (char c : actual[2].toCharArray()){
                        if (!str.contains(Character.toString(c))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        res += "2";
                        continue;
                    }
                    flag = true;
                    for (char c : actual[3].toCharArray()){
                        if (!str.contains(Character.toString(c))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        res += "3";
                    } else {
                        res += "5";
                    }
                } else {
                    flag = true;
                    for (char c : actual[0].toCharArray()){
                        if (!str.contains(Character.toString(c))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        res += "0";
                        continue;
                    }
                    flag = true;
                    for (char c : actual[6].toCharArray()){
                        if (!str.contains(Character.toString(c))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        res += "6";
                    } else {
                        res += "9";
                    }
                }
            }
            sum += Integer.parseInt(res);
        }
        System.out.println(sum);
        scanner.close();
    }

}