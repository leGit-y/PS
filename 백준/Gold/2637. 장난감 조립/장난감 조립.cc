#include <iostream>
#include <vector>
#include <queue>
#include <utility>
using namespace std;

typedef long long ll;
typedef pair<int, int> pii;

void topologicalSort(int N);

vector<vector<pii>> graph;
vector<int> indegree;

int main(){

    ios::sync_with_stdio(false); cin.tie(0);

    int N, M;
    cin >> N >> M;

    // init
    graph.assign(N+1, vector<pii>());
    indegree.assign(N+1, 0);

    // input
    for(int i=0;i<M;i++){
        int X, Y, K;
        cin >> X >> Y >> K;
        graph[Y].push_back({X,K});
        indegree[X]++;
    }

    topologicalSort(N);

}

void topologicalSort(int N){
    vector<vector<int>> parts(N+1, vector<int>(N+1,0));
    queue<int> q;

    for(int i=1;i<N+1;i++){
        if(indegree[i] == 0){
            parts[i][i] = 1;
            q.push(i);
        }
    }

    while(!q.empty()){
        int cur = q.front(); q.pop();
        
        for(const auto& [next,cnt]: graph[cur]){
            for(int i=1;i<=N;i++){
                // dp 느낌
                parts[next][i] += parts[cur][i] * cnt;
            }
            if(--indegree[next] == 0){
                q.push(next);
            }
        }
    }

    for(int i=1;i<=N;i++){
        if(parts[N][i] == 0) continue;
        cout << i << " " << parts[N][i] << "\n";
    }

}