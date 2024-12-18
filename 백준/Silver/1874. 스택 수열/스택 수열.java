import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int start = 1;
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            for (int j = start; j <= x; j++) {
                stack.push(j);
//                bw.write("+\n");
                sb.append("+\n");
                start += 1;
            }

            if(!stack.isEmpty() && stack.peek() == x){
                stack.pop();
//                bw.write("-\n");
                sb.append("-\n");
            }else{
                System.out.println("NO\n");
                return;
            }
        }

        System.out.println(sb);
//        bw.flush();

    }

}
