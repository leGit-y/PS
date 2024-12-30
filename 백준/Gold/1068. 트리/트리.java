import java.io.*;
import java.util.*;

public class Main {
    static int[] check;
    static int[] parent;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            parent[i] = a;
        }

        int d = Integer.parseInt(br.readLine());
        // 지우기
        delete(d);

        int[] candidate = new int[N];
        Arrays.fill(candidate,1);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if(parent[i] < 0)
                continue;
            candidate[parent[i]] = 0;
        }

        for (int i = 0; i < N; i++) {
            if(parent[i] == -2)
                continue;
            if(candidate[i] == 1){
                answer++;
            }
        }

        bw.write(answer + "\n");
        bw.flush();

    }

    private static void delete(int delNode){
        Deque<Integer> q = new ArrayDeque<>();
        q.add(delNode);
        parent[delNode] = -2;

        while(!q.isEmpty()){
            int pop = q.pop();
            for (int i = 0; i < N; i++) {
                if(parent[i] == pop) {
                    q.add(i);
                    parent[i] = -2;
                }
            }
        }

    }

}
