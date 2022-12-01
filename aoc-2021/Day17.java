public class Day17 {

    public static void main(String[] args) {
        int max_height = 0;
        int count = 0;
        for (int i = 1; i <= 202; i++) {
            for (int j = -110; j < 500; j++) {
                max_height = Math.max(max_height, helper(i, j));
                if (helper(i, j) != Integer.MIN_VALUE) {
                    count++;
                }
            }
        }
        System.out.println(max_height);     // Part 1 Answer
        System.out.println(count);          // Part 2 Answer
    }

    public static int helper(int i, int j) {
        int min_x = 156;
        int max_x = 202;
        int min_y = -110;
        int max_y = -69;
        int pos_x = 0;
        int pos_y = 0;
        int height = 0;
        boolean hit = false;
        while (pos_x <= max_x) {
            if (i == 0 && pos_x < min_x) {
                break;
            } else if (i == 0 && pos_y < min_y){
                break;
            }
            pos_x += i;
            pos_y += j;
            height = Math.max(height, pos_y);
            if (i > 0) {
                i--;
            } else if (i < 0) {
                i++;
            }
            j--;
            if (pos_x <= max_x && pos_x >= min_x && pos_y <= max_y && pos_y >= min_y) {
                hit = true;
                break;
            }
        }
        return hit ? height : Integer.MIN_VALUE;
    }
}
