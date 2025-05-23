import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int x = Integer.parseInt(br.readLine());
        int s = 0;
        int e = N-1;

        int answer = 0;
        while(s < e){
            int num = arr[s] + arr[e];

            if(num == x){
                answer++;
                s++;
                e--;
            }

            else if(num < x)
                s++;

            else if(num > x)
                e--;
        }

        bw.write(answer + "\n");
        bw.flush();

    }
}
