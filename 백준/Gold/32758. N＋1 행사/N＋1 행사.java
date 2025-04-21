import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int[] arr, goal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());
        arr = new int[M];
        goal = new int[M];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            goal[i] = Integer.parseInt(st.nextToken());
            answer = solution(i);
            bw.write(answer+" ");
        }

        bw.flush();
        br.close();
        bw.close();

    }
    
    private static int solution(int idx) {
        if(arr[idx] > goal[idx]){
            return goal[idx];
        }
        if(goal[idx] % arr[idx] ==0)
            return goal[idx] - (goal[idx] / arr[idx] - 1);

        return goal[idx] - (goal[idx] / arr[idx]);
    }
}