import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String cmd;
//        Stack<Integer> stack = new Stack<>();
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
//            solution_stack(st, cmd, stack);
            solution_arraylist(st, cmd, stack);

        }

    }

    private static void solution_arraylist(StringTokenizer st, String cmd, List<Integer> stack) {
        switch (cmd){
            case "push":
                int X = Integer.parseInt(st.nextToken());
                stack.add(X);
                break;

            case "pop":
                if(stack.isEmpty())
                    System.out.println("-1");
                else
                    System.out.println(stack.remove(stack.size()-1));
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
                    System.out.println(stack.get(stack.size()-1));
                break;

        }
    }

    private static void solution_stack(StringTokenizer st, String cmd, Stack<Integer> stack) {
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
