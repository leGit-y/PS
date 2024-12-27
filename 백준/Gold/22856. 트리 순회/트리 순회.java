import java.io.*;
import java.util.*;

public class Main {
    static int answer = 0;
    static int N;
    static boolean flag = false;
    static boolean[] visited;
    static int lastNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Node[] tree = new Node[N+1];
        visited = new boolean[N+1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a] = new Node(b,c);
        }

        getLastNode(tree,1);
        inorderTraversal(tree, 1);
        bw.write((answer) + "\n");
        bw.flush();
    }

    private static class Node{
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static void getLastNode(Node[] tree, int start){
        if(start == -1){
            return;
        }
        getLastNode(tree, tree[start].left);
        lastNode = start;
        getLastNode(tree, tree[start].right);

    }

    private static void inorderTraversal(Node[] tree, int start){
        if(start == -1){
            return;
        }
        visited[start] = true;
//        System.out.println(start);
        int left = tree[start].left;
        int right = tree[start].right;

        if(left != -1 && !visited[left]) {
            answer++;
            inorderTraversal(tree, left);
        }


        if(right != -1 && !visited[right]){
            answer++;
            inorderTraversal(tree, right);
        }

        if(lastNode == start)
            flag = true;

        if(flag)
            return;

        answer++;


    }
}
