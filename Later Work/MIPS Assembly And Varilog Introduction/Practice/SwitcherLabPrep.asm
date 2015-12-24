#Brandon Fowler
#CSCD260
#Fall 2015
#Switcher Lab Prep

#Notes: 5 Get Input, 4 Print String, 1 Print Number, 11 Print Character

.data

	space: .asciiz " "
	new: .asciiz "\n"
	task0Print: .asciiz "---------Task0-----------"
	task1Print: .asciiz "---------Task1-----------"
	saveTask: .asciiz "Saving Task State...."
	saveAndLoadComplete: .asciiz "Task State Saved And Next Task Loaded"
	t0val: .asciiz "t0 == "
	addOne: .asciiz "Incrementing t0 by one"
	addTwo: .asciiz "Incrementing t0 by two"
	.align 2
	tcb0: .space 128
	tcb1: .space 128
	tid: .word 0
	
.text
.globl main

#Start of main==============================================================
main:

	#Initialize t0 to show state difference=================================
	addi $t0, $0, 0
	
	#Initialize task control block 0========================================
	la $a0, tcb0
	la $s0, task0
	sw $sp, 116($a0)
	sw $s0, 124($a0)
	
	#Initialize task control block 1=========================================
	la $a0, tcb1
	la $s0, task1
	sw $sp, 116($a0)
	sw $s0, 124($a0)
	
	#Go to task 0============================================================
	j task0
	
	#End this program========================================================
	endProg:
	
		addi $v0, $0, 10
		syscall

	#Start task0=============================================================
	task0:

		#Print state and changes==============================================
		la $a0, task0Print
		addi $v0, $0, 4				
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		la $a0, t0val
		addi $v0, $0, 4				
		syscall
		add $a0, $0, $t0
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		la $a0, addOne
		addi $v0, $0, 4							
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		la $a0, t0val
		addi $v0, $0, 4				
		syscall
		addi $t0, $t0, 1
		add $a0, $0, $t0
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		#Jump to ts=============================================================
		jal ts
		
		#Jump back to start of task0=============================================
		j task0
	
	#Start task1=================================================================
	task1:
	
		#Print state and changes=================================================
		la $a0, task1Print
		addi $v0, $0, 4				
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		la $a0, t0val
		addi $v0, $0, 4				
		syscall
		add $a0, $0, $t0
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		la $a0, addTwo
		addi $v0, $0, 4				
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		la $a0, t0val
		addi $v0, $0, 4				
		syscall
		addi $t0, $t0, 2
		add $a0, $0, $t0
		addi $v0, $0, 1
		syscall
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		#Jump to ts==================================================================
		jal ts
		
		#Jump back to start of task1==================================================
		j task1
	
	#Start ts==========================================================================
	ts:
	
		#Check if my end condition is met==============================================
		beq $t0, 10, endProg
		
		#Print start of state save==================================================
		la $a0, saveTask
		addi $v0, $0, 4					
		syscall	
		la $a0, new
		addi $v0, $0, 4				
		syscall
		
		#Save a0 to stack then load tid to check current running task==================
		add $sp, $sp, -4
		sw $a0, 0($sp)
		la $a0, tid
		
		#Save a1 to stack so I can use it to check tid=================================
		add $sp, $sp, -4
		sw $a1, 0($sp)
		lw $a1, 0($a0)
		
		#If task one running, jump to swapTask1 else continue into swapTask0============
		beq $a1, 1, swapTask1
		swapTask0:
			
			#Load task control block 0 address into a0=================================
			la $a0, tcb0
			
			#Save all registers========================================================
			sw $0, 0($a0)
			.set noat	  #Get direct access to at register
			sw $1, 4($a0) #Save at
			.set at		  #Release direct access to at register
			sw $2, 8($a0)
			sw $3, 12($a0)
			lw $a1, 4($sp)#retrieve a0/register4 from stack
			sw $a1, 16($a0)#Save a0/register4 into tcb
			lw $a1, 0($sp)#retrieve a1/register5 from stack
			sw $a1, 20($a0)#Save a1/register5 into tcb
			sw $6, 24($a0)
			sw $7, 28($a0)
			sw $8, 32($a0)
			sw $9, 36($a0)
			sw $10, 40($a0)
			sw $11, 44($a0)
			sw $12, 48($a0)
			sw $13, 52($a0)
			sw $14, 56($a0)
			sw $15, 60($a0)
			sw $16, 64($a0)
			sw $17, 68($a0)
			sw $18, 72($a0)
			sw $19, 76($a0)
			sw $20, 80($a0)
			sw $21, 84($a0)
			sw $22, 88($a0)
			sw $23, 92($a0)
			sw $24, 96($a0)
			sw $25, 100($a0)
			sw $26, 104($a0)
			sw $27, 108($a0)
			sw $28, 112($a0)
			add $t0,$0,$sp #Save stack pointer in t0
			add $t0,$t0,8 #Fix saved stack pointer
			sw $t0, 116($a0) #Save stack in task control block
			sw $30, 120($a0)
			sw $31, 124($a0)
			
			#Fix stack pointer=========================================================
			add $sp,$sp,8
			
			#Change tid value==========================================================
			lui $a1, 4097     
            ori $a0, $a1, 428
			addi $a1, $0, 1
			sw $a1, 0($a0)
			
			#Load task control block 1 into a0=========================================
			la $a0, tcb1
			
			#Load all registers========================================================
			lw $0, 0($a0)
			.set noat	  #Get direct access to at register
			lw $1, 4($a0) #Load at
			.set at		  #Release direct access to at register
			lw $2, 8($a0)
			lw $3, 12($a0)
			lw $5, 20($a0)
			lw $6, 24($a0)
			lw $7, 28($a0)
			lw $8, 32($a0)
			lw $9, 36($a0)
			lw $10, 40($a0)
			lw $11, 44($a0)
			lw $12, 48($a0)
			lw $13, 52($a0)
			lw $14, 56($a0)
			lw $15, 60($a0)
			lw $16, 64($a0)
			lw $17, 68($a0)
			lw $18, 72($a0)
			lw $19, 76($a0)
			lw $20, 80($a0)
			lw $21, 84($a0)
			lw $22, 88($a0)
			lw $23, 92($a0)
			lw $24, 96($a0)
			lw $25, 100($a0)
			lw $26, 104($a0)
			lw $27, 108($a0)
			lw $28, 112($a0)
			lw $29, 116($a0)
			lw $30, 120($a0)
			lw $31, 124($a0)
			lw $4, 16($a0)#Loaded this one last because it was holding tcb1
			
			#Print end of save state/load state================================================
			la $a0, saveAndLoadComplete
			addi $v0, $0, 4					
			syscall	
			la $a0, new
			addi $v0, $0, 4				
			syscall
			la $a0, new
			addi $v0, $0, 4				
			syscall
			
			#Jump to next task=========================================================
			jr $ra
			
		swapTask1:
		
			#Load task control block 1 address into a0=================================
			la $a0, tcb1
			
			#Save all registers========================================================
			sw $0, 0($a0)
			.set noat	  #Get direct access to at register
			sw $1, 4($a0) #Save at
			.set at		  #Release direct access to at register
			sw $2, 8($a0)
			sw $3, 12($a0)
			lw $a1, 4($sp)#retrieve a0/registar4 from stack
			sw $a1, 16($a0)#Save a0/register4 into tcb
			lw $a1, 0($sp)#retrieve a1/register5 from stack
			sw $a1, 20($a0)#Save a1/register5 into tcb
			sw $6, 24($a0)
			sw $7, 28($a0)
			sw $8, 32($a0)
			sw $9, 36($a0)
			sw $10, 40($a0)
			sw $11, 44($a0)
			sw $12, 48($a0)
			sw $13, 52($a0)
			sw $14, 56($a0)
			sw $15, 60($a0)
			sw $16, 64($a0)
			sw $17, 68($a0)
			sw $18, 72($a0)
			sw $19, 76($a0)
			sw $20, 80($a0)
			sw $21, 84($a0)
			sw $22, 88($a0)
			sw $23, 92($a0)
			sw $24, 96($a0)
			sw $25, 100($a0)
			sw $26, 104($a0)
			sw $27, 108($a0)
			sw $28, 112($a0)
			add $t0,$0,$sp #Save stack pointer in t0
			add $t0,$t0,8 #Fix saved stack pointer
			sw $t0, 116($a0) #Save stack in task control block
			sw $30, 120($a0)
			sw $31, 124($a0)
			
			#Fix stack pointer=========================================================
			add $sp,$sp,8
			
			#Change tid value==========================================================
			lui $a1, 4097     
            ori $a0, $a1, 428
			addi $a1, $0, 0
			sw $a1, 0($a0)
			
			#Load task control block 0 into a0=========================================
			la $a0, tcb0
			
			#Load all registers========================================================
			lw $0, 0($a0)
			.set noat	  #Get direct access to at register
			lw $1, 4($a0) #Load at
			.set at		  #Release direct access to at register
			lw $2, 8($a0)
			lw $3, 12($a0)
			lw $5, 20($a0)
			lw $6, 24($a0)
			lw $7, 28($a0)
			lw $8, 32($a0)
			lw $9, 36($a0)
			lw $10, 40($a0)
			lw $11, 44($a0)
			lw $12, 48($a0)
			lw $13, 52($a0)
			lw $14, 56($a0)
			lw $15, 60($a0)
			lw $16, 64($a0)
			lw $17, 68($a0)
			lw $18, 72($a0)
			lw $19, 76($a0)
			lw $20, 80($a0)
			lw $21, 84($a0)
			lw $22, 88($a0)
			lw $23, 92($a0)
			lw $24, 96($a0)
			lw $25, 100($a0)
			lw $26, 104($a0)
			lw $27, 108($a0)
			lw $28, 112($a0)
			lw $29, 116($a0)
			lw $30, 120($a0)
			lw $31, 124($a0)
			lw $4, 16($a0)#Loaded this one last because it was holding tcb0
			
			#Print end of save state/load state========================================
			la $a0, saveAndLoadComplete
			addi $v0, $0, 4					
			syscall	
			la $a0, new
			addi $v0, $0, 4				
			syscall
			la $a0, new
			addi $v0, $0, 4				
			syscall
			
			#Jump to next task=========================================================
			jr $ra
			