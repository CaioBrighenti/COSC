// Arraysum.s
// Compute the sum of the elements in an array.

// Student:	Your Name		Date

//	void sumArray(long long int len, long long int[] A)
//	{
//	    long long int k = len;
//	    do
//	    {
//	        k--;
//	        C[k] = A[k] + B[k];
//	    } while (k != 0);
//	}

// Preconditions:	
//
//   len: length of array > 0
//   A:   start of array A of integers
//   Arrays B and C follow immediately in memory with same length
// Postconditions:	
//   result array C is A + B, element-by-element
//


        .text				// indicates start of code section
        .align  2			
        .global sumArray		// makes visible to other functions
        .type   sumArray, %function	// indicates this defines a function

//	X0 = len, length of arrays
//	X1 = base address of array A
//	X2 = base address of array B
//	X3 = base address of array C

// Code uses pointer hopping, X10 starts at address just past array A (A[len]) and jumps backwards
// through array A, being at address of A[0] on last pass.
// Addresses of corresponding entries on arrays B and C are spaced X9 = 8*len bytes apart.

/////////////////////////////////////////////////////////////////////////////////////

	lsl	X9, X0, 3		// multiply length by 8 to make X9 = length in bytes
	add	X10, X9, X1		// X10 is address after end of array A (so it is address of B[0])

loop:	
	sub	X10, X10, 8		// k--	, first time through this makes X10 address of A[len-1]
	ldur	X11, [X10, #0]		// X11 = A[k]
	add	X12, X10, X9		// X12 = address of B[k]
	ldur	X13, [X12, #0]		// X13 = B[k]
	nop
	add	X13, X13, X11		// X13 = A[k] + B[k]
	add	X12, X12, X9		// X12 = address of C[k]
	stur	X13, [X12, #0]		// C[k] = A[k] + B[k]

	sub	X12, X10, X1		// Is X10 at first element of A?
	nop
	cbnz	X12, loop		// if not branch to loop
	stall if taken
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	br	X30

// Author: Chris Nevison
//	    July 28, 2017
// Modified by: Caio Brighenti