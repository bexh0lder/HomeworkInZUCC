#include <stdio.h>
#include <cstring>
#include <algorithm>

using namespace std;
int parent[1050];
int flag[1050];//爱好归属者
int num[1050];//统计最后集群
int sum;
bool cmp(int x,int y){
    return x > y;
}
void Init()
{
    for(int i = 1; i <= 1050; i++){
        parent[i] = i;
    }
    return;
}
int get_parent(int x){
    if(parent[x] == x)
        return x;
    else{
        parent[x] = get_parent(parent[x]);
        return parent[x];
    }
}
void Merge(int x, int y){
    int t1 = get_parent(x);
    int t2 = get_parent(y);
    parent[t2] = t1;
//    printf("pre%d = %d\n", t2, t1);
    return;
}
int main() {
    int n;
    int h;
    int k;

    Init();

    scanf("%d", &n);
    for(int i = 1; i <= n; i++){
        scanf("%d", &k);
        getchar();
        for(int j = 1; j <= k; j++){
            scanf("%d", &h);
            if(flag[h] == 0) flag[h] = i;
            else Merge(flag[h],i);
        }
    }
    memset(flag,0, sizeof(flag));
    for(int i = 1; i <= n; i++){
        if(get_parent(i) == i) {
            flag[i] = ++sum;
        }
    }
    for(int i = 1; i <= n; i++){
        num[flag[get_parent(i)]]++;
    }
    printf("%d\n",sum);
    sort(num+1,num+sum+1,cmp);
    for(int i = 1; i < sum; i++){
        printf("%d ",num[i]);
    }
    printf("%d", num[sum]);
    return 0;
}
