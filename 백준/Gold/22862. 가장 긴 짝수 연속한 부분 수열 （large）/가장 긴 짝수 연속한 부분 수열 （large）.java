import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxLen = 0, cnt = 0, s = 0, e = 0;
        while(e < N){
            if(cnt <= K){
                if(arr[e] % 2 != 0){
                    cnt++;
                }
                e++;
            }
            else{
                if(arr[s] % 2 != 0){
                    cnt--;
                }
                s++;
            }

            maxLen = Math.max(maxLen, e - s - cnt);

        }

        bw.write(maxLen + "\n");
        bw.flush();


    }
}
