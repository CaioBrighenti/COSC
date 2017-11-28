// posrunDriver.c
// Author: Chris Nevison 
// July 21, 2017

#include <stdio.h>
long long int posrun(long long int[], long long int);

int main()
{
    long long int list[] = {3, 0, 1, 2, 6, -2, 4, 7, 3, 7};
    printf("result 1: %d \nâ€,   posrun(list, 10) );
    long long int  list[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    printf("result 1: %d \nâ€,   posrun(list1, 10) );
    long long int  list2[] = {-1, -2, -3, -4, -5, -6, -7, -8, -9, -10 };
    printf("result 1: %d \nâ€,   posrun(list2, 10) );
    long long int  list3[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    printf("result 1: %d \nâ€,   posrun(list3, 10) );
    long long int  list4[] = {1, 5, 0, 3, 4, 0, 6, -7, 8, 9, 10};
    printf("result 1: %d \nâ€,   posrun(list4, 10) );
    long long int  list5[] = {3, 0, 1, 2, 6};
    printf("result 1: %d \nâ€,   posrun(list5, 5) );
    long long int  list6[] = {6, -2, 4, 7, 3, 7, 0, 0, -1, -2, 1, 2, 3, 4, 5, 6, -7, 8, 9, 10};
    printf("result 1: %d \nâ€,   posrun(list6, 20) );
    long long int  list7[] = {3, 0, 1, 2, 6, -2, 4, 7, 3, 7};
    printf("result 1: %d \nâ€,   posrun(list7, -1) );
    return 0;
}


