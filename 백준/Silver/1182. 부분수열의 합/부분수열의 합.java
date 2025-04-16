import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N,S;
    private static int[] arr;
    private static boolean[] visited;
    private static int answer  = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        if(S == 0) --answer;
        
        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();

    }

    private static void dfs(int idx, int sum){
        if(sum == S){
            answer++;
        }
        for (int i = idx; i < N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            dfs(i+1 , sum + arr[i]);
            visited[i] = false;
        }
    }
}
