
.data
	
	space: .asciiz " "
	new: .asciiz "\n"
	task0Print: .asciiz "task0 running"
	task1Print: .asciiz "task1 running"

.text
main:

	addi $s0, $0, 0
	addi $t0, $0, 0

task0:

	la $a0, task0Print
	addi $v0, $0, 4
	syscall
	la $a0, new
	addi $v0, $0, 4				
	syscall	
	jal ts
	j task0
		
task1:

	la $a0, task1Print
	addi $v0, $0, 4
	syscall
	la $a0, new
	addi $v0, $0, 4				
	syscall	
	jal ts
	j task1
	
	
	
ts:
	addi $t0, $t0, 1
	beq $t0, 10, e
	beq $s0, 0, tzero
	beq $s0, 1, tone
	
	tzero:
	
		la $ra, task1
		addi $s0, $0, 1
		j rtn
		
	tone:
	
		la $ra, task0
		addi $s0, $0, 0
		
		
	rtn:
		
		jr $ra
		
	e:
	
		addi $v0, $0, 10
		syscall