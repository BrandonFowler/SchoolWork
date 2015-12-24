
NOT THE TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!

.data
myname: .asciiz "Brandon Fowler"         #Change this to your name


.align 2                                   # this is for word boundary
nnum: .word 14                   # number of numbers in an array below 
nums: .word -1, 4, 8, 9, -566, 78, 94, -9, -56, -77, 77, 89, 98, 1000
cpnm: .space 60
plus: .asciiz " + "
minus: .asciiz " - "
equal: .asciiz " = "
new: .asciiz "\n"
numev: .asciiz "Number of evens in array:"
result: .asciiz "Result Array of Evens: "
space: .asciiz " "

.globl main
.text
main:




########### problem 0 (1 point) ###########
# Change "FirstName LastName" in the above .data section to 
# print your own name.
		la $a0, myname				
		addi $v0, $0, 4				
		syscall	
		
		
   j p10   # your code for problem 0 ends here with this jump



########### problem 1.0 (1 point) ###########
# Input 0 or 1. Then, input two numbers.
# If the first input is 0, then add second number and third number. 
# If the first input is 1, then subtract third number from second number.
p10:
	la $a0, new
	addi $v0, $0, 4
	syscall

	addi $v0, $0, 5
	syscall

	add $t0, $0, $v0
	
	addi $v0, $0, 5
	syscall

	add $t1, $0, $v0
	
	addi $v0, $0, 5
	syscall
	
	add $t2, $0, $v0
	
	beq $t0, 1, subt
	
	add $a0, $0, $t1
	addi $v0, $0, 1
	syscall
	
	la $a0, plus
	addi $v0, $0, 4
	syscall
	
	add $a0, $0, $t2
	addi $v0, $0, 1
	syscall
	
	la $a0, equal
	addi $v0, $0, 4
	syscall

	add $t3, $t1, $t2
	
	add $a0, $0, $t3
	addi $v0, $0, 1
	syscall
	j end
	
	subt:
	
	add $a0, $0, $t1
	addi $v0, $0, 1
	syscall
	
	la $a0, minus
	addi $v0, $0, 4
	syscall
	
	add $a0, $0, $t2
	addi $v0, $0, 1
	syscall
	
	la $a0, equal
	addi $v0, $0, 4
	syscall
	
	sub $t3, $t1, $t2
	
	add $a0, $0, $t3
	addi $v0, $0, 1
	syscall
	
	end:
   j p21   # your code for problem 1 ends here with this jump

########### problem 2.1 (4 point) ###########
# Count how many even numbers in the array, nums. Print the number.
p21:
	addi $t1, $0, 0
	addi $t5, $0, 0
	la $s0, nums
	toploop1:
		lw $t3, ($s0)				
		andi $t4, $t3, 1			
		beq $t4, $0, ifeven			

		endifeven:					

		addi $t1, $t1, 4			
		addi $s0, $s0, 4			
		beq $t1, 56, bailloop1		
		b toploop1					

		ifeven:						
			addi $t5, $t5, 1
			b endifeven					
	bailloop1:					


	la $a0, new
	addi $v0, $0, 4
	syscall
	
	la $a0, numev
	addi $v0, $0, 4
	syscall

	add $a0, $0, $t5
	addi $v0, $0, 1
	syscall

   j p22   # your code for problem 2.1 ends here with this jump

########### problem 2.2 (4 point) ###########
# Copy only even numbers from the array "nums" into the array "cpnm", 
# and print copied numbers in the cpnm.

p22:

	addi $t1, $0, 0				
	addi $t2, $0, 0				
	la $s0, nums		
	la $s1, cpnm			

	toploop2:					

		lw $t3, ($s0)				
		andi $t4, $t3, 1			
		beq $t4, $0, ifeven2			

		endifeven2:					

		addi $t1, $t1, 4			
		addi $s0, $s0, 4			
		beq $t1, 56, bailloop2		
		b toploop2					

		ifeven2:						
			lw  $a0, ($s0)				
			sw  $a0, ($s1)				
			addi $t2, $t2, 4			
			addi $s1, $s1, 4			
			b endifeven2					

	bailloop2:					


	addi $t1, $0, 0				
	la $s1, cpnm
	
	la $a0, new					
	addi $v0, $0, 4				
	syscall						

	la $a0, result				
	addi $v0, $0, 4				
	syscall						

	toploop3:					

		lw $a0, ($s1)				
		addi $v0, $0, 1				
		syscall						

		la $a0, space				
		addi $v0, $0, 4			
		syscall						

		addi $t1, $t1, 4			
		addi $s1, $s1, 4			
		beq $t1, $t2, bailloop3		
		b toploop3				

	bailloop3:				


# your code for ends here






########
###  exit code     ####

addi $v0, $0, 10

syscall


