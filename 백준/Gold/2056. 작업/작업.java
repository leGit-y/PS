import java.io.*;
import java.util.StringTokenizer;

// 자바스럽게 잘 푼 풀이
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Job[] job = new Job[N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] preJob = new int[M];
            for (int j = 0; j < M; j++) {
                preJob[j] = Integer.parseInt(st.nextToken());
            }
            job[i] = new Job(t, preJob);
        }

        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            Job cur = job[i];
            int max = 0;
            for(int prev : cur.preJobs){
                max = Math.max(max, dp[prev]);
            }
            dp[i] = cur.time + max;
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }

    private static class Job{
        int time;
        int[] preJobs;

        public Job(int time, int[] preJobs) {
            this.time = time;
            this.preJobs = preJobs;
        }
    }
}
