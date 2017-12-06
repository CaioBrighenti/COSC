// Arraysum.s
// Compute the sum of the elements in an array.

// Student:	Caio Brighenti		Date 12/06/2017

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
	sub	X10, X10, 32			ldur	X11, [X10, #-8]	  // X11 = 1st A[k]
	add	X12, X10, X9			nop
	add X14, x12, X9			ldur	X13, [X12, #24]	  // X13 = 1st B[k]
	nop							ldur	X15, [X12, #16]	  // X15 = 2nd B[k]
	add	X13, X13, X11 			ldur	X16, [X10, #16]   // X16 = 2nd A[k]
	nop							stur	X13, [X14, #24]	  // Store 1st C[k]						
	nop							ldur	X13, [X12, #8]	  // X13 = 3rd B[k]
	add	X15, X15, X16			ldur	X11, [X10, #8]	  // X11 = 3rd A[k]
	nop							stur	X15, [X14, #16]	  // Store 2nd C[k]			
	nop							ldur	X15, [X12, #0]	  // X15 = 4th B[k]  
	add	X13, X13, X11			ldur	X16, [X10, #0]	  // X16 = 4th A[k]
	sub	X12, X10, X1			stur	X13, [X14, #8]	  // Store 3rd C[k]
	add	X15, X15, X16			nop
	cbnz	X12, loop			stur	X15, [X14, #0]	  // Store 4th C[k]	
	stall if taken
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	br	X30

// Author: Chris Nevison
//	    July 28, 2017
// Modified by: Caio Brighenti