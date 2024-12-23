import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        tree = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = st.nextToken().charAt(0) - 'A';
            String l = st.nextToken();
            String r = st.nextToken();

            tree[n][0] = l.equals(".") ? -1 : l.charAt(0) - 'A';
            tree[n][1] = r.equals(".") ? -1 : r.charAt(0) - 'A';

        }

        preOrderTraversal(0);
        System.out.println();
        inOrderTraversal(0);
        System.out.println();
        postOrderTraversal(0);
        System.out.println();

    }

    static void preOrderTraversal(int cur){
        if (cur == -1) return;
        System.out.print((char)(cur+'A'));
        preOrderTraversal(tree[cur][0]);
        preOrderTraversal(tree[cur][1]);
    }

    static void inOrderTraversal(int cur){
        if (cur == -1) return;

        inOrderTraversal(tree[cur][0]);
        System.out.print((char)(cur+'A'));
        inOrderTraversal(tree[cur][1]);

    }

    static void postOrderTraversal(int cur){
        if (cur == -1) return;

        postOrderTraversal(tree[cur][0]);
        postOrderTraversal(tree[cur][1]);
        System.out.print((char)(cur+'A'));
    }

}
