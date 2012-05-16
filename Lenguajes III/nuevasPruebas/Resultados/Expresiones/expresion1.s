	.data
	b: .space 4
	a: .space 4
	c: .space 4
	.text
main :
	la $s3, a
	ori $s4, $0, 2
	sw $s4,0($s3)
instr_1: 
	la $s3, b
	lw $s4, a
	sw $s4,0($s3)
instr_2: 
	la $s3, c
	ori $s4, $0, 1
	sw $s4,0($s3)
instr_3: 
	la $s3, a
	lw $s4, b
	lw $s5, c
	add $s4, $s4, $s5
	ori $s5, $0, 2
	div $s4, $s4, $s5
	mflo $s4
	lw $s5, a
	mult $s4, $s4, $s5
	mflo $s4
	lw $s5, c
	sub $s4, $s4, $s5
	sw $s4,0($s3)
fin_0: 
	li $v0, 10
	syscall

