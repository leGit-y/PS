#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    int N;
    vector<int> v;

    cin >> N;

    int *input = new int[N];
    for(int i=0;i<N;i++){
        int x;  cin >> x;
        input[i] = x;
        v.push_back(x);
    }

    sort(v.begin(), v.end());

    // ex) 1 1 1 2 2 2 3  -- unique() --> 1 2 3 ( 2 2 2 3 )   
    // (괄호) 안은 원래 배열에 저장된 수 그대로.
    // unique() 반환값은 뒤의 () 괄호의 시작 위치
    std::vector<int>::iterator begin = unique(v.begin(), v.end());

    // () 괄호 안의 값들 제거
    v.erase(begin, v.end());

    for(int i=0;i<N;i++){
        cout << lower_bound(v.begin(), v.end(), input[i]) - v.begin() << " ";
    }

}