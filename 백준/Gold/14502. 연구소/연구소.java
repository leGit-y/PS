import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static String[][] arr;
    static boolean[][] visited;
    static int max = 0;
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new String[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split(" ");
        }

        dfs( 0);
        bw.write(max + "\n");
        bw.flush();
    }



    private static void dfs(int depth){
        if(depth == 3){
            max = Math.max(max, spreadVirus());
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) continue;
                if(arr[i][j].equals("1") || arr[i][j].equals("2")) continue;


                visited[i][j] = true;
                arr[i][j] = "1";
                dfs(depth + 1);
                visited[i][j] = false;
                arr[i][j] = "0";

            }
        }

    }

    private static int spreadVirus(){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> q = new ArrayDeque<>();

        // init
        String[][] copyArr = new String[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyArr[i][j] = arr[i][j];
                if (copyArr[i][j].equals("2")) {
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] now = q.pop();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;

                if(copyArr[nx][ny].equals("0")){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    copyArr[nx][ny] = "2";
                }
            }
        }

        return calculate(copyArr);

    }

    private static int calculate(String[][] arr){
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j].equals("0")) {
                    cnt++;
                }
            }
        }

        return cnt;

    }
}
