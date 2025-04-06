import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines.add(new Line(x, y));
        }
        Collections.sort(lines, (x, y) -> x.x - y.x);

        Line before = lines.get(0);
        int sum = before.y - before.x;
        for(int i=1; i<lines.size(); i++){
            Line now = lines.get(i);
            if(now.x <= before.y && now.y <= before.y) continue;
            if(now.x <= before.y){
                sum += now.y - before.y;
            }else{
                sum += now.y - now.x;
            }
            before = now;

        }

        bw.write(sum + "\n");
        bw.flush();

    }

    public static class Line{
        int x;
        int y;
        public Line(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
