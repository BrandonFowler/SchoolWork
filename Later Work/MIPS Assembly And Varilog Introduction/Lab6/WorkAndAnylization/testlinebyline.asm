

.data

str0:   .asciiz "123"

.text
.globl main
main:

task0:
        add  $t0, $0, $0   
        addi $t1, $0, 10   
        la   $s0, str0     
	beg0:
        lb   $t2, ($s0)     	
        beq  $t2, $0, quit0 	
        sub  $t2, $t2, '0' 
		mult $t0, $t1
		mflo $t0
		add  $t0, $t0, $t2
		
		add	 $a0, $0, $t2
		add  $v0, $0, 1
		syscall
	quit0:
		addi $v0, $0, 10
		syscall