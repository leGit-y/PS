#include <iostream>
#include <vector>
#include <set>
using namespace std;

int main(){
    int N;
    set<int> s;

    cin >> N;

    int *input = new int[N];
    for(int i=0;i<N;i++){
        int x;  cin >> x;
        input[i] = x;
        s.insert(x);
    }

    vector<int> v(s.begin(), s.end());

    for(int i=0;i<N;i++){
        cout << lower_bound(v.begin(), v.end(), input[i]) - v.begin() << " ";
    }
}