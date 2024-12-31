import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int cycle = 0;

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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        // 사이클 갯수 만큼 -> 연결 끊는 연산
        // (트리 개수 - 1)번 만큼 -> 연결 하는 연산
        int group = 0;
        for (int i = 1; i <= N; i++) {
            if(parent[i] == i) 
                group++;
        }
        bw.write(cycle + group-1+"\n");
        bw.flush();

    }
    
    private static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y){
        int px = find(parent[x]);
        int py = find(parent[y]);

        if (px < py) {
            parent[py] = px;
        }else if (px > py){
            parent[px] = py;
        }else{
            cycle++;
        }
    }

}
