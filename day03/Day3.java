import java.util.*;
import java.io.*;

public class Day3 {

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        File file = new File("day3-input.txt");
        Scanner scanner = new Scanner(file);
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNext()){
            count++;
            String curr = scanner.nextLine();
            if (list.size() < curr.length()){
                for (int i = 0; i < curr.length(); i++){
                    list.add(0);
                }
            }
            for (int i = 0; i < curr.length(); i++){
                if (curr.charAt(i) == '1'){
                    list.set(i, list.get(i) + 1);
                }
            }
        }
        String gamma = "";
        String epsilon = "";
        for (Integer num : list) {
            if (num > count / 2) {
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }
        }
        System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));
        scanner.close();
    }

    public static void part2() throws FileNotFoundException {
        File file = new File("day3-input.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        while (scanner.hasNext()){
            String curr = scanner.nextLine();
            list1.add(curr);
            list2.add(curr);
        }
        String oxygen = findMore(list1, 0);
        String co2 = findLess(list2, 0);
        System.out.println(Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2));
        scanner.close();
    }

    public static String findMore(ArrayList<String> list, int index){
        if (list.size() == 1){
            return list.get(0);
        } else {
            int ones = 0;
            int zeros = 0;
            for (String s : list) {
                if (s.charAt(index) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
            if (ones >= zeros){
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).charAt(index) == '0') {
                        list.remove(i--);
                    }
                }
            } else {
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).charAt(index) == '1') {
                        list.remove(i--);
                    }
                }
            }
            return findMore(list, index + 1);
        }
    }

    public static String findLess(ArrayList<String> list, int index){
        if (list.size() == 1){
            return list.get(0);
        } else {
            int ones = 0;
            int zeros = 0;
            for (String s : list) {
                if (s.charAt(index) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
            if (zeros <= ones){
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).charAt(index) == '1') {
                        list.remove(i--);
                    }
                }
            } else {
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).charAt(index) == '0') {
                        list.remove(i--);
                    }
                }
            }
            return findLess(list, index + 1);
        }
    }
}