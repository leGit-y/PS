#include <iostream>
#include <queue>
using namespace std;
#define MAX_N 30


int L, R, C;
char arr[MAX_N][MAX_N][MAX_N];
int visited[MAX_N][MAX_N][MAX_N];

class Pos{
    public:
        int z, x, y;
        Pos(): x(-1), y(-1), z(-1) {}          // 기본값 -1
        Pos(int _z,int _x,int _y): z(_z), x(_x), y(_y) {}
};

bool isInRange(int x, int y, int z){
    if(x < 0 || x >= R)
        return false;
    if(y < 0 || y >= C)
        return false;
    if(z < 0 || z >= L)
        return false;
    
    return true;
}

int bfs(Pos start, Pos end){
    int dx[] = {-1,1,0,0,0,0};
    int dy[] = {0,0,-1,1,0,0};
    int dz[] = {0,0,0,0,-1,1};

    queue<Pos> q;
    q.push(start);
    visited[start.z][start.x][start.y] = 1;

    while(!q.empty()){
        Pos now = q.front();
        q.pop();
        if(now.z == end.z && now.x == end.x && now.y == end.y)
            return visited[now.z][now.x][now.y];

        for(int i=0;i<6;i++){
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            int nz = now.z + dz[i];

            if(!isInRange(nx,ny,nz)) continue;
            if(visited[nz][nx][ny]) continue;
            
            if(arr[nz][nx][ny] == '.'){
                visited[nz][nx][ny] = visited[now.z][now.x][now.y] + 1;
                q.push(Pos(nz,nx,ny));
            }
        }


    }

    return -1;

}



int main(){

    while(true){
        cin >> L >> R >> C;

        if(L == 0 && R == 0 && C == 0) break;

        Pos start, end;

        for(int i=0;i<L;i++){
            for(int j=0;j<R;j++){
                for(int k=0;k<C;k++){
                    cin >> arr[i][j][k];
                    if(arr[i][j][k] == 'S'){
                        start = {i,j,k};
                    }
                    if(arr[i][j][k] == 'E'){
                        end = {i,j,k};
                        arr[i][j][k] = '.'; // 도착지점은 방문할 수 있게 변경
                    }
                    
                }
            }
        }

        fill_n(&visited[0][0][0], L*R*C, 0);

        int result = bfs(start, end);
        if(result == -1)
            cout << "Trapped!" << endl;
        else{
            cout << "Escaped in " << result-1 << " minute(s)." << endl;
        }
    }

}

