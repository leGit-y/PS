import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static char[] A;
    static List<String> answerArr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = new char[N];
        answerArr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = st.nextToken().charAt(0);
        }

        for (int i = 0; i < 10; i++) {
            visited = new boolean[10];
            visited[i] = true;
            dfs(i+"", i, 0);

        }

        System.out.println(answerArr.get(answerArr.size() - 1));
        System.out.println(answerArr.get(0));

    }

    private static void dfs(String answer, int cur, int depth){

        if(depth == N) {
            answerArr.add(answer);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if(visited[i]) continue;
            if(A[depth] == '<' && cur > i) continue;
            if(A[depth] == '>' && cur < i) continue;

            visited[i] = true;
            dfs(answer+i, i, depth+1);
            visited[i] = false;

        }

    }

}
