import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] tree;
    static int[] visited;
    static int max = 0;
    static int farthest = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        visited = new int[N+1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        // 루트 노드에서 dfs 돌려서 루트에서 가장 먼 farthest 노드 찾기
        dfs(1, 0);

        // farthest 노드에서 dfs 돌리면 트리를 가장 긴 경로로 순회하게 됨
        Arrays.fill(visited,0);
        dfs(farthest, 0);


        bw.write(max + "\n");
        bw.flush();
    }

    private static void dfs(int start, int dist){
        visited[start] = 1;
        if(max < dist){
            max = dist;
            farthest = start;
        }

        for(Node node: tree[start]){
            if (visited[node.e] == 0) {
                dfs(node.e,dist+node.w);
            }
        }

    }

    private static class Node{
        int e;
        int w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

    }
}
