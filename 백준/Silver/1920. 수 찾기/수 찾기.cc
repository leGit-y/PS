#include <iostream>
#include <algorithm>
using namespace std;

int solution(int num);

int *arr;
int N, M;

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL);
    
    cin >> N;
    arr = new int[N];
    for(int i=0;i<N;i++){
        cin >> arr[i];
    }

    // sort!
    sort(arr, arr+N);

    cin >> M;
    for(int i=0;i<M;i++){
        int x;
        cin >> x;
        cout << solution(x) << "\n";
    }
}

int solution(int target){
    int i = 0, j = N-1;
    while(i <= j){
        int midIdx = (i+j) / 2;
        int midNum = arr[midIdx];

        if(midNum == target){
            return 1;
        }
        else if(midNum < target){
            i = midIdx + 1;
        }
        else if(midNum > target){
            j = midIdx - 1;
        }
    }
    return 0;
}