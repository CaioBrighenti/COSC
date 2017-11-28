/*
 * chkprog1.c
 * calls assembly language function prog1.s and
 * prints its return number.
 *
 * Chris Nevison, March 22, 2017
 */

#include <stdio.h>
long long int prog1(void);


int main()
{
  long long int x;

  x = prog1();
  printf("Here is the result of prog1: %d \n", x);

  return 0;
}