import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int end;
    int weight;

    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }

}

public class Main {
    static int N, E;
    static ArrayList<ArrayList<Node>> a; 
    static int[] dist; 
    static final int INF = 200000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        a = new ArrayList<>();
        dist = new int[N + 1];

        Arrays.fill(dist, INF);

        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            a.get(start).add(new Node(end, weight));
            a.get(end).add(new Node(start, weight));
        }

        // 반드시 거쳐야 하는 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N으로 가는 경우
        int res1 = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, N);

        // 1 -> v2 -> v1 -> N으로 가는 경우
        int res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, N);

        int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int start, int end) {
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;
            if (dist[cur] < curNode.weight) continue;

            for (Node node : a.get(cur)) {
                int cost = dist[cur] + node.weight;
                if (dist[node.end] > cost) {
                    dist[node.end] = cost;
                    pq.add(new Node(node.end, dist[node.end]));
                }
            }

        }

        return dist[end];
    }
}


