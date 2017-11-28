// test.s
// 
// Takes the integer (32 bit) array of length 4 in data memory and replaces
// it with the array of partial sums
// 
// X10 holds the constant 16, the address after the end of the array
// X14 holds the constant 4, for incrementing the array pointer
// X9 holds k, the array pointer
// X11 holds the partial sum, initialized to 0
// X12 holds A[k] for each iteration
// X13 holds the difference of X9 and X10; the loop terminates when X13 = 0

.data
A:	.word 0x6, 0x5 			// binary 110 and 101

        .text				// indicates start of code section
        .align  2			

main:
	ldur X9, [XZR, #0]		// X9 = 6
	ldur X10, [XZR, #4]		// X10 = 5
	add X11, X9, X10		// X11 = 11 (0xb)
	cbz X11, endprg			// should not branch
	stur X11, [XZR, #8]
	sub X11, X11, X11
	cbz X11, skip			// should branch
	stur X11, [XZR, #8]		// should not execute
	b afterSkip			// should not execute
skip:	
	sub X12, X9, X10		// X12 = 1
	stur X12, [XZR, #12]
afterSkip:	
	and X13, X9, X10		// X13 = 4 (100 binary)
	or X14, X9, X10			// X14 = 7 (111 binary)
	b skipAgain
	stur X13, [XZR, #12]		// should not execute
skipAgain:
	stur X13, [XZR, #16]
	
endprg:b endprg				// "stop" -- loop here forever
