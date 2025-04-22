#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int N, M;
    vector<int> arr;
    
    cin >> N;
    for(int i=0;i<N;i++){
        int x; cin >> x;
        arr.push_back(x);
    }
    sort(arr.begin(), arr.end());

    cin >> M;
    for(int i=0;i<M;i++){
        int num;
        cin >> num;
        cout << upper_bound(arr.begin(), arr.end(), num) - lower_bound(arr.begin(), arr.end(), num) << " ";
    }

}
