#include <iostream>
#include <algorithm>
using namespace std;

string solution(int N, int B);

int main(){
    int N, B;
    cin >> N >> B;

    string answer = solution(N, B);

    cout << answer << endl;

}

string solution(int N, int B){
    string result = "";

    while(N > 0){
        int remain = N % B;
        if(remain >= 10){
            result += ('A' + remain - 10);
        }else{
            result += ('0' + remain);
        }
        N /= B;
    }

    reverse(result.begin(), result.end());
    return result;
}