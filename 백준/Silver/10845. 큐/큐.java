import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd){
                case "push":
                    int X = Integer.parseInt(st.nextToken());
                    q.add(X);
                    break;

                case "pop":
                    if(!q.isEmpty())
                        bw.write(q.remove()+"\n");
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
        }
        bw.flush();
    }
}
