import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dist;
    static final int INF = 200000000;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // init graph
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new LinkedList<>();
        }

        // input
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 양방향 그래프
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        // 반드시 거쳐야 하는 두 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());


        // init dist
        dist = new int[N+1];
        int result1 = 0;
        // dijkstra
        // 정점 1 -> v2 -> v1 -> N
        result1 += dijkstra(1, v2);
        result1 += dijkstra(v2, v1);
        result1 += dijkstra(v1, N);


        int result2 = 0;
        // 정점 1 -> v1 -> v2 -> N
        result2 += dijkstra(1, v1);
        result2 += dijkstra(v1, v2);
        result2 += dijkstra(v2, N);

        int answer = (result1 >= INF && result2 >= INF) ? -1 : Math.min(result1, result2);

        sb.append(answer);
        System.out.println(sb);


    }

    private static int dijkstra(int start, int end){
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.dist - o2.dist;
            }
        });
        
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.node] < cur.dist) continue;
            for (Edge next : graph[cur.node]) {
                int cost = dist[cur.node] + next.dist;
                if(cost < dist[next.node]){
                    dist[next.node] = cost;
                    pq.add(new Edge(next.node, cost));
                }
            }
        }

        return dist[end];

    }
    
    private static class Edge {
        int node;
        int dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

    }

    
}
