import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> q = new ArrayDeque<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int X;
            switch (cmd){

                case "push_back":
                    X = Integer.parseInt(st.nextToken());
                    q.addLast(X);
                    break;

                case "push_front":
                    X = Integer.parseInt(st.nextToken());
                    q.addFirst(X);
                    break;

                case "pop_back":
                    if(!q.isEmpty())
                        bw.write(q.removeLast()+"\n");
                    else
                        bw.write("-1\n");
                    break;

                case "pop_front":
                    if(!q.isEmpty())
                        bw.write(q.removeFirst()+"\n");
                    else
                        bw.write("-1\n");
                    break;

                case "size":
                    bw.write(q.size()+"\n");
                    break;

                case "empty":
                    bw.write(q.isEmpty() ? "1\n" : "0\n");
                    break;

                case "front":
                    if(!q.isEmpty())
                        bw.write(q.getFirst()+"\n");
                    else
                        bw.write("-1\n");
                    break;

                case "back":
                    if(!q.isEmpty())
                        bw.write(q.getLast()+"\n");
                    else
                        bw.write("-1\n");
                    break;
            }
            bw.flush();
        }
    }
}
