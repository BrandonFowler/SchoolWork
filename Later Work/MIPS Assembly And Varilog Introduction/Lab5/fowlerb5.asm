#Brandon Fowler
#CSCD260
#Fall 2015
#Lab 5

#Notes: 5 Get Input, 4 Print String, 1 Print Number, 11 Print Character

.data

	space: .asciiz " "
	new: .asciiz "\n"
	testForward: .asciiz "---Testing Print Forward---"
	testBackward: .asciiz "---Testing Print Backward---"
	result: .asciiz "Result: "
	fullName: .byte 'B','r','a','n','d','o','n',' ','F','o','w','l','e','r','0'
	
.text
.globl main

#Start of main===============================================
main:

	#Print what is bein tested and result identifier=========
	la $a0, testForward
	addi $v0, $0, 4				
	syscall	
	la $a0, new
	addi $v0, $0, 4				
	syscall	
	la $a0, result
	addi $v0, $0, 4					
	syscall	
	
	#Load address for name, and place it on stack=============
	la $t0, fullName
	add $sp, $sp, -4
	sw $t0, 0($sp)
	
	#Call subroutine then fix stack pointer===================
	jal printNameForward
	add $sp, $sp, 4
	
	#Print a couple new lines to distinguish tests============
	la $a0, new
	addi $v0, $0, 4				
	syscall	
	la $a0, new
	addi $v0, $0, 4				
	syscall	

	#Print what is bein tested and result identifier==========
	la $a0, testBackward
	addi $v0, $0, 4				
	syscall	
	la $a0, new
	addi $v0, $0, 4				
	syscall	
	la $a0, result
	addi $v0, $0, 4					
	syscall	
	
	#Load address for name, and place it on stack=============
	la $t0, fullName
	add $sp, $sp, -4
	sw $t0, 0($sp)
	
	#Call subroutine then fix stack pointer===================
	jal printNameBackward
	add $sp, $sp, 4

	#Close Program============================================
	addi $v0, $0, 10
	syscall

#Start of printNameForward subroutine=========================	
printNameForward:

	#Get pointer from stack, and first byte from pointer======
	lw $t0, 0($sp)
	lb $t1, 0($t0)
	
	#Set go or stop contidtion================================
	bne $t1, '0', go1
	jr $ra
	
	#If going, save ra on stack================================
	go1:
	add $sp, $sp, -4
	sw $ra, 0($sp)
	
	#Print character==========================================
	add $a0, $0, $t1
	addi $v0, $0, 11
	syscall
	
	#Move pointer by one byte then place back on the stack====
	add $t0, $t0, 1
	add $sp, $sp, -4
	sw $t0, 0($sp)
	
	#Recursive call, fix stack, get ra, then return===========
	jal printNameForward
	add $sp, $sp, 4
	lw $ra, 0($sp)
	add $sp, $sp, 4
	jr $ra

#Start of printNameBackward subroutine=========================
printNameBackward:

	#Get pointer from stack, and get char======================
	lw $t0, 0($sp)
	lb $t1, 0($t0)

	#Set go or stop contidtion depending on char===============
	bne $t1, '0', go2
	jr $ra
	
	#If going, save char and ra on stack=======================
	go2:
	add $sp, $sp, -4
	sb $t1, 0($sp)
	add $sp, $sp, -4
	sw $ra, 0($sp)
	
	#Move pointer by one byte then place back on the stack====
	add $t0, $t0, 1
	add $sp, $sp, -4
	sw $t0, 0($sp)
	
	#Recursive call===========================================
	jal printNameBackward
	
	#Fix Stack, get ra and get char again=====================
	add $sp, $sp, 4
	lw $ra, 0($sp)
	add $sp, $sp, 4
	lb $t1, 0($sp)
	add $sp, $sp, 4
	
	#Print char then return===================================
	add $a0, $0, $t1
	addi $v0, $0, 11
	syscall
	jr $ra