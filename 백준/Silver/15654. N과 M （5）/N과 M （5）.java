import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<String> answer = new ArrayList<>();
    static int N, M;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        visited = new boolean[N];

        dfs("", 0);

        for (int i = 0; i < answer.size(); i++) {
            bw.write(answer.get(i)+"\n");
        }
        bw.flush();

    }

    static private void dfs(String str, int depth){
       if(depth == M){
           answer.add(str);
           return;
       }

       for (int i = 0; i < N; i++) {
           if(visited[i]) continue;

           visited[i] = true;
           dfs(str + arr[i] + " " , depth + 1);
           visited[i] = false;
       }
    }
}
