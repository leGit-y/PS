#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<vector<int>> graph;
vector<int> indegree;

void topologicalSort(int N);

int main(){
    int N, M;
    cin >> N >> M;

    graph.assign(N+1, vector<int>());
    indegree.assign(N+1, 0);

    for(int i=0;i<M;i++){
        int a, b;
        cin >> a >> b;

        graph[a].push_back(b);
        indegree[b]++;
    }

    topologicalSort(N);

}

void topologicalSort(int N){
    priority_queue<int, vector<int>, greater<int>> pq;

    for(int i=1;i<N+1;i++){
        if(indegree[i] == 0){
            pq.push(i);
        }
    }

    while(!pq.empty()){
        int cur = pq.top();
        pq.pop();
        cout << cur << " ";

        for(const auto& next: graph[cur]){
            indegree[next]--;

            if(indegree[next] == 0){
                pq.push(next);
            }
        }
    }

    cout << "\n";

}