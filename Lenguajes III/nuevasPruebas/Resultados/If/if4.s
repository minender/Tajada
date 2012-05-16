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
	lw $s3, b
	lw $s4, d
	sub $s3, $s4, $s3
	bltz $s3, si_3
	j sino_4
si_3: 
	la $s3, b
	lw $s4, d
	ori $s5, $0, 30
	add $s4, $s4, $s5
	sw $s4,0($s3)
sino_4: 
cond_6: 
	lw $s3, d
	ori $s4, $0, 120
	sub $s3, $s4, $s3
	bgez $s3, bloque_5
	j noCorCir_7
noCorCir_7 :
	lw $s3, d
	ori $s4, $0, 30
	beq $s3, $s4, bloque_5
	j fin_0
bloque_5: 
	la $s3, d
	lw $s4, d
	ori $s5, $0, 1
	add $s4, $s4, $s5
	sw $s4,0($s3)
	j cond_6
fin_0: 
	li $v0, 10
	syscall

