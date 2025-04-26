import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(a, b);
            } else if (cmd == 1) {
                if (isSameParent(a,b)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();
    }

    private static boolean isSameParent(int a, int b) {
        int x = find(parent[a]);
        int y = find(parent[b]);

        return x==y;
    }


    private static void union(int a, int b) {
        int x = find(parent[a]);
        int y = find(parent[b]);

        if (x < y) {
            parent[y] = x;
        } else if (x > y) {
            parent[x] = y;
        }
    }
    private static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
