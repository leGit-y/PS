import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static int N;
    private static String[][] arr;
    private static String[][] arr_copy;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new String[N][N];
        arr_copy = new String[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split("");
            arr_copy[i] = arr[i].clone();
        }

        sb.append(Solution(false)+" ");
        sb.append(Solution(true));

        System.out.println(sb);

    }

    private static int Solution(boolean isSick){
        int[][] visited = new int[N][N];
        int cnt = 0;

        if(isSick) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 1) continue;
                    bfs(true, arr, visited, i, j);
                    cnt++;
                }
            }
            return cnt;
        }

        // 아닌 경우
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j] == 1) continue;
                bfs(false, arr_copy, visited,i,j);
                cnt++;
            }
        }

        return cnt;
    }

    private static void bfs(boolean isSick, String[][] arr, int[][] visited, int x, int y){
        boolean flag = false;
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            Point now = q.removeFirst();
            if (arr[now.x][now.y].equals("R") || arr[now.x][now.y].equals("G")) {
                flag = true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny] == 1) continue;
                if(isSick) {
                    if (flag && (arr[nx][ny].equals("R") || arr[nx][ny].equals("G"))
                            || (!flag && arr[nx][ny].equals("B"))) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = 1;
                    }
                }else{
                    if(!arr[nx][ny].equals(arr[now.x][now.y])) continue;
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = 1;
                }

            }

        }

    }

    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
