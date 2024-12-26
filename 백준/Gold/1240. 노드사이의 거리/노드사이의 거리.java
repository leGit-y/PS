import java.io.*;
import java.util.*;

public class Main {
    static List<Pair>[] graph;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // init graph
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b,c));
            graph[b].add(new Pair(a,c));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            bw.write(bfs(s,e)+"\n");
        }

        bw.flush();

    }
    private static class Pair{
        Integer first;
        Integer second;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }
    }

    private static int bfs(int start, int end){
        int[] visited = new int[N+1];

        Deque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(start,0));
        visited[start] = 1;

        int result = 0;
        while(!q.isEmpty()){
            Pair p = q.removeFirst();
            int x = p.first;
            int d = p.second;
            if(x == end){
                result = d;
                break;
            }

            for(Pair nx : graph[x]){
                int node = nx.first;
                int dist = nx.second;

                if(visited[node] == 1){
                    continue;
                }

                q.add(new Pair(node,dist+d));
                visited[node] = 1;

            }
        }

        return result;

    }
}

