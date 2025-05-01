import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }
        int[] ptr = new int[N];
        Arrays.fill(ptr, 0);

        int minIdx = 0;
        int answer = Integer.MAX_VALUE;
        while (ptr[minIdx] < M) {
            int min = arr[0][ptr[0]];
            int max = arr[0][ptr[0]];
            minIdx = 0;


            for (int i = 1; i < N; i++) {
                if (arr[i][ptr[i]] < min) {
                    min = arr[i][ptr[i]];
                    minIdx = i;
                }
                if(arr[i][ptr[i]] > max) {
                    max = arr[i][ptr[i]];

                }
            }

            int dif = max - min;
            if(dif < answer){
                answer = dif;
            }
            ++ptr[minIdx];
        }

        bw.write(answer + "\n");
        bw.flush();

    }


}
