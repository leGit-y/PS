import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Pair>[] graph;
    static int V;
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        // init graph
        graph = new List[V+1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Pair(v, w));

        }
        int[] dist = dijkstra(K);
        for (int i = 1; i <= V; i++) {
            int answer = dist[i];
            if(answer == INF){
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(answer).append("\n");
        }
        
        System.out.println(sb);

    }

    private static int[] dijkstra(int start){
        int[] dist = new int[V+1];
        for (int i = 1; i <= V; i++) {
           dist[i] = INF;
        }
        dist[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (dist[cur.dest] < cur.weight) continue;
            for(Pair next: graph[cur.dest]){
                int cost = cur.weight + next.weight;
                if (cost < dist[next.dest]) {
                    dist[next.dest] = cost;
                    pq.add(new Pair(next.dest, cost));
                }

            }
        }
        return dist;
    }

    private static class Pair implements Comparable<Pair>{
        int dest;
        int weight;

        public Pair(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }
}
