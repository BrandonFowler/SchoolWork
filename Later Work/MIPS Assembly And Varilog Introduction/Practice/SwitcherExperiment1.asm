#Brandon Fowler
#CSCD260
#Fall 2015
#Switcher Experiment 1

#Notes: 5 Get Input, 4 Print String, 1 Print Number, 11 Print Character

.data

	space: .asciiz " "
	new: .asciiz "\n"
	task0Print: .asciiz "---------Task0-----------"
	task1Print: .asciiz "---------Task1-----------"
	resultt0: .asciiz "t0 = "
	resultt1: .asciiz "t1 = "
	tcb: .space 8
	
.text
.globl main

#Start of main===============================================
main:

	addi $s0, $0, 0
	addi $s1, $0, 0
	la $s3, tcb
	la $ra, task0
	sw $ra, 0($s3)
	la $ra, task1
	sw $ra, 4($s3)

	task0:
	
		addi $s1, $s1, 1
		
		addi $t0, $0, 1
		
		la $a0, task0Print
		addi $v0, $0, 4				
		syscall	
		la $a0, new
		addi $v0, $0, 4				
		syscall	
		la $a0, resultt0
		addi $v0, $0, 4					
		syscall	
		add $a0, $0, $t0
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		jal ts
		
		addi $t0, $0, 2
		
		la $a0, task0Print
		addi $v0, $0, 4				
		syscall	
		la $a0, new
		addi $v0, $0, 4				
		syscall	
		la $a0, resultt0
		addi $v0, $0, 4					
		syscall	
		add $a0, $0, $t0
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		j task0
		
	task1:
	
		addi $s1, $s1, 1
		
		addi $t1, $0, 1
		
		la $a0, task1Print
		addi $v0, $0, 4				
		syscall	
		la $a0, new
		addi $v0, $0, 4				
		syscall	
		la $a0, resultt1
		addi $v0, $0, 4					
		syscall	
		add $a0, $0, $t1
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		jal ts
		
		addi $t1, $0, 2
		
		la $a0, task1Print
		addi $v0, $0, 4				
		syscall	
		la $a0, new
		addi $v0, $0, 4				
		syscall	
		la $a0, resultt1
		addi $v0, $0, 4					
		syscall	
		add $a0, $0, $t1
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		j task1
		
	ts:
		
		beq $s1, 10, endProg
		beq $s0, 1, saveTask1
		
		sw $ra, 0($s3)
		addi $s0, $0, 1
		lw $ra, 4($s3)
		jr $ra
		
		saveTask1:
		
		sw $ra, 4($s3)
		addi $s0, $0, 0
		lw $ra, 0($s3)
		jr $ra
		
	endProg:
	
		addi $v0, $0, 10
		syscall