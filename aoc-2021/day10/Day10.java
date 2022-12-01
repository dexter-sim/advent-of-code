import java.io.*;
import java.util.*;

public class Day10 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day10-input.txt"));
        int part1score = 0;
        ArrayList<Long> part2scores = new ArrayList<>();
        while (scanner.hasNext()){
            Stack<Character> stack = new Stack<>();
            boolean incompleteFlag = true;
            for (char c : scanner.nextLine().toCharArray()){
                if (c == '('){
                    stack.push(')');
                } else if (c == '['){
                    stack.push(']');
                } else if (c == '<'){
                    stack.push('>');
                } else if (c == '{'){
                    stack.push('}');
                } else if (stack.isEmpty()){
                    break;
                } else {
                    char temp = stack.pop();
                    if (temp != c){
                        incompleteFlag = false;
                        part1score += c == ')' ? 3 : c == ']' ? 57 : c == '}' ? 1197 : 25137;
                        break;
                    }
                }
            }
            if (!stack.isEmpty() && incompleteFlag){
                part2scores.add(completeString(stack));
            }
        }
        scanner.close();
        System.out.println(part1score);                                 // Part 1 Answer
        Collections.sort(part2scores);
        System.out.println(part2scores.get(part2scores.size() / 2));    // Part 2 Answer
    }

    public static long completeString(Stack<Character> stack){
        long score = 0;
        while (!stack.isEmpty()){
            score *= 5;
            char curr = stack.pop();
            score += curr == ')' ? 1 : curr == ']' ? 2 : curr == '}' ? 3 : 4;
        }
        return score;
    }
}