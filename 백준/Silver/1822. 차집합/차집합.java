import java.io.*;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nA = Integer.parseInt(st.nextToken());
        int nB = Integer.parseInt(st.nextToken());

        Set<Integer> A = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nA; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nB; i++) {
            int num = Integer.parseInt(st.nextToken());
            A.remove(num);
        }

        bw.write(A.size() + "\n");
        for (Integer num : A) {
            bw.write(num + " ");
        }
        bw.flush();


    }
}
