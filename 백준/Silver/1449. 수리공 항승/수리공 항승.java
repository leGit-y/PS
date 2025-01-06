import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] loc = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            loc[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(loc);

        double start = loc[0] - 0.5;
        double end = start + L;
        int answer = 1;
        
        for (int i = 1; i < N; i++) {
            if (end >= loc[i] + 0.5 &&  start <= loc[i] - 0.5){
                continue;
            }

            // 새 테이프 필요
            answer++;
            start = loc[i] - 0.5;
            end = start + L;
        }

        System.out.println(answer);

    }
}
