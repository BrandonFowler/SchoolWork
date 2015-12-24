
.data

str0:   .asciiz "123"
str1:   .asciiz "45678"
	
.text
.globl main
main:

task0:
        add  $t0, $0, $0   #t0 = 0
        addi $t1, $0, 10   #t1 = 10  
        la   $s0, str0     #s0 = begining of str0  
	beg0:
        lb   $t2, ($s0)     	#t2 = 1	(char)					#t2 = 2	(char)					#t2 = 3	(char)					#t2 = 0 (char)
        beq  $t2, $0, quit0 	#not quiting					#not quiting					#not quiting					#quit
        sub  $t2, $t2, '0'  	#t2 = 1	(int)					#t2 = 2	(int)					#t2 = 3	(int)
        mult $t0, $t1       	#doesnt change regs				#doesnt change regs				#doesnt change regs
        mflo $t0				#t0 = 0							#t0 = 10						#t0 = 120
        add  $t0, $t0, $t2  	#t0 = 1							#t0 = 12						#t0 = 123
        add  $s0, $s0, 1    	#s0 pointing at 2nd spot		#s0 pointing at 3rd spot		#s0 pointing at arrayend
        b    beg0
	quit0:
		add  $v1, $0, $t0		#v1 = 123
		add  $s0, $0, $v1		#s0 = 123
		add  $a1, $0, $s0 		#a1 = 123
		add  $t5, $0, $a1		#t5 = 123
		add  $t6, $0, $t5		#t6 = 123
		addi $s0, $0, 1			#s0 = 1
		add  $v0, $0, $s0		#v0 = 1
		add  $a0, $0, $t6		#a0 = 123
		syscall					#print 123
        j task0