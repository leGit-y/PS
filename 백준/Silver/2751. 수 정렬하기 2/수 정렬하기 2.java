import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int[] arr;
    static int[] tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tmp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0,N-1);

        for (int i = 0; i < N; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
    }

    private static void mergeSort(int start, int end){
        if(start >= end) return;

        int mid = (start + end) / 2;
        mergeSort(start, mid);
        mergeSort(mid+1, end);

        int first = start;
        int second = mid + 1;
        int idx = first;

        while(first <= mid && second <= end){
            if(arr[first] <= arr[second]){
                tmp[idx++] = arr[first++];
            }else{
                tmp[idx++] = arr[second++];
            }
        }

        while(first <= mid){
            tmp[idx++] = arr[first++];
        }
        while(second <= end){
            tmp[idx++] = arr[second++];
        }

        for (int i = start; i <= end; i++) {
            arr[i] = tmp[i];
        }

    }

}
