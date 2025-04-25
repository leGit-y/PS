#include <iostream>
#include <algorithm>
using namespace std;

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);

    int N, M;
    cin >> N  >> M;

    int *arr = new int[N];

    for(int i=0;i<N;i++){
        cin >> arr[i];
    }

    sort(arr, arr+N);

    int minDif = arr[N-1] - arr[0];
    int s = 0, e = 0;

    while(s <= e && e < N){
        int dif = arr[e] - arr[s];
        if(dif < M){
            e++;
        }else{
            if(dif < minDif){
                minDif = dif;
            }
            s++;
        }
    }

    cout << minDif << "\n";

    
}