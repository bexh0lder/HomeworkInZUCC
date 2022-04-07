#include<stdio.h>
bool orz = false;//用于实现在每一种情况输出后隔一行再输出下一种情况的标志
struct state{
    int sit;
};
//使用二进制分布来判断人狼羊菜
void Init(struct state *start, int *target){
    char x;
    int sum = 0;
    scanf("%c",&x);
    if(x == 'M') sum += 1 << 3;
    scanf("%c",&x);
    if(x == 'W') sum += 1 << 2;
    scanf("%c",&x);
    if(x == 'G') sum += 1 << 1;
    scanf("%c",&x);
    if(x == 'C') sum += 1;
    while (x != '\n')
        x = getchar();
    start->sit = sum;
    *target = 0;
    scanf("%c",&x);
    if(x == 'M') *target += 1 << 3;
    scanf("%c",&x);
    if(x == 'W') *target += 1 << 2;
    scanf("%c",&x);
    if(x == 'G') *target += 1 << 1;
    scanf("%c",&x);
    if(x == 'C') *target += 1;
    while (x != '\n')
        x = getchar();
}
//判断是否是非法情况
bool IsWrong(int test){
    int p, w, g, c;
    p = (test >> 3) % 2;
    w = (test >> 2) % 2;
    g = (test >> 1) % 2;
    c = test % 2;
    if((p != w && w == g) || (p != g && g == c))
        return true;
    else return false;
}
//判断是否重复
bool IsRepeat(int test,struct state *start, int flag){
    for(int i = 0; i < flag; ++i){
        if(start[i].sit == test)
            return true;
    }
    return false;
}
//打印结果
void print(struct state *start, int flag){
    if(orz) printf("\n");
    orz = true;
    for(int i = 0; i < flag; ++i){
        int p, w, g, c;
        char out[13];
        p = (start[i].sit >> 3) % 2;
        w = (start[i].sit >> 2) % 2;
        g = (start[i].sit >> 1) % 2;
        c = start[i].sit % 2;
//        printf("%d%d%d%d\n", p, w, g, c);
        out[4] = out[7] = ' ';
        out[12] = '\0';
        if(p){
            out[0] = 'M';
            out[8] = '.';
            out[5] = '-';
            out[6] = '>';
        }
        else{
            out[0] = '.';
            out[8] = 'M';
            out[5] = '<';
            out[6] = '-';
        }
        if(w){
            out[1] = 'W';
            out[9] = '.';
        }
        else{
            out[1] = '.';
            out[9] = 'W';
        }
        if(g){
            out[2] = 'G';
            out[10] = '.';
        }
        else{
            out[2] = '.';
            out[10] = 'G';
        }
        if(c){
            out[3] = 'C';
            out[11] = '.';
        }
        else{
            out[3] = '.';
            out[11] = 'C';
        }
        printf("%s\n",out);
    }
}
//flag即表示寻找第flag步操作
void Move(int target,struct state *start, int flag){
//    printf("%d\n", start[0].sit);

    if(start[flag-1].sit == target){
        print(start, flag);
    }
    int p, w, g, c;
    p = (start[flag-1].sit >> 3) % 2;
    w = (start[flag-1].sit >> 2) % 2;
    g = (start[flag-1].sit >> 1) % 2;
    c = start[flag-1].sit % 2;

    int sum;
//    直接过河(不带任何东西)
    sum = (!p << 3) + (w << 2) + (g << 1) + c;
    if(!IsRepeat(sum, start, flag) && !IsWrong(sum)){
        start[flag].sit = sum;
        Move(target, start, flag+1);
    }
//    带狼过河
    if(p == w){
        sum = (!p << 3) + (!w << 2) + (g << 1) + c;
        if(!IsRepeat(sum, start, flag) && !IsWrong(sum)){
            start[flag].sit = sum;
            Move(target, start, flag+1);
        }
    }
//    带羊过河
    if(p == g){
        sum = (!p << 3) + (w << 2) + (!g << 1) + c;
        if(!IsRepeat(sum, start, flag) && !IsWrong(sum)){
            start[flag].sit = sum;
            Move(target, start, flag+1);
        }
    }
//    带菜过河
    if(p == c){
        sum = (!p << 3) + (w << 2) + (g << 1) + !c;
        if(!IsRepeat(sum, start, flag) && !IsWrong(sum)){
            start[flag].sit = sum;
            Move(target, start, flag+1);
        }
    }
}
int main() {
    struct state state[1000];
    int target;
    Init(&state[0], &target);
    if(state[0].sit == target || IsWrong(target) || IsWrong(state[0].sit))
        printf("None");
    else
        Move(target,state, 1);
//    printf("%d\n",state[1].sit);
//    printf("%d\n",target);
    return 0;
}
