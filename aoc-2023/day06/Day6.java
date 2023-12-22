import java.util.Arrays;

public class Day6 {

	public static void main(String[] args) {
		/**
		 * Input
		 *
		 * Time:        54     94     65     92
		 * Distance:   302   1476   1029   1404
		 */
		part1(new int[]{54, 94, 65, 92}, new int[]{302, 1476, 1029, 1404});
		part2(54946592L, 302147610291404L);
	}

	public static void part1(int[] times, int[] distances) {
		int[] ways = new int[times.length];
		for (int i = 0; i < times.length; i++) {
			for (int j = 0; j <= times[i]; j++) {
				int distance = j * (times[i] - j);
				if (distance > distances[i]) {
					ways[i]++;
				}
			}
		}
		int result = Arrays.stream(ways).reduce(1, (x, y) -> x * y);
		System.out.println(result);
	}

	public static void part2(long time, long distance) {
		long ways = 0L;
		for (int j = 0; j <= time; j++) {
			if (j * (time - j) > distance) {
				ways++;
			}
		}
		System.out.println(ways);
	}

}