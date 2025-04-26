import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        arr = new LinkedList[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        int answer = solution(1);
        bw.write(answer+"\n");
        bw.flush();

    }

    static int solution(int start){
        boolean[] visited = new boolean[N+1];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.pop();

            for (Integer next : arr[cur]) {
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
                cnt++;
            }
        }

        return cnt;
    }
}
