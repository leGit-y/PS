import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {-1,-2,-2,-1,1,2,2,1};
    private static int[] dy = {-2,-1,1,2,2,1,-1,-2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[] x = new int[2];
        int[] y = new int[2];
        for (int t = 0; t < T; t++) {
            int l = Integer.parseInt(br.readLine());
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }

            int answer = bfs(x, y, l);
            bw.write((answer-1) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }

    private static int bfs(int[] x, int[] y, int l) {
        int[][] visited = new int[l][l];
        int sx = x[0], sy = y[0];
        int ex = x[1], ey = y[1];

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = 1;

        while (!q.isEmpty()) {
            int[] now = q.removeFirst();
            int xx = now[0];
            int yy = now[1];

            if(xx == ex && yy == ey)
                return visited[xx][yy];

            for (int i = 0; i < dx.length; i++) {
                int nx = xx + dx[i];
                int ny = yy + dy[i];

                if (nx < 0 || nx >= l || ny < 0 || ny >= l) continue;
                if (visited[nx][ny] != 0) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = visited[xx][yy] + 1;

            }
        }

        return -1;


    }
}
