import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = 0;

        dfs(0);
        System.out.println(answer);

    }
    
    private static void dfs(int col){
        if(col == N){
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {

            // col: 행 / arr[col]: 열
            arr[col] = i;

            if(!possible(col)) continue;

            // 재귀를 돌때 col값은 다 다르므로
            // 같은 열인지, 같은 대각선인지만 체크
            dfs(col+1);
        }

    }

    private static boolean possible(int col){

        // 방문한 행(col)들 중에
        for (int i = 0; i < col; i++) {
            
            // 같은 열 존재하는 경우
            if(arr[col] == arr[i]){
                return false;
            }

            // 대각선 존재하는 경우
            if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}
