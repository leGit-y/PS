import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // X + Y + Z = K --> X + Y = K - Z
        // 두개의 합 배열 만들기 (배열 크기: nC2 + N)
        int[] sum = new int[N * (N-1) / 2 + N];
        int idx = 0;
        for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                sum[idx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(sum);
        
        int max = -1;
        for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                int target = arr[j] - arr[i];
                if(Arrays.binarySearch(sum, target) >= 0){
                    max = Math.max(max, arr[j]);
                }
            }
        }

        bw.write(max + "\n");
        bw.flush();

    }
}
