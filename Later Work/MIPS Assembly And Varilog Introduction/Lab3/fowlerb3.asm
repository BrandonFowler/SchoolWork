#Brandon Fowler
#CSCD260
#Fall 2015
#Lab 3

#Notes: 5 Get Input, 4 Print String, 1 Print Number

.data

	size: .word 11
	StartingArray: .word -17, 8, -8, 5, 55, 555, -550, 11, 11, 350
	ResultArray: .space 44
	space: .asciiz " "
	new: .asciiz "\n"
	start: .asciiz "Starting Array: "
	result: .asciiz "Result Array: "

.text
.globl main

main:

	addi $t1, $0, 0				#loop counter for StartingArray traversal
	addi $t2, $0, 0				#loop counter for Result traversal
	la $s0, StartingArray		#Assign StartingArray
	la $s1, ResultArray			#Assign ResultArray

	#Start First Loop To Find Even Numbers In StartingArray And Copy Them Into ResultArray
	#=====================================================================================
	toploop1:					#Start of loop 1

		lw $t3, ($s0)				#Copy number from StartingArray to t3
		andi $t4, $t3, 1			#and t3 with 1, store result in t4
		beq $t4, $0, ifeven			#jump if t4 is 0

		endifeven:					#Return point from ifeven

		addi $t1, $t1, 4			#Increment counter by 4 for StartingArray traversal
		addi $s0, $s0, 4			#Move position in StartingArray by 4
		beq $t1, 40, bailloop1		#Bail if reached the end of StartingArray
		b toploop1					#Go back to the top of first loop

		ifeven:						#Jump here if even number found
			lw  $a0, ($s0)				#Load word from StartingArray into a0
			sw  $a0, ($s1)				#Save word from a0 into ResultArray
			addi $t2, $t2, 4			#Increment counter by 4 for ResultArray traversal
			addi $s1, $s1, 4			#Move position in ResultArray by 4
			b endifeven					#Jump back to position in first loop

	bailloop1:					#Bail point for first loop

	#Prepare To Print StartingArray
	#=====================================================================================
	addi $t1, $0, 0				#Set t1 counter back to 0 for StartingArray traversal
	la $s0, StartingArray		#Set s0 back to StartingArray starting point

	la $a0, start				#Load "Starting Array: " into a0
	addi $v0, $0, 4				#Set command for printing string in a0
	syscall						#print

	#Start Second Loop To Print Numbers In StartingArray
	#=====================================================================================
	toploop2:

		lw $a0, ($s0)				#Copy value from StartingArray into a0
		addi $v0, $0, 1				#Set command for printing number in a0
		syscall						#Print

		la $a0, space				#Load space into a0
		addi $v0, $0, 4				#Set command for printing string in a0
		syscall						#print

		addi $t1, $t1, 4			#Increment counter for StartingArray traversal
		addi $s0, $s0, 4			#Move position in StartingArray by 4
		beq $t1, 40, bailloop2		#Bail if reached the end of the StartingArray
		b toploop2					#Jump to the top of the second loop

	bailloop2:
	
	#Prepare To Print ResultArray
	#=====================================================================================
	addi $t1, $0, 0				#Set t1 counter back to 0 for ResultArray traversal
	la $s1, ResultArray			#Set s1 back to ResultArray starting point
	
	la $a0, new					#Load new line into a0
	addi $v0, $0, 4				#Set command for printing string in a0
	syscall						#print

	la $a0, result				#Load "Result Array: " into a0
	addi $v0, $0, 4				#Set command for printing string in a0
	syscall						#print
	
	#Start Third Loop To Print Numbers In ResultArray
	#=====================================================================================
	toploop3:					#Start of loop 3

		lw $a0, ($s1)				#Copy value from ResultArray into a0
		addi $v0, $0, 1				#Set command for printing number in a0
		syscall						#Print

		la $a0, space				#Load space into a0
		addi $v0, $0, 4				#Set command for printing string in a0
		syscall						#print

		addi $t1, $t1, 4			#Increment counter for ResultArray traversal
		addi $s1, $s1, 4			#Move position in ResultArray by 4
		beq $t1, $t2, bailloop3		#Bail if reached the end of the ResultArray
		b toploop3					#Jump to the top of the third loop

	bailloop3:					#Bail point for third loop

	#End Program
	#=====================================================================================
	addi $v0, $0, 10			#Set command to end the program
	syscall						#End program
