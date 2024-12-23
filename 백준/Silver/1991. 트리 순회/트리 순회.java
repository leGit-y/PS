import java.io.*;
import java.util.StringTokenizer;

// 이진트리: 자식이 최대 2개인 트리
public class Main {
    static char[][] binaryTree;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        binaryTree = new char[N+1][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = st.nextToken().charAt(0) - 'A' + 1;
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            binaryTree[n][0] = left;
            binaryTree[n][1] = right;

        }


        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');

    }

    static private void preorder(char cur) {
        if(cur == '.'){
            return;
        }
        int curIdx = cur - 'A' + 1;
        System.out.print(cur);
        preorder(binaryTree[curIdx][0]);
        preorder(binaryTree[curIdx][1]);

    }

    static private void inorder(char cur){
        if(cur == '.'){
            return;
        }
        int curIdx = cur - 'A' + 1;

        inorder(binaryTree[curIdx][0]);
        System.out.print(cur);
        inorder(binaryTree[curIdx][1]);

    }

    static private void postorder(char cur){
        if(cur == '.'){
            return;
        }
        int curIdx = cur - 'A' + 1;
        postorder(binaryTree[curIdx][0]);
        postorder(binaryTree[curIdx][1]);
        System.out.print(cur);


    }
}
