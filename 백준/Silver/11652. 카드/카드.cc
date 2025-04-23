#include <iostream>
#include <algorithm>
using namespace std;
typedef long long ll;

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    int N;
    cin >> N;

    ll *arr = new ll[N];
    for(int i=0;i<N;i++){
        cin >> arr[i];
    }

    sort(arr, arr+N);

    int cnt = 1;
    int maxCnt = 1;
    ll answer = arr[0];
    for(int i=1;i<N;i++){
        if(arr[i] == arr[i-1]){
            cnt++;
        }else{
            cnt = 1;
        }

        if(cnt > maxCnt){
            maxCnt = cnt;
            answer = arr[i-1];
        }
    }

    cout << answer;
}
