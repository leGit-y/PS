import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '('){
               stack.push(c);
            }
            if (i > 0) {
                if (str.charAt(i-1) == '(' && c == ')' ) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                        answer += stack.size();
                    }

                }
                else if(str.charAt(i-1) == ')' && c == ')' ){
                    stack.pop();
                    answer++;
                }
            }
        }
        bw.write(answer+"\n");
        bw.flush();
    }
}
