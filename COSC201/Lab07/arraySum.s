// ArraySum
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
A:	.word 0x3, 0xf, 0x14, 0x2
sxtn:	.word 0x10
four:	.word 0x4

        .text				// indicates start of code section
        .align  2			

main:

	ldur X10, [XZR, #16]		// 16 is address of “sxtn” (16), so X10 = 16
					// the first address after the array
	ldur X14, [XZR, #20]		// 20 is the address of “four”, so X14 = 4
	add X9, XZR, XZR		// set X9 = k = base address of 0 (in logisim)
	add X11, XZR, XZR		// set X11 = sum = 0

loop: 	sub X13, X9, X10		// Check to see if k is past end of array
	cbz X13, endprg			// if so, branch to end of program
	lw X12, [X9, #0]		// X12 = A[k]
	add X11, X11, X12		// sum = sum + A[k]
	sw X11, [X9, #0]		// A[k] = sum
	add X9, X9, X14			// k = k+4
	b loop				// jump to top of loop
	
endprg:b endprg				// "stop" -- loop here forever
