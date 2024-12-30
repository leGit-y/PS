import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] tree;
    static int[] visited;
    static int answer = 0;
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


        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited,0);
            visited[i] =  1;
            dfs(i, 0);
        }

        bw.write(answer + "\n");
        bw.flush();
    }

    private static void dfs(int start, int dist){
        for(Node node: tree[start]){
            if (visited[node.e] == 0) {
                visited[node.e] = 1;
                dfs(node.e,dist+node.w);
            }
        }
        answer = Math.max(answer, dist);
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
