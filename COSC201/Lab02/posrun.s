
// posrun.s 
// An ARM assembly language compilation of the C program posrun.c
// Author:	Caio Brighenti	September 13th, 2017


        .text				// indicates start of code section
        .align  2			
        .global posrun			// makes visible to other functions
        .type   posrun, %function	// indicates this defines a function

// Preconditions:
//   1st parameter (X0) is the address of the first element of the array
//   2nd parameter (X1) is the size of the array
// Postconditions:	
//   result (X0)   is the length of the longest consecutive run of positive
//     numbers in the array
//
// Register usage
//	X9 is count
//	X14 is maxrun
//	X10 is k
//	X11, X12, X13 for array entry address and value 

posrun: 
	add	X9, XZR, XZR		// X9, count = 0
	add X14, XZR, XZR	// X14, maxrun = 0
	add X10, XZR, XZR	// X10, k = 0
for:	
	subs XZR, X10, X1	// set flags for k - length
	b.ge endfor	// if k >= length, goto endfor
	lsl X11, X10, #3	// X11 = 8*k (calculate address of list[k])
	add X12, XZR, X11 // X12 = address of list[k]
	ldur X13, [X12, #0]	// X13 = list[k]
	subs XZR, XZR, X13	// set flags for 0 - list[k]
	b.ge else1	// if list[k] <= 0, goto else1
if1:
	addi X9, X9, #1	// count++
	br endif1 	// branch to end of if
else1:
	add X9, XZR, XZR // count = 0
endif1:	
	subs XZR, X14, X9	// set flags for maxrun - count
	b.ge endif2	// if maxrun >= count, branch to end of if
if2:
	add X14, XZR, X9	// maxrun = count
endif2:
	addi X10, X10, #1	// k++
	br for	// back to top of loop
endfor:	
	add X0, X14, XZR		// maxrun into X0
	br X30				// return
