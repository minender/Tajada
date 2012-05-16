	.data
	b1: .space 4
	a1: .space 4
	mensaje0: .asciiz "error: indice fuera de rango en arreglo"
	.text
proc1f: 
	sub $sp, $sp, 4
	sw $fp, 0($sp)
	or $fp, $0, $sp
	sub $sp, $sp, 4
	sub $sp, $sp, 4
	sub $sp, $sp, 4
	lw $s0, a1
	ori $s1, $0, 2
	beq $s0, $s1, si_2
	j instr_1
si_2: 
	lw $fp, 0($sp)
	add $sp, $sp, 4
	jr $ra
instr_1: 
	la $s0, c1
	ori $s1, $0, 2
	sw $s1,0($s0)
instr_3: 
	la $s0, a1
	ori $s1, $0, 3
	sw $s1,0($s0)
instr_4: 
	la $s0, b1
	ori $s1, $0, 2
	sw $s1,0($s0)
finfun_5: 
	add $sp, $sp, 4
	add $sp, $sp, 4
	add $sp, $sp, 4
	lw $fp, 0($sp)
	add $sp, $sp, 4
	jr $ra
main:
	ori $s0, $0, 2
	sub $sp, $sp, 4
	sw $s0, 0($sp)
	ori $s0, $0, 3
	sub $sp, $sp, 4
	sw $s0, 0($sp)
	sub $sp, $sp, 4
	sw $ra, 0($sp)
	jal proc1f
	lw $ra, 0($sp)
	add $sp, $sp, 4
	add $sp, $sp, 4
	add $sp, $sp, 4
fin_0: 
	li $v0, 10
	syscall

