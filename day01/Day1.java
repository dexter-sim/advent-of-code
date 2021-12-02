import java.io.*;
import java.util.*;

public class Day1 {

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    public static void part1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day1-inputs.txt"));
        String line = br.readLine();
        int prev = Integer.parseInt(line);
        int count = 0;
        while (line != null){
            int curr = Integer.parseInt(line);
            if (curr > prev){
                count++;
            }
            prev = curr;
            line = br.readLine();
        }
        System.out.println(count);
    }

    public static void part2() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("day1-inputs.txt"));
        String s = reader.readLine();
        while (s != null){
            list.add(Integer.parseInt((s)));
            s = reader.readLine();
        }
        int count = 0;
        for (int i = 3; i < list.size(); i++){
            int prev = list.get(i - 3) + list.get(i - 2) + list.get(i - 1);
            int curr = list.get(i - 2) + list.get(i - 1) + list.get(i);
            if (curr > prev){
                count++;
            }
        }
        System.out.println(count);
    }

}
