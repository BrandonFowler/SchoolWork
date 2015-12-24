.data
str: .asciiz "Hello World"

.text
.globl main
main: 
la $a0, str
addi $v0, $0, 4
syscall
addi $v0, $0, 10
syscall