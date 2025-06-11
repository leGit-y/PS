import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] num;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        num = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(num);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            long tmp = Long.parseLong(st.nextToken());
            bw.write(solution(tmp) + " ");
        }

        bw.flush();

    }

    private static long solution(long tmp) {

        int start = 0, end = N-1;

        while (start <= end) {
            int midIdx = (start + end) / 2;
            long midNum = num[midIdx];

            if (tmp < midNum) {
                end = midIdx - 1;

            }else if(tmp > midNum){
                start = midIdx + 1;
            }else{
                return 1;
            }

        }

        return 0;
    }
}
