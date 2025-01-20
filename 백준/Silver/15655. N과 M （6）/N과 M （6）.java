import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb;

	static int N, M;
	static int[] numbers, picks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		numbers = new int[N];
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(numbers);
		picks = new int[M];
		combination(0, 0);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void combination(int depth, int start) {
		if (depth == M) {
			for (int pick : picks)
				sb.append(pick).append(" ");

			sb.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {
			picks[depth] = numbers[i];
			combination(depth + 1, i + 1);
		}
	}
}
