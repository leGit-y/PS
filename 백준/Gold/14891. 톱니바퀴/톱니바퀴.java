import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static LinkedList<Integer>[] wheel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        wheel = new LinkedList[4];
        for (int i = 0; i < 4; i++) {
            wheel[i] = new LinkedList<>();
            for (char ch : br.readLine().toCharArray()) {
                wheel[i].add(ch - '0');  // 문자를 숫자로 변환
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            wheelNum--;
            rotateCheck(wheelNum, dir);
        }

        int answer = addScore();
        System.out.println(answer);

    }

    private static void rotateCheck(int wheelNum, int dir) {
        List<Rotate> list = new LinkedList<>();
        list.add(new Rotate(wheelNum, dir));

        int opDir = dir;
        // 왼쪽 바퀴들
        for (int i = wheelNum; i >= 1; i--) {
            opDir = (opDir == 1 ? -1 : 1);
            if(wheel[i].get(6) != wheel[i-1].get(2)) {
                list.add(new Rotate(i - 1, opDir));
            }
            // 한 개가 회전하지 않으면 그 왼쪽 바퀴들도 회전 X
            else break;
        }

        opDir = dir;
        // 오른쪽 바퀴들
        for (int i = wheelNum; i < 3; i++) {
            opDir = (opDir == 1 ? -1 : 1);
            if(wheel[i].get(2) != wheel[i+1].get(6)){
                list.add(new Rotate(i + 1, opDir));
            }
            // 한 개가 회전하지 않으면 그 오른쪽 바퀴들도 회전 X
            else break;

        }

        // rotate
        for (Rotate r : list) {
            rotate(r.num, r.dir);
        }

    }

    private static int addScore() {
        int score = 0;
        if(wheel[0].get(0) == 1){
            score += 1;
        }
        if(wheel[1].get(0) == 1){
            score += 2;
        }
        if(wheel[2].get(0) == 1){
            score += 4;
        }
        if(wheel[3].get(0) == 1){
            score += 8;
        }
        return score;

    }

    private static void rotate(int num, int dir) {
        // 시계방향
        if(dir == 1){
            int last = wheel[num].removeLast();
            wheel[num].addFirst(last);
        }
        
        // 반시계 방향
        else if (dir == -1){
            int first = wheel[num].removeFirst();
            wheel[num].add(first);
        }

    }

    private static class Rotate{
        int num;
        int dir;

        public Rotate(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }


}
