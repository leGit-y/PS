import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] rope = new int[N];
        for (int i = 0; i < N; i++) {
            rope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope);
        if(N == 1){
            System.out.println(rope[0]);
            return;
        }

        int k = 2;
        int weight = 0;
        for (int i = N - 2; i >= 0; i--) {
            weight = Math.max(weight, k * rope[i]);
            k++;
        }

        bw.write(weight+"\n");
        bw.flush();

    }
}
