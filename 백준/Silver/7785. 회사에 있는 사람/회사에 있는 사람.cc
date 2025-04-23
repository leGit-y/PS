#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    int n;
    cin >> n;

    map<string, string, greater<string>> m;
    for(int i=0;i<n;i++){
        string name, status;
        cin >> name >> status;

        m[name] = status;
    }

    for (const auto& str : m) {
        if(str.second == "enter")
		    cout << str.first << "\n";
	}
}