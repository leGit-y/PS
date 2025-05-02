import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_N = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        int [] cnt = new int [MAX_N];
        int max = 0, min = 1000001;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cnt[start]++;
            cnt[end]--;
            min = Math.min(min, start);
            max = Math.max(max, end);

        }

        // 누적합으로 만들기
        for (int i = min + 1; i <= max; i++) {
            cnt[i] += cnt[i - 1];
        }


        int A = 0, B = 0, totalLen = 0;
        while (B <= max) {

            if (totalLen < K) {
                totalLen += (cnt[B]);
                B++;
            }else if (totalLen > K){
                totalLen -= cnt[A];
                A++;
            }else{
                break;
            }
        }

        if(totalLen != K){
            bw.write("0 0");
        }else{
            bw.write(A + " " + B);
        }

        bw.flush();


    }
}
