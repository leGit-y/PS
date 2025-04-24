#include <iostream>
#include <algorithm>
#include <map>
#include <queue>
#include <set>

using namespace std;

map<string, int> idxMap;
vector<int> indegree;
vector<map<string, int>> graph;

void topologicalSort(int N);
string findNameByIdx(int idx);

int main(){
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    
    int N, M;
    cin >> N;

    for(int i=0;i<N;i++){
        string name;
        cin >> name;
        idxMap.insert({name, i});
    }

    graph.assign(N, map<string, int>());
    indegree.assign(N, 0);

    cin >> M;

    for(int i=0;i<M;i++){
        string X,Y;
        cin >> X >> Y;
        
        int Yidx = idxMap[Y];
        int Xidx = idxMap[X];
        
        // Y -> X 방향으로 그래프 생성
        graph[Yidx].insert({X,Xidx});
        // 진입차수 카운트
        indegree[Xidx]++;
    }

    // 각 가문 개수 및 시조들 이름 출력
    int K = 0;
    set<string> rootNames;
    for(int i=0;i<N;i++){
        if(indegree[i] == 0){
            K++;
            string name = findNameByIdx(i);
            rootNames.insert(name);
        }
    }
    cout << K << "\n";
    for(const auto& name: rootNames){
        cout << name << " ";
    }
    cout << "\n";

    
    topologicalSort(N);

}

string findNameByIdx(int idx){
    for(const auto& i: idxMap){
        if(i.second == idx){
            return i.first;
        }
    }
    return "";
}

void topologicalSort(int N){
    queue<int> q;
    vector<vector<string>> childs(N);
    
    for(int i=0;i<N;i++){
        if(indegree[i] == 0){
            q.push(i);
        }
    }

    while(!q.empty()){
        int cur = q.front();
        q.pop();

        // 부모 -> 자식 순회
        for(const auto& g: graph[cur]){
            string name = g.first;
            int idx = g.second;

            indegree[idx]--;
            if(indegree[idx] == 0){
                childs[cur].push_back(name);
                q.push(idx);
            }
        }
    }
    
    // output
    for(const auto& m: idxMap){
        string name = m.first;
        int idx = m.second;

        cout << name <<  " " << childs[idx].size();

        for(const auto& child: childs[idx]){
            cout << " " << child;
        }

        cout << "\n";
    }



}