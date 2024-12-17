import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String cmd;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            switch (cmd){
                case "push":
                    int X = Integer.parseInt(st.nextToken());
                    stack.push(X);
                    break;

                case "pop":
                    if(stack.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(stack.pop());
                    break;

                case "size":
                    System.out.println(stack.size());
                    break;

                case "empty":
                    if(stack.isEmpty())
                        System.out.println("1");
                    else
                        System.out.println("0");
                    break;

                case "top":
                    if(stack.isEmpty())
                        System.out.println("-1");
                    else
                        System.out.println(stack.peek());
                    break;

            }

        }

    }


}
