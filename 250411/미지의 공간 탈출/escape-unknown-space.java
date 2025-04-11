import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        int miji_ex = -1, miji_ey = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 4) {
                    miji_ex = i;
                    miji_ey = j;
                    board[i][j] = 0;
                }
            }
        }


        int[][][] wall = new int[5][M][M];

        int time_sx = -1, time_sy = -1;

        // k:0(동) , k:1(서), k:2(남), k:3(북), k:4(윗면)
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    wall[k][i][j] = Integer.parseInt(st.nextToken());
                    // 시간의 벽 윗면에 있는 타임머신 위치 찾기
                    if (k == 4 && wall[k][i][j] == 2) {
                        time_sx = i;
                        time_sy = j;
                    }
                }
            }
        }

        Deque<StrangeTime> q = new ArrayDeque<>();

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            q.add(new StrangeTime(r, c, d, v));

        }
        int[] timeBase = findTimeBase(board);
        int time_bx = timeBase[0], time_by = timeBase[1];
        int miji_sx = -1, miji_sy = -1;
        int time_ex = -1, time_ey = -1, time_ek = -1;
        // 미지의 공간으로 이어지는 출구 좌표 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 3){
                    // 왼쪽에 출구 있는 경우
                    if(board[i][j-1] == 0){
                        miji_sx = i;
                        miji_sy = j-1;

                        time_ek = 1;
                        time_ex = M-1;
                        time_ey = i-time_bx;
                    }

                    // 오른쪽에 출구 있는 경우
                    if(board[i][j+1] == 0){
                        miji_sx = i;
                        miji_sy = j+1;

                        time_ek = 0;
                        time_ex = M-1;
                        time_ey = (M-1)-(i-time_bx);
                    }

                    // 아래쪽에 출구 있는 경우
                    if(board[i+1][j] == 0){
                        miji_sx = i+1;
                        miji_sy = j;

                        time_ek = 2;
                        time_ex = M-1;
                        time_ey = j-time_by;
                    }

                    // 위쪽에 출구 있는 경우
                    if(board[i-1][j] == 0){
                        miji_sx = i-1;
                        miji_sy = j;

                        time_ek = 3;
                        time_ex = M-1;
                        time_ey = (M-1)-(j-time_by);
                    }
                }
            }
        }

        // 시간의 벽 bfs
        int result = wall_bfs(wall, time_sx, time_sy, time_ek, time_ex, time_ey);
        if(result == -1){
            bw.write("-1");
            bw.flush();
            return;
        }

        int[][] spreadBoard = spreadStrangeTime(board, q);

        // 미지의 공간 bfs
        int answer = miji_bfs(board, spreadBoard, miji_sx, miji_sy, miji_ex, miji_ey, result);
        if(answer == -1){
            bw.write("-1");
            bw.flush();
            return;
        }

        bw.write(answer + "\n");
        bw.flush();


    }
    private static int[] findTimeBase(int[][] board){
        int N = board.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 3){
                    return new int[]{i,j};
                }
            }
        }

        return null;
    }

    private static int[][] spreadStrangeTime(int[][] board, Deque<StrangeTime> q){
        int[][] spreadBoard = new int[N][N];
        for (StrangeTime st : q) {
            spreadBoard[st.r][st.c] = 1;
        }

        while (!q.isEmpty()) {
            StrangeTime now = q.removeFirst();


            int next_r = now.r, next_c = now.c;
            // 동
            if(now.d == 0){
                next_c++;
            }

            // 서
            if(now.d == 1){
                next_c--;
            }

            // 남
            if(now.d == 2){
                next_r++;
            }

            // 북
            if(now.d == 3){
                next_r--;
            }
            if (!isInRange(next_r, next_c, N)) continue;
            if(board[next_r][next_c] == 1) continue;
            if(spreadBoard[next_r][next_c] != 0) continue;


            q.add(new StrangeTime(now.cnt+1, next_r,next_c,now.d,now.v));
            spreadBoard[next_r][next_c] = now.cnt * now.v;

        }

        return spreadBoard;

    }

    private static int miji_bfs(int[][] board, int[][] spreadBoard, int sx, int sy, int ex, int ey, int num){
        int[][] visited = new int[N][N];

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = num;

        while (!q.isEmpty()) {
            int[] pop = q.removeFirst();
            int x = pop[0];
            int y = pop[1];
            if(x == ex && y == ey){
                return visited[x][y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isInRange(nx,ny,N)) continue;
                if(visited[nx][ny] != 0) continue;
                if(board[nx][ny] != 0) continue;

                if(spreadBoard[nx][ny] != 0 && (spreadBoard[nx][ny] <= visited[x][y] + 1)){
                    continue;
                }

                visited[nx][ny] = visited[x][y] + 1;
                q.add(new int[]{nx, ny});






            }
        }
        return -1;

    }

    private static int wall_bfs(int[][][] wall, int sx, int sy, int ek, int ex, int ey){
        int[][][] visited = new int[5][M][M];


        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{4, sx, sy});
        visited[4][sx][sy] = 1;

        while (!q.isEmpty()) {
            int[] now = q.removeFirst();
            int k = now[0];
            int x = now[1];
            int y = now[2];

            if(x == ex && y == ey && k == ek) return visited[k][x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nk = k;


                if(nx < 0){
                    if (k == 0) {
                        nk = 4;
                        nx = (M-1) - y;
                        ny = M-1;
                    }
                    if (k == 1) {
                        nk = 4;
                        nx = y;
                        ny = 0;
                    }
                    if (k == 2) {
                        nk = 4;
                        nx = M-1;
                    }
                    if (k == 3) {
                        nk = 4;
                        nx = 0;
                        ny = (M-1) - y;
                    }
                    if (k == 4) {
                        nk = 3;
                        nx = 0;
                        ny = (M-1) - y;
                    }
                }

                else if(nx >= M){
                    if (k == 4) {
                        nk = 2;
                        nx = 0;
                    }else{
                        continue;
                    }
                }

                else if(ny < 0){
                    if (k == 0) {
                        nk = 2;
                        ny = M-1;
                    }
                    if (k == 1) {
                        nk = 3;
                        ny = M-1;
                    }
                    if (k == 2) {
                        nk = 1;
                        ny = M-1;
                    }
                    if (k == 3) {
                        nk = 0;
                        ny = M-1;
                    }
                    if (k == 4) {
                        nk = 1;
                        nx = 0;
                        ny = x;
                    }
                }

                else if(ny >= M){
                    if (k == 0) {
                        nk = 3;
                        ny = 0;
                    }
                    if (k == 1) {
                        nk = 2;
                        ny = 0;
                    }
                    if (k == 2) {
                        nk = 0;
                        ny = 0;
                    }
                    if (k == 3) {
                        nk = 1;
                        ny = 0;
                    }

                    if (k == 4) {
                        nk = 0;
                        nx = 0;
                        ny = (M-1) - x;
                    }
                }


                if(visited[nk][nx][ny] != 0) continue;
                if(wall[nk][nx][ny] == 1) continue;


                q.add(new int[]{nk, nx, ny});
                visited[nk][nx][ny] = visited[k][x][y] + 1;

            }
        }

        return -1;


    }

    private static boolean isInRange(int x, int y, int range) {
        if(x < 0 || x >= range || y < 0 || y >= range)
            return false;

        return true;
    }



    private static class StrangeTime{
        int cnt;
        int r,c;
        int d,v;

        public StrangeTime(int r, int c, int d, int v) {
            this.cnt = 1;
            this.r = r;
            this.c = c;
            this.d = d;
            this.v = v;
        }

        public StrangeTime(int cnt, int r, int c, int d, int v) {
            this.cnt = cnt;
            this.r = r;
            this.c = c;
            this.d = d;
            this.v = v;
        }
    }
}
