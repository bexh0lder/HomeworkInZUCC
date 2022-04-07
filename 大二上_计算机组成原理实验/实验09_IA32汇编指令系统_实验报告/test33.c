#include <stdio.h>
void f1(int *x, int *y, int *z)
{
    int a = *x;
    int b = *y;
    int c = *z;
    *y = a;
    *z = b;
    *x = c;
}

int main() {
    int x = 1;
    int y = 2;
    int z = 3;
    f1(&x, &y, &z);
    return 0;
}

