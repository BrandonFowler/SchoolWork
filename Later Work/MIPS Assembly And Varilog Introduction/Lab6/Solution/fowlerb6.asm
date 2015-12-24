#Brandon Fowler
#CSCD260
#Fall 2015
#Lab 6, Task Switcher

.data

.align 2
tcb0: .space 136
tcb1: .space 136
tid: .word 0
.align 2

str0:   .asciiz "123"
str1:   .asciiz "45678"
	
.text
.globl main
main:

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
	
#------------ task0 ---------------

task0:
        add  $t0, $0, $0
		jal ts     
        addi $t1, $0, 10     
        la   $s0, str0       
		jal ts
	beg0:
        lb   $t2, ($s0)     
        beq  $t2, $0, quit0 
        sub  $t2, $t2, '0'  
        mult $t0, $t1       
        mflo $t0
        add  $t0, $t0, $t2  
		jal ts
        add  $s0, $s0, 1    
        b    beg0
	quit0:
		jal ts
		add  $v1, $0, $t0
		add  $s0, $0, $v1
		add  $a1, $0, $s0 
		jal ts
		add  $t5, $0, $a1
		add  $t6, $0, $t5
		addi $s0, $0, 1
		add  $v0, $0, $s0
		add  $a0, $0, $t6
		jal ts
		syscall
        j task0


#------------ task1 ---------------

task1:
        add  $t0, $0, $0    
        addi $t1, $0, 10    
        la   $s0, str1      
	beg1:
        lb   $t2, ($s0)      
        beq  $t2, $0, quit1 
		jal ts
        sub  $t2, $t2, '0'  
        mult $t0, $t1
		addi $t8, $0, 0       
        jal ts
		addi $s5, $t8, 0       
		add  $t8, $s5, $s5       
        jal ts
		addi $t8, $0, 0       
        jal ts
		addi $s5, $t8, 0       
		add  $t8, $s5, $s5       
        mflo $t0
        add  $t0, $t0, $t2   
        add  $s0, $s0, 1     
        b    beg1
	quit1:
		add  $v1, $0, $t0
		add  $s0, $0, $v1
		jal ts
		add  $a1, $0, $s0 
		add  $t5, $0, $a1
		jal ts
		add  $t6, $0, $t5
		jal ts
		addi $s0, $0, 1
		add  $v0, $0, $s0
		jal ts
		add  $a0, $0, $t6
		jal ts
		syscall
        j task1
	
#Start ts==========================================================================
ts:
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
		.set noat	  	#Get direct access to at register
		sw $1, 4($a0) 	#Save at
		.set at		  	#Release direct access to at register
		sw $2, 8($a0)
		sw $3, 12($a0)
		lw $a1, 4($sp)	#retrieve a0/register4 from stack
		sw $a1, 16($a0)	#Save a0/register4 into tcb
		lw $a1, 0($sp)	#retrieve a1/register5 from stack
		sw $a1, 20($a0)	#Save a1/register5 into tcb
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
		add $t0,$0,$sp 		#Save stack pointer in t0
		add $t0,$t0,8 		#Fix saved stack pointer
		sw $t0, 116($a0) 	#Save stack in task control block
		sw $30, 120($a0)
		sw $31, 124($a0)
		mflo $t0			#Get value fom special register LO
		sw $t0, 128($a0)	#Save special register LO
		mfhi $t0			#Get value fom special register HI
		sw $t0, 132($a0)	#Save special register HI
			
		#Fix stack pointer=========================================================
		add $sp,$sp,8
			
		#Change tid value==========================================================
		la $a0, tid
		addi $a1, $0, 1
		sw $a1, 0($a0)
			
		#Load task control block 1 into a0=========================================
		la $a0, tcb1
			
		#Load all registers========================================================
		lw $0, 0($a0)
		.set noat	  		#Get direct access to at register
		lw $1, 4($a0) 		#Load at
		.set at		  		#Release direct access to at register
		lw $2, 8($a0)
		lw $3, 12($a0)
		lw $5, 20($a0)
		lw $6, 24($a0)
		lw $7, 28($a0)
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
		lw $t0, 128($a0)	#Get value for special register LO
		mtlo $t0			#Restore special register LO
		lw $t0, 132($a0)	#Get value for special register HI
		mthi $t0			#Restore special register HI
		lw $8, 32($a0)		#Loaded this one(t0) later because needed it to resore LO and HI
		lw $4, 16($a0)		#Loaded this one(a0) last because it was holding tcb1
				
		#Jump to next task=========================================================
		jr $ra
			
	swapTask1:
		
		#Load task control block 1 address into a0=================================
		la $a0, tcb1
			
		#Save all registers========================================================
		sw $0, 0($a0)
		.set noat	  	#Get direct access to at register
		sw $1, 4($a0) 	#Save at
		.set at		  	#Release direct access to at register
		sw $2, 8($a0)
		sw $3, 12($a0)
		lw $a1, 4($sp)	#retrieve a0/register4 from stack
		sw $a1, 16($a0)	#Save a0/register4 into tcb
		lw $a1, 0($sp)	#retrieve a1/register5 from stack
		sw $a1, 20($a0)	#Save a1/register5 into tcb
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
		add $t0,$0,$sp 		#Save stack pointer in t0
		add $t0,$t0,8 		#Fix saved stack pointer
		sw $t0, 116($a0) 	#Save stack in task control block
		sw $30, 120($a0)
		sw $31, 124($a0)
		mflo $t0			#Get value fom special register LO
		sw $t0, 128($a0)	#Save special register LO
		mfhi $t0			#Get value fom special register HI
		sw $t0, 132($a0)	#Save special register HI
			
		#Fix stack pointer=========================================================
		add $sp,$sp,8
			
		#Change tid value==========================================================
		la $a0, tid
		addi $a1, $0, 0
		sw $a1, 0($a0)
			
		#Load task control block 0 into a0=========================================
		la $a0, tcb0
			
		#Load all registers========================================================
		lw $0, 0($a0)
		.set noat	  		#Get direct access to at register
		lw $1, 4($a0) 		#Load at
		.set at		  		#Release direct access to at register
		lw $2, 8($a0)
		lw $3, 12($a0)
		lw $5, 20($a0)
		lw $6, 24($a0)
		lw $7, 28($a0)
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
		lw $t0, 128($a0)	#Get value for special register LO
		mtlo $t0			#Restore special register LO
		lw $t0, 132($a0)	#Get value for special register HI
		mthi $t0			#Restore special register HI
		lw $8, 32($a0)		#Loaded this one(t0) later because needed it to resore LO and HI
		lw $4, 16($a0)		#Loaded this one(a0) last because it was holding tcb1
			
		#Jump to next task=========================================================
		jr $ra
			