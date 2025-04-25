#include <iostream>
using namespace std;
#define MAX_N 100000 + 1

// O(N^2) 일 경우 시간초과 날 것으로 예상

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);
    int N;

    cin >> N;
    int *arr = new int[N];
    bool visited[MAX_N];
    fill_n(visited, MAX_N, false);

    for(int i=0;i<N;i++){
        cin >> arr[i];
    }

    long long answer = 0;
    int e = 0;
    for(int s=0;s<N;s++){
        while(e < N){
            if(visited[arr[e]]) break;
            visited[arr[e]] = true;
            e++;
        }
        answer += (e-s);
        visited[arr[s]] = false;
    }
    
    cout << answer << "\n";

}
