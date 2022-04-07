#include <stdio.h>
#include <string.h>
#define M 2000//定义最大节点数
int key;//统计边数为奇数的点个数
int Graph[M];//统计每个点连接的边数
int Edge[M][M];//判断两个点是否连通的数组
int visit[M];//标记访问过的点
int n, m;
//判断是否连通
void Connect(int x){
    visit[x] = 1;
    for(int i = 1; i <= n; ++i){
        if(Edge[x][i] && !visit[i])
            Connect(i);//运用DFS遍历每一个点，经过的点就标记
    }
}
//判断是否能完成一笔画
bool judge(){
    key = 0;
    memset(Graph,0,sizeof(Graph));
    memset(Edge,0,sizeof(Edge));
    int x, y;
    for(int i = 1; i <= m; ++i){
        scanf("%d %d", &x, &y);
        Edge[x][y] = 1;
        Edge[y][x] = 1;
        Graph[x]++;
        Graph[y]++;
    }
    memset(visit,0,sizeof(visit));
    Connect(1);//从第一个点开始遍历
    for(int i = 1; i <= n; ++i){
        if(!visit[i]) return false;//如果有点没有被访问就证明不是连通图，直接返回false
        if(Graph[i] % 2 != 0)
            key++;
    }
    if(key == 0 || key == 2)//如果边数为奇数的点个数不是0或2则证明无法完成一笔画
        return true;
    else
        return false;
}
int main() {
    scanf("%d %d", &n, &m);
    if(judge())
        printf("Y");
    else
        printf("N");
}
