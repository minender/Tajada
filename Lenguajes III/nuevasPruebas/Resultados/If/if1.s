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
	j noCorCir_4
noCorCir_4 :
	j si_3
si_3: 
	la $s3, b
	lw $s4, d
	ori $s5, $0, 30
	add $s4, $s4, $s5
	sw $s4,0($s3)
fin_0: 
	li $v0, 10
	syscall

