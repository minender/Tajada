	.data
	b1: .space 4
	a1: .space 4
	c1: .space 4
	.text
main:
	la $s3, a1
	ori $s4, $0, 2
	sw $s4,0($s3)
instr_1: 
	la $s3, b1
	lw $s4, a1
	sw $s4,0($s3)
instr_2: 
	la $s3, c1
	ori $s4, $0, 1
	sw $s4,0($s3)
instr_3: 
	la $s3, a1
	lw $s4, b1
	lw $s5, c1
	add $s4, $s4, $s5
	ori $s5, $0, 2
	div $s4, $s5
	mflo $s4
	lw $s5, a1
	mult $s4, $s5
	mflo $s4
	lw $s5, c1
	sub $s4, $s4, $s5
	sw $s4,0($s3)
fin_0: 
	li $v0, 10
	syscall

