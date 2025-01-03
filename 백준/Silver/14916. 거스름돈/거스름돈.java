import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int answer = 0;
        while(true){
            if (n % 5 == 0) {
                answer += n / 5;
                break;
            }else{
                n -= 2;
                answer++;
            }
            if (n<0){
                answer = -1;
                break;
            }

        }

        bw.write(answer+"\n");
        bw.flush();


    }
}
