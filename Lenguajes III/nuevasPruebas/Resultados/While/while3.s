	.data
	b: .space 4
	d: .space 4
	.text
main :
	la $s3, d
	ori $s4, $0, 100
	sw $s4,0($s3)
instr_1: 
	la $s3, b
	ori $s4, $0, 0
	sw $s4,0($s3)
instr_2: 
cond_4: 
	ori $s3, $0, 3
	lw $s4, d
	sub $s3, $s3, $s4
	bltz $s3, bloque_3
	j fin_0
bloque_3: 
	la $s3, d
	lw $s4, d
	ori $s5, $0, 1
	sub $s4, $s4, $s5
	sw $s4,0($s3)
instr_5: 
cond_7: 
	lw $s3, b
	lw $s4, d
	sub $s3, $s4, $s3
	bltz $s3, bloque_6
	j cond_4
bloque_6: 
	lw $s3, b
	lw $s4, d
	sub $s3, $s3, $s4
	bgez $s3, si_8
	j cond_7
si_8: 
	la $s3, b
	lw $s4, b
	ori $s5, $0, 1
	add $s4, $s4, $s5
	sw $s4,0($s3)
	j cond_7
	j cond_4
fin_0: 
	li $v0, 10
	syscall

