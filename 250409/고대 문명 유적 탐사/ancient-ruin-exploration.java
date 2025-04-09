import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr = new int[5][5];
    static Deque<Integer> order = new ArrayDeque<>();
    static final int SIZE = 5;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            order.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < K; i++){

            // doExplore();
            Map map = doExplore();
            if(map == null) break;

            arr = map.arr;


            // fillYumool();
            int answer = fillYumool(map.value);
            if(answer == -1) break;

            bw.write(answer + " ");


        }

        bw.flush();

    }


    private static Map doExplore(){
        // 회전 중심 좌표 설정(9개 중 하나)
        // 회전 각도 설정(3개 중 하나)
        // 총 27가지의 경우의 수 중 유물1차 획득 가치 최대인 경우
        // 리스트에 저장
        // 회전한 각도 가장 작은
        // 회전 중심 좌표 열 가장 작은 -> 행 가장 작은

        int max = 0;
        Map maxMap = null;
        for (int k = 1; k <= 3; k ++) {
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {

                    int[][] rotateArr = rotateMap(j, i, k);
                    int val = calculateValue(rotateArr);
                    if (val > max) {
                        max = val;
                        maxMap = new Map(rotateArr, max);
                    }
                }
            }
        }


        return maxMap;


    }

    private static int[][] rotateMap(int x, int y, int cnt) {
        // 전체 보드를 복사
        int[][] newArr = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                newArr[i][j] = arr[i][j];
            }
        }


        for (int i = 0; i < cnt; i++) {
            // 90도 회전
            int[] tmp = new int[]{newArr[x-1][y+1],newArr[x-1][y]};
            newArr[x-1][y+1] = newArr[x-1][y-1];
            newArr[x-1][y] = newArr[x][y-1];
            newArr[x-1][y-1] = newArr[x+1][y-1];
            newArr[x][y-1] = newArr[x+1][y];
            newArr[x+1][y-1] = newArr[x+1][y+1];
            newArr[x+1][y] = newArr[x][y+1];

            newArr[x+1][y+1] = tmp[0];
            newArr[x][y+1] = tmp[1];

        }

        return newArr;

    }

    private static int calculateValue(int[][] arr) {
        boolean[][] visited = new boolean[5][5];
        int totalValue = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(visited[i][j]) continue;

                int num = arr[i][j];

                // dfs()
                Queue<int[]> trace = new LinkedList<>();
                dfs(arr, visited, trace, i, j, num);

                if(trace.size() >= 3){

                    // 유물 가치에 조각 개수 더하기
                    totalValue += trace.size();

                    // 유물 사라짐???
                    while(!trace.isEmpty()){
                        int[] poll = trace.poll();
                        arr[poll[0]][poll[1]] = 0;
                    }


                }
            }
        }

        return totalValue;
    }


    private static void dfs(int[][] arr, boolean[][] visited, Queue<int[]> trace, int x, int y, int num){
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        trace.add(new int[]{x, y});

        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.pop();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if(visited[nx][ny]) continue;

                if(arr[nx][ny] == num){
                    q.add(new int[]{nx, ny});
                    trace.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

    }

    private static int fillYumool(int max){

        int result = -1;

        while(true){
            // 열이 작고 행이 큰 우선순위로 채워
            for (int j = 0; j < SIZE; j++) {
                for (int i = SIZE - 1; i >= 0; i--) {
                    if (arr[i][j] == 0 && !order.isEmpty()) {
                        arr[i][j] = order.poll();
                    }
                }
            }

            result = calculateValue(arr);
            if(result == 0)
                break;
            max += result;

        }

        if(result == -1)
            return -1;

        return max;

    }

    private static class Map {
        int[][] arr;
        int value;
//        int x,y;
//
//        int degree;

        public Map(int[][] arr, int value, int x, int y, int degree) {
            this.arr = arr;
            this.value = value;
//            this.x = x;
//            this.y = y;
//            this.degree = degree;
        }

        public Map(int[][] arr, int value) {
            this.arr = arr;
            this.value = value;
//            this.x = x;
//            this.y = y;
//            this.degree = degree;
        }


    }
}
