#Brandon Fowler
#CSCD260
#Fall 2015
#Lab 4

#Notes: 5 Get Input, 4 Print String, 1 Print Number

.data

	space: .asciiz " "
	new: .asciiz "\n"
	testLess: .asciiz "Testing: 5 < 8"
	testEqual: .asciiz "Testing: 8 = 8"
	testMore: .asciiz "Testing: 8 > 5"
	result: .asciiz "Result: "

.text
.globl main

#Start of main=============================================
main:

	#Print what is bein tested and result identifier=======
	la $a0, testLess
	addi $v0, $0, 4				
	syscall	
	la $a0, new				
	addi $v0, $0, 4				
	syscall	
	la $a0, result
	addi $v0, $0, 4				
	syscall	
	
	#Set test values========================================
	addi $t0, $0, 5
	addi $t1, $0, 8

	#Prepare stack for parameters and place them============
	add $sp, $sp, -4
	sw $t0, ($sp)
	add $sp, $sp, -4
	sw $t1, ($sp)

	#Call "Compare subroutine" and clean up stack============
	jal compare
	add $sp, $sp, 4
	add $sp, $sp, 4

	#Retrieve and print result with some formating===========
	add $a0, $0, $v0
	addi $v0, $0, 1
	syscall
	la $a0, new				
	addi $v0, $0, 4				
	syscall	
	la $a0, new				
	addi $v0, $0, 4				
	syscall	

	#Print what is bein tested and result identifier=========
	la $a0, testEqual
	addi $v0, $0, 4				
	syscall	
	la $a0, new				
	addi $v0, $0, 4				
	syscall	
	la $a0, result
	addi $v0, $0, 4				
	syscall

	#Set test values=========================================
	addi $t0, $0, 8
	addi $t1, $0, 8

	#Prepare stack for parameters and place them=============
	add $sp, $sp, -4
	sw $t0, ($sp)
	add $sp, $sp, -4
	sw $t1, ($sp)

	#Call "Compare subroutine" and clean up stack============
	jal compare
	add $sp, $sp, 4
	add $sp, $sp, 4
	
	#Retrieve and print result with some formating===========
	add $a0, $0, $v0
	addi $v0, $0, 1
	syscall
	la $a0, new				
	addi $v0, $0, 4				
	syscall	
	la $a0, new				
	addi $v0, $0, 4				
	syscall	

	#Set test values=========================================
	addi $t0, $0, 8
	addi $t1, $0, 5

	#Print what is bein tested and result identifier=========
	la $a0, testMore
	addi $v0, $0, 4				
	syscall	
	la $a0, new				
	addi $v0, $0, 4				
	syscall	
	la $a0, result
	addi $v0, $0, 4				
	syscall	
	
	#Prepare stack for parameters and place them============
	add $sp, $sp, -4
	sw $t0, ($sp)
	add $sp, $sp, -4
	sw $t1, ($sp)

	#Call "Compare subroutine" and clean up stack============
	jal compare
	add $sp, $sp, 4
	add $sp, $sp, 4

	#Retrieve and print result with some formating===========
	add $a0, $0, $v0
	addi $v0, $0, 1
	syscall
	la $a0, new				
	addi $v0, $0, 4				
	syscall	
	la $a0, new				
	addi $v0, $0, 4				
	syscall	

	#Close Program===========================================
	addi $v0, $0, 10
	syscall

#Start of compare============================================
compare:

	#Get parameters from stack===============================
	lw $t1, ($sp)
	lw $t0, 4($sp)

	#Solve equality==========================================
	blt $t0, $t1, returnLess
	beq $t0, $t1, returnEqual
	bgt $t0, $t1, returnMore
	
	#Place return value into $v0 and return to main==========
	returnLess:
	addi $v0, $0, -1
	jr $ra
	returnEqual:
	addi $v0, $0, 0
	jr $ra
	returnMore:
	addi $v0, $0, 1
	jr $ra
