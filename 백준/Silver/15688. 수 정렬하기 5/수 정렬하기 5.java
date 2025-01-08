import java.io.*;
import java.util.Arrays;

public class Main {
    static int[] arr;
    static int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[2*MAX+1];

        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(br.readLine()) + MAX;
            arr[idx]++;
        }

        for (int i = 0; i < MAX*2 + 1; i++) {
            int cnt = arr[i];
            if (cnt == 0) continue;
            int num = i - MAX;
            while(cnt > 0){
                bw.write(num + "\n");
                cnt--;
            }

        }
        bw.flush();
    }

}
