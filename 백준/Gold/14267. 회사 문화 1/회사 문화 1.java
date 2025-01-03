import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] result;
    static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        result = new int[N + 1];
        tree = new List[N+1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
             int higher = Integer.parseInt(st.nextToken());
             if(higher == -1) continue;
             tree[higher].add(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            result[num] += w;
        }

        solution(1);

        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
    }
    
    private static void solution(int num) {
        for(int lower : tree[num]){
            result[lower] += result[num];
            solution(lower);
        }

    }
}
