import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int caseNum = 0;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                break;
            }

            // init graph
            List<Integer>[] graph = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);

            }

            int[] visited = new int[n+1];
            boolean[] finished = new boolean[n+1];
            int T = 0;

            for(int i=1;i<=n;i++){
                if(visited[i] != 0){
                    continue;
                }
                int result = bfs(graph, i, visited);
                if(result == -1){
                    continue;
                }
                T++;
            }

            caseNum++;

            bw.write("Case " + caseNum + ": ");
            if(T==0){
                bw.write("No trees.\n");
            }
            else if (T==1){
                bw.write("There is one tree.\n");
            }
            else{
                bw.write("A forest of " + T + " trees.\n");
            }

        }

        bw.flush();

    }


    static private int bfs(List<Integer>[] graph, int start, int[] visited){
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = 1;
        int[] parent = new int[n+1];
        parent[start] = -1;


        while(!q.isEmpty()){
            int x = q.pop();
            for(int nx: graph[x]){
                // 두 노드가 인접요소가 아닌데 방문한적 있는 경우
                // --> 사이클
                if(parent[nx] != x && parent[x] != nx && visited[nx] == 1){
                    return -1;
                }

                if(visited[nx] != 0){
                    continue;
                }

                visited[nx] = 1;
                parent[nx] = x;
                q.add(nx);

            }
        }
        return 0;
    }
}
