import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[N-1];
        for (int i = 1; i < N; i++) {
            diff[i - 1] = arr[i] - arr[i - 1];
        }

        Arrays.sort(diff);

        int answer = 0;
        for (int i = 0; i < N - K; i++) {
            answer += diff[i];
        }

        sb.append(answer);
        System.out.println(sb);


    }
}
