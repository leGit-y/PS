import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;

    static int N, M, answer;
    static int[][] room;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        dfs(new Point(r, c, d));
        System.out.println(answer);

    }

    private static void dfs(Point p) {

        while(true){
            // 청소된것으로 체크
            if(room[p.x][p.y] == 0) {
                room[p.x][p.y] = 2;
                answer++;
            }

            if(!hasUncleanedRoom(p.x,p.y)){
                int x, y;
                x = p.x;
                y = p.y;

                // 방향 유지한 채 한 칸 후진
                if (p.dir == NORTH) {
                    x = p.x+1;
                }
                if (p.dir == SOUTH) {
                    x = p.x-1;
                }
                if (p.dir == WEST) {
                    y = p.y+1;
                }
                if (p.dir == EAST) {
                    y = p.y-1;
                }

                if (x < 0 || x >= N || y < 0 || y >= M)
                    return;

                // 뒤쪽 칸이 벽이라 후진할 수 없다면 -> 작동을 멈춘다
                if (room[x][y] == 1){
                    System.out.println(answer);
                    System.exit(0);
                }

                p.x = x;
                p.y = y;
                return;

            }

            rotateCounterClockwise(p);

            if (frontIsUncleaned(p))
                dfs(p);

        }

    }
    
    private static void rotateCounterClockwise(Point p){
        p.dir = (p.dir + 3) % 4;
    }

    private static boolean frontIsUncleaned(Point p){
        if(p.dir == NORTH){
            if(room[p.x-1][p.y] == 0) {
                p.x--;
                return true;
            }
        }
        if(p.dir == SOUTH){
            if(room[p.x+1][p.y] == 0) {
                p.x++;
                return true;
            }
        }
        if(p.dir == EAST){
            if(room[p.x][p.y+1] == 0) {
                p.y++;
                return true;
            }
        }
        if(p.dir == WEST){
            if(room[p.x][p.y-1] == 0) {
                p.y--;
                return true;
            }
        }
        return false;
    }

    private static boolean hasUncleanedRoom(int r, int c){

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if(room[nx][ny] != 0)
                continue;

            return true;
        }
        return false;
    }

    private static class Point {
        int x;
        int y;
        int dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

    }
}
