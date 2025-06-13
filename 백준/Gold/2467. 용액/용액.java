import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // sort
        Arrays.sort(arr);

        int start = 0, end = N - 1;
        int s1 = 0, s2 = N-1;
        long min = Long.MAX_VALUE;
        while (start < end) {
            long add = arr[start] + arr[end];

            if (Math.abs(add) < min) {
                min = Math.abs(add);
                s1 = start;
                s2 = end;
                if (add == 0) {
                    break;
                }
            }

            if(add < 0){
                start++;

            }else if (add > 0){
                end--;
            }

        }

        bw.write(arr[s1] + " " + arr[s2]);
        bw.flush();


    }
}
