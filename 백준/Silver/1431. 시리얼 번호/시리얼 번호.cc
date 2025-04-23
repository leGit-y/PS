#include <iostream>
#include <algorithm>

using namespace std;

bool comp(string a, string b);

int main(){
    int N;
    cin >> N;

    string *serial = new string[N];
    for(int i=0;i<N;i++){
        cin >> serial[i];
    }

    sort(serial, serial+N, comp);

    for(int i=0;i<N;i++){
        cout << serial[i] << "\n";
    }

}

bool comp(string a, string b){
    int aLen = a.length();
    int bLen = b.length();

    if(aLen != bLen)
        return aLen < bLen;
    
    int aSum = 0;
    for(int i=0;i<aLen;i++){
        if('0' <= a[i] && a[i] <= '9'){
            aSum += (a[i] - '0');
        }
    }
    int bSum = 0;
    for(int i=0;i<bLen;i++){
        if('0' <= b[i] && b[i] <= '9'){
            bSum += (b[i] - '0');
        }
    }

    if(aSum != bSum)
        return aSum < bSum;
    
    return a < b;

}