import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] picks, arr;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        picks = new int[6];

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0) break;

            arr = new int[K];
            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            sb.append("\n");
        }

        System.out.print(sb);

    }

    private static void dfs(int idx, int depth){
        if(depth == 6){
            for(int pick: picks){
                sb.append(pick).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < K; i++) {
            picks[depth] = arr[i];
            dfs(i + 1, depth + 1);
        }

    }
}
