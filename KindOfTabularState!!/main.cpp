#include <iostream>

const  int N = 500;

int n,m;

char graph[N][N];
int state[N][N];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void dfs(int x, int y, int index){
    state[x][y] = index;
    for(int i = 0 ; i<4 ; i++){
        int xx = x + dx[i];
        int yy = y + dy[i];
        
        if(xx >= 0 && yy >= 0 && xx < n && yy < m && graph[xx][yy] == '.' && state[xx][yy] == -1){
            dfs(xx, yy, index);
        }
    }
}

int main() {
    std :: cin >> n >> m;

    for(int i = 0 ; i<n ; i++)
        for(int j = 0 ; j<m ; j++){
            std :: cin >> graph[i][j];
            state[i][j] = -1;
        }

    int index = 0;
    for(int i = 0 ; i<n ; i++)
        for(int j = 0 ; j<m ; j++){
            if(state[i][j] == -1 && graph[i][j] == '.'){
                dfs(i, j, index);
                index++;
            }
        }

    int q;
    std :: cin >> q;
    while(q--){
        int x1, y1, x2, y2;
        std :: cin >> x1 >> y1 >> x2 >> y2;

        if(state[x1-1][y1-1] == state[x2-1][y2-1])
            std :: cout << "YES\n";
        else
            std :: cout << "NO\n";
    }

    return 0;
}