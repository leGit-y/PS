import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] tree;
    static int[][] dp;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // init tree
        tree = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 행: 현재 노드 얼리어답터인지 아닌지, 열: 현재 노드 번호,
        // 값: 현재 노드부터 리프까지의 얼리어답터 최소 수
        dp = new int[2][N + 1];

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 1);

        int answer = Math.min(dp[0][1], dp[1][1]);
        bw.write(answer + "\n");
        bw.flush();

    }

    private static void dfs(int cur, int parent){
        dp[0][cur] = 0;
        dp[1][cur] = 1;

        for(int child: tree[cur]){
            if(child == parent) continue;

            dfs(child, cur);
            dp[0][cur] += dp[1][child]; // 부모가 얼리x -> 자식 무조건 얼리여야
            dp[1][cur] += Math.min(dp[1][child], dp[0][child]); // 부모 얼리 o -> 자식 얼리 o /얼리 x

        }
    }
}
