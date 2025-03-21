import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Set<String> answer;
    private static int[] arr;
    private static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = new LinkedHashSet<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            dfs(""+arr[i], 1);
        }

        answer.forEach(s -> sb.append(s+"\n"));

        System.out.println(sb);


    }

    private static void dfs(String result, int depth){
        if(depth == M){
            answer.add(result);
            return;
        }
        for (int i=0; i < N; i++) {
            dfs(result + " " +arr[i],depth+1);
        }
    }
}
