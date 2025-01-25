import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static String[][] arr;
    static int answer = 0;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new String[5][5];
        visited = new boolean[25];
        result = new int[7];

        for (int i = 0; i < 5; i++) {
            arr[i] = br.readLine().split("");
        }

        dfs(0, 0, 0);

        System.out.println(answer);

    }

    private static void dfs(int depth, int idx, int cnt){

        if(depth == 7){
            if(cnt >= 4){
                answer += isAdjacent();
            }
            return;
        }

        for (int i = idx; i < 25; i++) {
            int r = i / 5;
            int c = i % 5;
            if(visited[i]) continue;

            visited[i] = true;
            result[depth] = i;
            if(arr[r][c].equals("S")) {
                dfs(depth + 1, i + 1, cnt + 1);
            }
            else{
                dfs(depth + 1, i + 1, cnt);
            }
            visited[i] = false;
        }
    }

    static private int isAdjacent(){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[] f_visited = new boolean[25];

        Deque<Integer> q = new ArrayDeque<>();
        q.add(result[0]);
        f_visited[result[0]] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int x = q.removeLast();
            int r = x / 5;
            int c = x % 5;

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                int ni = nr * 5 + nc;
                if(f_visited[ni]) continue;
                if(!visited[ni]) continue;
                f_visited[ni] = true;
                q.add(ni);
                cnt++;
                if(cnt == 7) return 1;
            }
        }

        return 0;

    }
}
