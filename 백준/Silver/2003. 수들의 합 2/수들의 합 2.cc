#include <iostream>

using namespace std;

int main(){
    int N, M;
    cin >> N >> M;

    int *arr = new int[N+1];
    arr[0] = 0;
    for(int i=1;i<=N;i++){
        cin >> arr[i];
    }

    for(int i=1;i<=N;i++){
        arr[i] += arr[i-1];
    }

    int i = 1, j = 1;
    int answer = 0;
    while(j <= N){
        // i ~ j 까지의 합
        int sum = arr[j] - arr[i-1];
        if(sum == M){
            answer++;
            j++;
        }else if(sum < M){
            j++;
        }else if(sum > M){
            i++;
        }
    }

    cout << answer << "\n";

}