import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        Deque<Character> left_q = new ArrayDeque<>();
        Deque<Character> right_q = new ArrayDeque<>();
        int len = str.length();

        for (int i = 0; i < len; i++) {
            left_q.add(str.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd){
                case "L":
                    if(!left_q.isEmpty()){
                        right_q.addFirst(left_q.removeLast());
                    }
                    break;
                case "D":
                    if (!right_q.isEmpty()){
                        left_q.addLast(right_q.removeFirst());
                    }
                    break;
                case "B":
                    if (!left_q.isEmpty()){
                        left_q.removeLast();
                    }
                    break;
                case "P":
                    char c = st.nextToken().charAt(0);
                    left_q.addLast(c);
                    break;
            }
        }
        
        while(!left_q.isEmpty()){
            bw.write(left_q.removeFirst());
        }
        while(!right_q.isEmpty()){
            bw.write(right_q.removeFirst());
        }

        bw.flush();
    }
}
