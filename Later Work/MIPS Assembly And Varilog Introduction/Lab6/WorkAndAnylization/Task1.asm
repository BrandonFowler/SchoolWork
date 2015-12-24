
.data

str0:   .asciiz "123"
str1:   .asciiz "45678"
	
.text
.globl main
main:

task1:
        add  $t0, $0, $0    
        addi $t1, $0, 10    
        la   $s0, str1      
	beg1:
        lb   $t2, ($s0)      
        beq  $t2, $0, quit1 
        sub  $t2, $t2, '0'  
        mult $t0, $t1
		addi $t8, $0, 0       
		addi $s5, $t8, 0       
		add  $t8, $s5, $s5       
		addi $t8, $0, 0       
		addi $s5, $t8, 0       
		add  $t8, $s5, $s5       
        mflo $t0
        add  $t0, $t0, $t2   
        add  $s0, $s0, 1     
        b    beg1
	quit1:
		add  $v1, $0, $t0
		add  $s0, $0, $v1
		add  $a1, $0, $s0 
		add  $t5, $0, $a1
		add  $t6, $0, $t5
		addi $s0, $0, 1
		add  $v0, $0, $s0
		add  $a0, $0, $t6
		syscall
        j task1