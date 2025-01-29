import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = -5000;
        int min = 5000;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            map.put(num, map.getOrDefault(num,0) + 1);
            max = Math.max(num, max);
            min = Math.min(num, min);
            sum += num;
        }
        Arrays.sort(arr);

        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        keyList.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        int val = keyList.get(0);
        int maxCnt = map.get(val);
        for (int i = 1; i < keyList.size(); i++) {
            int cnt = map.get(keyList.get(i));
            if (cnt == maxCnt) {
                val = keyList.get(i);
                break;
            }
        }

        sb.append(Math.round((float)sum / N)).append("\n");
        sb.append(arr[N/2]).append("\n");
        sb.append(val).append("\n");
        sb.append(max - min).append("\n");
        System.out.println(sb);

    }


}
