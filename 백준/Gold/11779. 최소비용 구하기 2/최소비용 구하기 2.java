import java.io.*;
import java.util.*;

public class Main {
    private static List<int[]>[] graph;
    private static int N,M;
    private static int[] dist, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new LinkedList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new int[]{c, e});

        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        bw.write(dist[end]+"\n");

        ArrayList<Integer> routes = new ArrayList<>();
        int current = end;
        while(current != 0) {
            routes.add(current);
            current = parent[current];
        }
        bw.write(routes.size()+"\n");

        for(int i = routes.size() - 1; i >= 0; i--) {
            bw.write(routes.get(i) + " ");
        }
        bw.flush();
    }

    private static void dijkstra(int start){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        dist = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new int[]{0,start});
        dist[start] = 0;
        parent[start] = 0;

        while (!pq.isEmpty()) {
            int[] pop = pq.poll();
            int d = pop[0];
            int curNode = pop[1];

            if(dist[curNode] < d) continue;

            for (int[] next : graph[curNode]) {
                int next_d = next[0];
                int nextNode = next[1];
                int cost = dist[curNode] + next_d;

                if(cost < dist[nextNode]){
                    dist[nextNode] = cost;
                    parent[nextNode] = curNode;
                    pq.add(new int[]{cost, nextNode});

                }
            }

        }

    }
}
