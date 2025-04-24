#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;

vector<int> answer;
vector<vector<int>> graph;
vector<int> indegree;

void topologicalSort(int N, int M);

int main(){
    int N, M;
    cin >> N >> M;

    // 크기 N+1 으로 초기화
    graph.assign(N+1, vector<int>());
    indegree.assign(N+1, 0);

    // input
    for(int i=0;i<M;i++){
        int num, first;
        cin >> num >> first;

        for(int j=0;j<num-1;j++){
            int second;
            cin >> second;
            graph[first].push_back(second);
            indegree[second]++;
            first = second;
        }
    }

    topologicalSort(N, M);

    // output
    if(answer.size() != N){
        cout << "0" << "\n";
    }
    else{
        for(const int& n : answer){
            cout << n << "\n";
        }
    }

}

void topologicalSort(int N, int M){
    queue<int> q;

    for(int i=1;i<N+1;i++){
        if(indegree[i] == 0){
            q.push(i);
        }
    }

    while(!q.empty()){
        int now = q.front();
        q.pop();
        answer.push_back(now);

        for(const int& next: graph[now]){
            indegree[next]--;
            if(indegree[next] == 0){
                q.push(next);
            }

        }

    }

}