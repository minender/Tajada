	.data
	i: .space 4
	.text
main :
	la $s3, i
	ori $s4, $0, 0
	sw $s4,0($s3)
instr_1: 
cond_3: 
	lw $s3, i
	ori $s4, $0, 20
	sub $s3, $s3, $s4
	bltz $s3, bloque_2
	j fin_0
bloque_2: 
	la $s3, i
	lw $s4, i
	ori $s5, $0, 1
	add $s4, $s4, $s5
	sw $s4,0($s3)
	j cond_3
fin_0: 
	li $v0, 10
	syscall

