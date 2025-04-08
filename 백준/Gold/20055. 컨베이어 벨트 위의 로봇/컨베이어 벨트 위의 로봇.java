import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }


        int answer = solution(N, K);
        bw.write(answer + "\n");
        bw.flush();


    }

    private static int solution(int N, int K){
        int M = 2 * N;

        int step = 0;

        while(isValid(M,K)){

            // 1. 벨트와 로봇 회전
            int tmp = belt[M - 1];
            for (int i = M-1; i > 0; i--) {
                belt[i] = belt[i-1];
            }
            belt[0] = tmp;

            for (int i = N-1; i > 0; i--) {
                robot[i] = robot[i-1];
            }
            robot[0] = false;
            robot[N - 1] = false;


            // 2. 로봇 이동
            // 이동하려는 칸에 로봇 없고, 해당 칸 내구도 1이상이어야
            for (int i = N - 1; i > 0; i--) {
                if(robot[i-1] && !robot[i] && belt[i] >= 1) {
                        robot[i] = true;
                        robot[i - 1] = false;
                        belt[i]--;
                }
            }

            // 3. 로봇 올리기
            if (belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
            }

            step++;
        }

        return step;
    }

    private static boolean isValid(int M, int K){
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (belt[i] == 0) {
                cnt++;
            }
            if (cnt >= K)
                return false;
        }
        return true;

    }
}
