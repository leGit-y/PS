import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] building;
    static int[] time;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            building = new List[N + 1];
            time = new int[N + 1];
            indegree = new int[N + 1];

            // input time
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                building[j] = new ArrayList<>();
                time[j] = Integer.parseInt(st.nextToken());
            }

            // input order
            for (int j = 1; j <= K; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                building[X].add(Y);
                indegree[Y]++;
            }

            int w = Integer.parseInt(br.readLine());
            int answer = solution(N, w);
            bw.write(answer + "\n");

        }
        bw.flush();
    }

    static private int solution(int N, int w){
        int[] dp = new int[N+1];
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0)
                q.offer(i);
        }
        for (int i = 1; i <= N; i++) {
            dp[i] = time[i];
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : building[cur]){
                dp[next] = Math.max(dp[next], dp[cur] + time[next]);
                indegree[next]--;
                if(indegree[next] == 0)
                    q.offer(next);
            }
        }
        return dp[w];
    }
}
