	.arch armv8-a
	.file	"posrunDriver.c"
	.section	.rodata
	.align	3
.LC6:
	.string	"result 1: %d \n"
	.align	3
.LC7:
	.string	"result 2: %d \n"
	.align	3
.LC8:
	.string	"result 3: %d \n"
	.align	3
.LC9:
	.string	"result 4: %d \n"
	.align	3
.LC10:
	.string	"result 5: %d \n"
	.align	3
.LC11:
	.string	"result 6: %d \n"
	.align	3
.LC12:
	.string	"result 7: %d \n"
	.align	3
.LC13:
	.string	"result 8: %d \n"
	.text
	.align	2
	.global	main
	.type	main, %function
main:
	
	stp	x29, x30, [sp, -16]!
	
	adrp	x0, .LC0
	add	x0, x0, :lo12:.LC0
	mov	x1, 10
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC6
	add	x0, x0, :lo12:.LC6
	bl	printf

	adrp	X0, .LC14
	add	X0, X0, :lo12:.LC14
	mov	x1, 10
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC7
	add	x0, x0, :lo12:.LC7
	bl	printf
	
	adrp	x0, .LC1
	add	X0, X0, :lo12:.LC1
	mov	x1, 10
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC8
	add	x0, x0, :lo12:.LC8
	bl	printf
	
	adrp	x0, .LC2
	add	X0, X0, :lo12:.LC2
	mov	x1, 10
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC9
	add	x0, x0, :lo12:.LC9
	bl	printf
	
	adrp	x0, .LC3
	add	X0, X0, :lo12:.LC3
	mov	x1, 10
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC10
	add	x0, x0, :lo12:.LC10
	bl	printf
	adrp	x0, .LC4
	add	X0, X0, :lo12:.LC4
	mov	x1, 5
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC11
	add	x0, x0, :lo12:.LC11
	bl	printf
	
	adrp	x0, .LC5
	add	X0, X0, :lo12:.LC5
	mov	x1, 20
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC12
	add	x0, x0, :lo12:.LC12
	bl	printf
	
	adrp	x0, .LC0
	add	X0, X0, :lo12:.LC0
	mov	x1, -1
	bl	posrun
	
	mov	x1, x0
	adrp	x0, .LC13
	add	x0, x0, :lo12:.LC13
	bl	printf
	
	mov	w0, 0


	ldp	x29, x30, [sp], 16
	br	X30
	.size	main, .-main
	.section	.rodata
	.align 3
.LC14:
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.xword	0
	.align	3
.LC0:
	.xword	3
	.xword	0
	.xword	1
	.xword	2
	.xword	6
	.xword	-2
	.xword	4
	.xword	7
	.xword	3
	.xword	7
	.align	3
.LC1:
	.xword	-1
	.xword	-2
	.xword	-3
	.xword	-4
	.xword	-5
	.xword	-6
	.xword	-7
	.xword	-8
	.xword	-9
	.xword	-10
	.align	3
.LC2:
	.xword	1
	.xword	2
	.xword	3
	.xword	4
	.xword	5
	.xword	6
	.xword	7
	.xword	8
	.xword	9
	.xword	10
	.align	3
.LC3:
	.xword	1
	.xword	5
	.xword	0
	.xword	3
	.xword	4
	.xword	0
	.xword	6
	.xword	-7
	.xword	8
	.xword	9
	.xword	10
	.align	3
.LC4:
	.xword	3
	.xword	0
	.xword	1
	.xword	2
	.xword	6
	.align	3
.LC5:
	.xword	6
	.xword	-2
	.xword	4
	.xword	7
	.xword	3
	.xword	7
	.xword	0
	.xword	0
	.xword	-1
	.xword	-2
	.xword	1
	.xword	2
	.xword	3
	.xword	4
	.xword	5
	.xword	6
	.xword	-7
	.xword	8
	.xword	9
	.xword	10
	.text
	.ident	"GCC: (SUSE Linux) 6.2.1 20160826 [gcc-6-branch revision 239773]"
	.section	.note.GNU-stack,"",@progbits