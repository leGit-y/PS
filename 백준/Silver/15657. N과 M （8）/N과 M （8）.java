import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Set<String> answer = new LinkedHashSet<>();
    private static int N,M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs("", 0, 0);

        answer.forEach(s -> System.out.println(s));

    }

    private static void dfs(String result, int depth, int idx) {
        if(depth == M){
            answer.add(result);
            return;
        }

        for (int i = idx; i < N; i++) {
            dfs(result + arr[i] + " ", depth + 1, i);
        }
    }
}
