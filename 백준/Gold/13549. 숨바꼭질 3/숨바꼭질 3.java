import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_N = 100000;
    static final int INF = Integer.MAX_VALUE;
    static int[] distance;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        distance = new int[MAX_N + 1];
        dijkstra(N);

        bw.write(distance[K] + "\n");
        bw.flush();
    }


    private static void dijkstra(int start){
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.d - o2.d);
        Arrays.fill(distance, INF);

        q.add(new Node(start,0));
        distance[start] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if(distance[now.n] < now.d) continue;

            int[] move = new int[]{now.n+1, now.n-1, now.n*2};
            for (int i = 0; i < 3; i++) {
                int next = move[i];
                if(!isInRange(next)) continue;

                // 순간이동하는 경우
                int cost = now.d;
                // 걷는 경우
                if(i!=2){
                    cost = now.d + 1;
                }

                if(cost < distance[next]){
                    distance[next] = cost;
                    q.add(new Node(next,cost));
                }
            }
        }
    }

    private static boolean isInRange(int num){
        if(num < 0 || num > 100000)
            return false;

        return true;
    }

    private static class Node{
        int n;
        int d;

        public Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
}
