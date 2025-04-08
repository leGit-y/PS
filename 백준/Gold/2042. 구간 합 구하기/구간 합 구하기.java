import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int OFFSET;
    static long[] tree;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // init()
        init(N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                // update()
                update(b - 1, c);

            }
             else if (a == 2) {
                int c = Integer.parseInt(st.nextToken());
                // query()
                long answer = query(b - 1, c - 1);
                bw.write(answer + "\n");
            }
        }

        bw.flush();

    }

    private static void init(int N){
        for (OFFSET = 1; OFFSET <= N; OFFSET *= 2);
        tree = new long[OFFSET * 2];

        int i;
        for (i = 0; i < N; i++) {
            tree[i + OFFSET] = arr[i];
        }
        for (; i < OFFSET; i++) {
            tree[i + OFFSET] = 0;
        }


        for (i = OFFSET - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }


    private static void update(int i, long num) {
        i += OFFSET;
        tree[i] = num;

        while ((i /= 2) > 0) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }

    }

    private static long query(int s, int e) {
        long result = 0;
        s += OFFSET;
        e += OFFSET;

        while (s <= e) {
            if (s % 2 == 1) {
                result += tree[s];
                s++;
            }
            if (e % 2 == 0) {
                result += tree[e];
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return result;

    }

}
