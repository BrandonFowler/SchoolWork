#Brandon Fowler
#CSCD260
#Fall 2015
#Lab 2

.data

plus: .asciiz " + "
minus: .asciiz " - "
equal: .asciiz " = "
new: .asciiz "\n"

.text
.globl main
main:

#get input first number
addi $v0, $0, 5
syscall

#move input to t0
add $t0, $0, $v0

#get input second number
addi $v0, $0, 5
syscall

#move input to t1
add $t1, $0, $v0

#add numbers in t3
add $t3, $t0, $t1

#move first num to a0
add $a0, $0, $t0

#print first number
addi $v0, $0, 1
syscall

#print + sign with spaces
la $a0, plus
addi $v0, $0, 4
syscall

#move second num to a0
add $a0, $0, $t1

#print second number
addi $v0, $0, 1
syscall

#print = sign with spaces
la $a0, equal
addi $v0, $0, 4
syscall

#move result to a0
add $a0, $0, $t3

#print result
addi $v0, $0, 1
syscall

#print newline
la $a0, new
addi $v0, $0, 4
syscall

#sub numbers in t4
sub $t4, $t0, $t1 

#move first num to a0
add $a0, $0, $t0

#print first number
addi $v0, $0, 1
syscall

#print - sign with spaces
la $a0, minus
addi $v0, $0, 4
syscall

#move second num to a0
add $a0, $0, $t1

#print second number
addi $v0, $0, 1
syscall

#print = sign with spaces
la $a0, equal
addi $v0, $0, 4
syscall

#move result to a0
add $a0, $0, $t4

#print result
addi $v0, $0, 1
syscall

#close program
addi $v0, $0, 10
syscall

