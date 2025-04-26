#include <iostream>
#include <queue>
#include <utility>
using namespace std;;

int n, m;
int cnt = 0;
int maxSize = 0;
vector<vector<int>> canvas, visited;
int dx[] = {-1,1,0,0};
int dy[] = {0,0,-1,1};

int dfs(int x, int y);

int main(){
    ios::sync_with_stdio(false); cin.tie(0);
    
    cin >> n >> m;

    canvas.assign(n, vector<int>(m));
    visited.assign(n, vector<int>(m));
    
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> canvas[i][j];
            visited[i][j] = 0;
        }
    }

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(visited[i][j]) continue;
            if(canvas[i][j] == 0) continue;
            
            int size = dfs(i,j);
            cnt++;
            if(size > maxSize){
                maxSize = size;
            }
        }
    }

    cout << cnt << "\n" << maxSize << "\n";

}

int dfs(int x, int y){
    queue<pair<int , int>> q;
    q.push({x,y});
    visited[x][y] = 1;

    int size = 1;

    while(!q.empty()){
        auto cur = q.front(); q.pop();
        int x = cur.first;
        int y = cur.second;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(visited[nx][ny]) continue;
            if(canvas[nx][ny] == 0) continue;

            q.push({nx,ny});
            visited[nx][ny] = 1;
            size++;
        }
    }
    
    return size;
}