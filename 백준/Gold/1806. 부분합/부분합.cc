#include <iostream>
#include <algorithm>
using namespace std;

int main(){
    ios::sync_with_stdio(false); cin.tie(0);
    int N, S;
    cin >> N >> S;

    int *arr = new int[N+1];
    for(int i=1;i<=N;i++){
        cin >> arr[i];
    }

    for(int i=1;i<=N;i++){
        arr[i] += arr[i-1];
    }

    int minLen = 100001;
    bool check = false;
    int s = 1, e = 1; 
    while(e <= N){
        // s ~ e 까지의 부분합
        int sum = arr[e] - arr[s-1];
        if(sum < S){
            e++;
        }else{
            int len = e - s + 1;
            if(len < minLen){
                minLen = len;
            }
            s++;
            check = true;
        }
    }

    if(!check){
        cout << 0 << "\n";
    }else{
        cout << minLen << "\n";
    }

}