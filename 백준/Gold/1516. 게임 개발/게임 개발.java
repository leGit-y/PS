import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // input
        List<Integer>[] building = new List[N+1];
        for (int i = 1; i <= N; i++) {
            building[i] = new ArrayList<>();
        }
        int[] indegree = new int[N+1];
        int[] time = new int[N+1];
        for (int i = 1; i <= N; i++) {
            
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            while(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) break;
                building[num].add(i);
                indegree[i]++;
            }
        }

        // solution
        Deque<Integer> q = new ArrayDeque<>();
        int[] result = new int[N+1];
        for (int i = 1; i <= N; i++) {
            result[i] = time[i];
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for(int next: building[cur]){
                result[next] = Math.max(result[next], result[cur] + time[next]);
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                }
            }

        }
        
        // output
        for (int i = 1; i <= N; i++) {
            bw.write(result[i]+"\n");
        }
        bw.flush();

    }

}
