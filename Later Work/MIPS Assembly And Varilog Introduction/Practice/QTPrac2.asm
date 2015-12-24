#Brandon Fowler
#CSCD260
#Fall 2015
#Expiriment

#Notes: 5 Get Input, 4 Print String, 1 Print Number

.data

num1: .word 6

.text
.globl main
main:
	la $t0, num1
	lw $a0, 0($t0)
	add $v0,$0,1
	syscall
	
	add $v0,$0,10
	syscall