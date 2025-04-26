#include <iostream>
#include <vector>
#include <queue>
#include <utility>
using namespace std;

int M, N; 
int dx[] = {-1,1,0,0};
int dy[] = {0,0,-1,1};
vector<vector<int>> maze;

struct locWithDist{
    int dist;
    int x,y;
};
struct Compare{
    bool operator()(locWithDist& a, locWithDist& b){
        return a.dist < b.dist;
    }
};

int dijkstra(int sx, int sy){
    vector<vector<int>> dist(N, vector<int>(M));
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            dist[i][j] = 10001;
        }
    }
    priority_queue<locWithDist, vector<locWithDist>, Compare> pq;
    pq.push({0,sx,sy});
    dist[sx][sy] = 0;

    while(!pq.empty()){
        auto cur = pq.top(); pq.pop();
        if(dist[cur.x][cur.y] < cur.dist) continue;
        
        for(int i=0;i<4;i++){
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            int cost = -1;
            if(maze[nx][ny] == 1){
                cost = dist[cur.x][cur.y] + 1;
            }else{
                cost = dist[cur.x][cur.y];
            }
            if(cost < dist[nx][ny]){
                dist[nx][ny] = cost;
                pq.push({cost, nx,ny});
            }
        }
        

        

    }
    return dist[N-1][M-1];

}

int main(){
    
    cin >> M >> N;
    maze.assign(N, vector<int>(M));

    for(int i=0;i<N;i++){
        string line;
        cin >> line;
        for(int j=0;j<M;j++){
            maze[i][j] = line[j] - '0';
        }
    }

    int answer = dijkstra(0,0);

    cout << answer;
}

