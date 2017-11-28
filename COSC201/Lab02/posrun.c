
// posrun.c
//
// Chris Nevison
// July 21, 2017
//
// Find the longest consecutive run of positive values in an array.

long long int posrun(long long int list[], long long int size)
{
    long long int count = 0;
    long long int maxrun = 0;
    for (long long int k = 0; k < size; k++)
    {
		if (list[k] > 0)
		{
	   		count++;
		}
		else
		{
		    count = 0;
		}
		if (count > maxrun)
		{
		    maxrun = count;
		}
    }
    return maxrun;
}
