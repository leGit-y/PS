import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            A[i] = A[i-1] + num;
        }

        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int num = Integer.parseInt(st.nextToken());
            B[i] = B[i-1] + num;
        }

        List<Integer> aSums = new ArrayList<>();
        List<Integer> bSums = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                aSums.add(A[j] - A[i]);
            }
        }
        for (int i = 0; i <= M; i++) {
            for (int j = i + 1; j <= M; j++) {
                bSums.add(B[j] - B[i]);
            }
        }

        // sort
        Collections.sort(aSums);
        Collections.sort(bSums, Collections.reverseOrder());

        // 투 포인터
        long answer = 0;
        int p1 = 0, p2 = 0;

        while (p1 < aSums.size() && p2 < bSums.size()) {
            long sum = (long) aSums.get(p1) + bSums.get(p2);

            if (sum == T) {
                int aVal = aSums.get(p1);
                int bVal = bSums.get(p2);
                long aCnt = 0, bCnt = 0;

                while (p1 < aSums.size() && aSums.get(p1) == aVal) {
                    aCnt++;
                    p1++;
                }

                while (p2 < bSums.size() && bSums.get(p2) == bVal) {
                    bCnt++;
                    p2++;
                }

                answer += aCnt * bCnt;  
            } else if (sum < T) {
                p1++; 
            } else {
                p2++; 
            }
        }


        bw.write(answer + "\n");
        bw.flush();


    }
}
