import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new List[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[N+1];
        int[] time = new int[N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            indegree[i] = M;
            time[i] = t;
            for (int j = 0; j < M; j++) {
                int prev = Integer.parseInt(st.nextToken());
                graph[prev].add(i);
            }
        }

        Deque<Integer> q = new ArrayDeque<>();
        int[] result = new int[N+1];
        for (int i = 1; i <= N; i++) {
            result[i] = time[i];
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int prev = q.poll();
            for(int next: graph[prev]){
                result[next] = Math.max(result[next], result[prev] + time[next]);
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                }
            }

        }

        int answer = 0;
        for(int i : result){
            answer = Math.max(answer, i);
        }

        bw.write(answer+"\n");
        bw.flush();


    }
}
