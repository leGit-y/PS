import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Character> q = new ArrayDeque<>();
        String str = br.readLine();

        int i = 0;
        boolean tag = false;
        boolean print = false;
        while(i < str.length()) {

            char c = str.charAt(i);
            q.addLast(c);

            if (c == '>'){
                tag = true;
                print = true;
            }
            else if (c == '<'){
                tag = true;
                print = false;
            }
            else if (c == ' ' && !tag){
                print = true;
            }

            if(tag && print){
                while(!q.isEmpty()){
                    bw.write(q.removeFirst());
                }
                tag = false;
                print = false;
            }
            else if((!tag && print) || (tag && !print)){
                char end = q.removeLast();
                while(!q.isEmpty()){
                    bw.write(q.removeLast());
                }
                bw.write(end);
                print = false;
            }

            i++;

        }
        while(!q.isEmpty()){
            bw.write(q.removeLast());
        }

        bw.flush();

    }

}
