import java.io.*;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                String word = st.nextToken();
                Stack<Character> stack = new Stack<>();

                for (int j = 0; j < word.length(); j++) {
                    stack.push(word.charAt(j));
                }

                for (int j = 0; j < word.length(); j++) {
                    bw.write(stack.pop());
                }
                bw.write(" ");
            }
            bw.write("\n");
        }

        bw.flush();

    }
}
