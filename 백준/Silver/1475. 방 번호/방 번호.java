import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numStr = br.readLine().split("");

        int[] set = new int[10];

        int answer = 1;
        Arrays.fill(set, 1);

        for (int i = 0; i < numStr.length; i++) {
            int num = Integer.parseInt(numStr[i]);

            if(set[num] == 0) {
                if(num == 6 && set[9] != 0){
                    set[9]--;
                    continue;
                }
                if(num == 9 && set[6] != 0){
                    set[6]--;
                    continue;
                }
                answer++;
                addSet(set);
            }
            set[num]--;

        }
        System.out.println(answer);
    }
    
    private static void addSet(int[] set){
        for (int i = 0; i < 10; i++) {
            set[i]++;
        }
    }
}
