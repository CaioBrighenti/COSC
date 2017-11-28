// towers.c
// Chris Nevison	
// July 24, 2017


long long int towers(long long int numDiscs, long long int start, long long int goal) 
{ 
    long long int steps, peg; 

    if(numDiscs = 1)
    { 
	return 1; 
    } 
    else
    { 
	peg = 6 - start - goal;		// Calculate intermediate peg.
	steps  = towers(numDiscs-1, start, peg); 
	steps += towers(1, start, goal); 
	steps += towers(numDiscs-1, peg, goal); 
	return steps; 
    } 
} 

