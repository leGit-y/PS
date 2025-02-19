import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        Queue<Meeting> meetings_pq = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });

        Queue<Meeting> onGoingMeetings_pq = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new int[2];
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            meetings_pq.add(new Meeting(s, e));
        }

        onGoingMeetings_pq.add(meetings_pq.poll());

        while (!meetings_pq.isEmpty()) {
            Meeting next_m = meetings_pq.poll();
            Meeting ongoing_m = onGoingMeetings_pq.peek();
            if (next_m.start < ongoing_m.end) {
                onGoingMeetings_pq.add(next_m);
            }

            else{
                onGoingMeetings_pq.poll();
                onGoingMeetings_pq.add(next_m);
            }

        }

        System.out.println(onGoingMeetings_pq.size());

    }

    private static class Meeting{
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
