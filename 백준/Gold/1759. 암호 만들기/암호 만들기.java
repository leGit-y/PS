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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        dfs("", 0, 0, 0, 0);
        System.out.println(sb);

    }

    private static void dfs(String str, int idx, int depth, int v_cnt, int c_cnt){
        if(depth == L && v_cnt >= 1 && c_cnt >= 2){
            sb.append(str).append("\n");
            return;
        }
        for (int i = idx; i < C; i++) {
            if(visited[i]) continue;

            boolean check = false;
            visited[i] = true;
            if(isVowel(arr[i])){
                v_cnt++;
                check=true;
            }
            else{
                c_cnt++;
            }
            dfs(str + arr[i], i+1,depth + 1, v_cnt, c_cnt);
            visited[i] = false;
            if(check){
                v_cnt--;
            }
            else{
                c_cnt--;
            }
        }
    }

    private static boolean isVowel(char c){
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < vowels.length; i++) {
            if (c == vowels[i]) {
                return true;
            }
        }
        return false;
    }
}
