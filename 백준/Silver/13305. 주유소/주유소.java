import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long[] dist = new long[N-1];
        long[] oil = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            oil[i] = Long.parseLong(st.nextToken());
        }

        long min = oil[0];
        long result = oil[0] * dist[0];
        for (int i = 1; i < N-1; i++) {
            min = Math.min(min, oil[i]);
            result += min * dist[i];

        }

        bw.write(result + "\n");
        bw.flush();





    }
}
