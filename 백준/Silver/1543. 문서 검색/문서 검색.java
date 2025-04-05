import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String check = br.readLine();

        int answer = 0;
        String newStr = str.replace(check, "");
        answer = (str.length() - newStr.length()) / check.length();

        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
