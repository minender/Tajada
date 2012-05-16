	.data
	d: .space 4
	.text
main :
	la $s3, d
	ori $s4, $0, 100
	sw $s4,0($s3)
instr_1: 
cond_3: 
	ori $s3, $0, 3
	lw $s4, d
	sub $s3, $s3, $s4
	bltz $s3, bloque_2
	j fin_0
bloque_2: 
	la $s3, d
	lw $s4, d
	ori $s5, $0, 1
	sub $s4, $s4, $s5
	sw $s4,0($s3)
	j cond_3
fin_0: 
	li $v0, 10
	syscall

