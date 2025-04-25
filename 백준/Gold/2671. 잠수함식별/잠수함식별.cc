#include <iostream>
#include <regex>

using namespace std;

int main(){
    string str;
    cin >> str;

    regex check("(100+1+|01)+");
    
    if(regex_match(str, check)){
        cout << "SUBMARINE" << "\n";
    }else{
        cout << "NOISE" << "\n";
    }

}