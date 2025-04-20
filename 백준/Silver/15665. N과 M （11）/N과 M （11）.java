import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N,M;
    static int[] arr;
    static Set<String> answer;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        answer = new LinkedHashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs( "",0);

        for (String s : answer) {
            bw.write(s+"\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }

    private static void dfs(String result, int depth) throws IOException {
        if(depth == M){
            answer.add(result);
            return;
        }
        for (int i = 0; i < N; i++) {
            dfs(result+arr[i]+" ",depth + 1);
        }
    }

}
