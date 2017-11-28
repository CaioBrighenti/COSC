// towers.s - Solve the Towers of Hanoi puzzle.
// Return the total number
// of moves needed to solve the puzzle.
//
// Author:	Chris Nevison	September 9, 2017


// Solution.

        .text				// indicates start of code section
        .align  2			
        .global towers			// makes visible to other functions
        .type   towers, %function	// indicates this defines a function

// Preconditions:	
//   1st parameter (X0) numDiscs, number of discs to move
//   2nd parameter (X1) start, starting peg 
//   3rd parameter (X2) goal, ending peg
// Postconditions:
//   return value (X0)  steps, total number of steps required to solve puzzle

//	Use X19 to store steps needed until assigning to X0 for return value
//	Use X20 store intermediate peg (1, 2, or 3) for this move
//	Use other registers, X21, etc., to keep values across function calls
//
//	Use X28 as stack pointer, not SP

towers:	
	sub X28, X28, 48		// make space on stack by adjusting X28
							// preserve registers used by this function
	stur X30, [X28, 40]  	// store return address
	stur X23, [X28, 32]  	// store X23/goal
	stur X22, [X28, 24]   	// store X22/start
	stur X21, [X28, 16]   	// store X21/numDiscs
	stur X20, [X28, 8]   	// store X20/peg
	stur X19, [X28, 0]   	// store X19/steps

							// preserve parameters in registers
	add X21, XZR, X0 // X21 = numDiscs 
	add X22, XZR, X1 // X22 = start
	add X23, XZR, X2 // X23 = goal

if:	
	sub X9, X21, 1		// numDiscs = 1 ?
	cbnz X9, else		// if not, go to else

	add X0, XZR, 1		// return value = 1
	b endif				// branch past else

else: 
	add X20, XZR, 6		// X20 = peg = 6
	sub X20, X20, X22	// peg = peg - start
	sub X20, X20, X23	// peg = peg - goal = 6 - start - goal

	subs X0, X21, 1		// 1st parameter = numDiscs - 1
	b.le skip1          // skip recursive call if numDiscs <= 1
	add X1, X22, XZR	// 2nd parameter = start
	add	X2, X20, XZR	// 3rd parameter = peg
	bl towers			// recursive call to towers
	add X19, X0, XZR	// X19 = steps = result
	b endskip1			// branch to end of skip condition
skip1:
	add X19, X19, 1    // steps = steps + 1
endskip1:	

	add X19, X19, 1    // steps = steps + 1

	sub X0, X21, 1		// 1st parameter = numDiscs - 1
	add X1, X20, XZR	// 2nd parameter = peg
	add x2, X23, XZR	// 3rd parameter = goal
	bl towers			// recursive call to towers
	add X0, X0, X19		// return value = steps + result

endif:	
						// recover registers used by this function	
	ldur    X23, [X28, 32]
	ldur    X22, [X28, 24]
	ldur    X21, [X28, 16]
	ldur    X20, [X28, 8]
	ldur    X19, [X28, 0]
	
	ldur 	X30, [X28, 40]	// restore return address
	addi		X28, X28, 48	// restore stack pointer (X28)
	br 		X30				// return