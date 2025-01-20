import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] arr;
    static List<String> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
        
        for (int i = 0; i < answer.size(); i++) {
            bw.write(answer.get(i)+"\n");
        }
        bw.flush();


    }
    private static void dfs(String str, int idx, int depth){
        if(depth == M){
            answer.add(str);
            return;
        }
        for (int i = idx; i < N; i++) {
            dfs(str+arr[i]+" ", i+1, depth+1);
        }

    }
}
