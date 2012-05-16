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
	lw $s3, b
	beq $0, $s3, noCorCir_8
	j noCorCir_7
noCorCir_8 :
	lw $s3, c
	beq $0, $s3, noCorCir_6
	j noCorCir_7
noCorCir_7 :
	lw $s3, a
	beq $0, $s3, noCorCir_6
	j fin_0
noCorCir_6 :
	ori $s3, $0, 2
	lw $s4, d
	sub $s3, $s4, $s3
	bgez $s3, si_5
	j fin_0
si_5: 
	la $s3, d
	lw $s4, d
	ori $s5, $0, 1
	sub $s4, $s4, $s5
	sw $s4,0($s3)
fin_0: 
	li $v0, 10
	syscall

