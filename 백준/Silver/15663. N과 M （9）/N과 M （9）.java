import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    static Set<String> answer = new LinkedHashSet<>();
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

        answer.stream().forEach(s -> sb.append(s).append("\n"));
        System.out.println(sb);

    }

    private static void dfs(String str, int depth){
        if(depth == M){
            answer.add(str);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(str + arr[i] + " ", depth + 1);
            visited[i] = false;
        }

    }
}
