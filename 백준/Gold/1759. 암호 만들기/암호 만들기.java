import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int L,C;
    static StringBuilder sb = new StringBuilder();
    static char[] arr;
    static char[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        visited = new boolean[C];
        answer = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        dfs( 0,0, 0, 0);
        System.out.println(sb);


    }

    private static void dfs(int idx, int depth, int v_cnt, int c_cnt) {
        // 종료 조건: depth가 L에 도달하면 무조건 종료되게끔
        // 그렇지않으면 answer[depth] 에서 depth가 L보다 큰 경우가 생겨
        // ArrayIndexOutOfBounds 에러
        if (depth == L) {
            if (v_cnt >= 1 && c_cnt >= 2) {
                sb.append(answer).append("\n"); // 배열 전체를 한 번에 추가
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            answer[depth] = arr[i];

            if (isVowel(arr[i])) {
                dfs(i + 1, depth + 1, v_cnt + 1, c_cnt);
            } else {
                dfs(i + 1, depth + 1, v_cnt, c_cnt + 1);
            }
            visited[i] = false;
        }
    }

    private static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
