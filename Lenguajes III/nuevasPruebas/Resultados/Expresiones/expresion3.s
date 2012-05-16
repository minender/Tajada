	.data
	b: .space 1
	a: .space 1
	d: .space 4
	c: .space 1
	.text
main :
	la $s3, a
	ori $s4, $0, 1
	sw $s4,0($s3)
instr_1: 
	la $s3, b
	lw $s4, a
	sw $s4,0($s3)
instr_2: 
	la $s3, c
	ori $s4, $0, 0
	sw $s4,0($s3)
instr_3: 
	la $s3, d
	ori $s4, $0, 1
	sw $s4,0($s3)
instr_4: 
	la $s3, a
	lw $s4, b
	bne $s4,$0, CorCir_8
	nop
	lw $s4, c
CorCir_8 :
	beq $s4, $0, CorCir_7
	nop
	lw $s4, a
CorCir_7 :
	nor $s4, $s4, $0
	beq $s4, $0, CorCir_6
	nop
	ori $s4, $0, 2
	lw $s5, d
	sub $s4, $s5, $s4
	bgez $s4, menorI_9
	ori $s4, $0, 0
	j menorI_10
menorI_9: 
	ori $s4, $0, 1
menorI_10: 
CorCir_6 :
	sw $s4,0($s3)
instr_5: 
	lw $s3, a
	beq $0, $s3, fin_0
	j si_11
si_11: 
	la $s3, d
	lw $s4, d
	ori $s5, $0, 1
	sub $s4, $s4, $s5
	sw $s4,0($s3)
fin_0: 
	li $v0, 10
	syscall

