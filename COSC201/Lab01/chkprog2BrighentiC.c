/*
 * chkprog2.c
 * calls assembly language function prog2.s and
 * prints its return number.
 *
 * Caio Brighenti, September 6, 2017
 */

#include <stdio.h>
long long int prog2(void);


int main()
{
  long long int x;

  x = prog2();
  printf("Here is the result of prog2: %d \n", x);

  return 0;
}