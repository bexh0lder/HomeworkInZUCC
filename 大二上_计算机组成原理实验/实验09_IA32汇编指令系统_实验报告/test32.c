#include<stdio.h>

int exchange(int *xp, int y)
{
	int x = *xp;
	*xp = y;
	return x;
}
void main()
{
	int a = 5;
	int b = 10;
	a = exchange(&b, a);
}
