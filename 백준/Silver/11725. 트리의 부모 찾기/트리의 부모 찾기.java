import java.io.*;
import java.util.*;

// 트리: 무향그래프 + 순환X 그래프
public class Main {
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        visited = new int[N+1];
        List<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        order(graph);

        for (int i = 2; i <= N; i++) {
            bw.write(visited[i]+"\n");
        }

        bw.flush();

    }

    // 트리 순회
    public static void order(List<Integer>[] graph){

        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = 1;

        while(!q.isEmpty()){
            int x = q.pop();
            for (int i = 0; i < graph[x].size(); i++) {
                int nx = graph[x].get(i);
                if(visited[nx] != 0){
                    continue;
                }
                q.offer(nx);
                visited[nx] = x;

            }
        }

    }
}
