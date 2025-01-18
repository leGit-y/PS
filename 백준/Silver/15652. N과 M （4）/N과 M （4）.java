import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static List<String> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            dfs(""+i, i, 1);
        }
        for (int i = 0; i < answer.size(); i++) {
            bw.write(answer.get(i)+"\n");
        }
        bw.flush();
    }
    
    private static void dfs(String str, int num, int depth){
        if(depth == M){
            answer.add(str);
            return;
        }
        for (int i = num; i <= N; i++) {
            dfs(str+" "+i, i, depth+1);
        }

    }
}
