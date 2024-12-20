import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> q = new ArrayDeque<>();
        // init q
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                q.addLast(q.removeFirst());
            }
            result[i] = q.removeLast();
        }

        // print
        bw.write("<");
        for (int i = 0; i < N-1; i++) {
           bw.write(result[i]+", ");
        }
        bw.write(result[N-1]+"");
        bw.write(">");
        bw.flush();
    }
}
