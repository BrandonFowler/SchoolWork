#! /bin/bash

#Lab17
#Brandon Fowler
#CSCD240-02

#Worked with Mat Klingenburg. We worked out how to solve some things together,
#but both wrote our own code.

#Please See Notes At The Very Bottom!

if [[ -d $1 ]]; then #Check if valid directory was a command parameter
	directory=$1 
else #No valid directory yet
	read -p "Enter Directory:" directory 
fi

until [[ -d $directory ]]; do #Go till valid directory is given
	read -p "Enter Directory:" directory
done

cd $directory

echo
echo "Extracting"
echo

for i in *.zip; do #Find an unpack zip files
	[[ -f $i ]] || continue
	unzip $i -d ${i%%.zip}
done

for j in *.tar.gz; do #Find and unpack tar.gz files
	[[ -f $j ]] || continue
	mkdir ${j%%.tar.gz}
	tar -xzvf $j -C ${j%%.tar.gz}
done

fnames=`ls`

echo
echo "Compiling"
echo

for x in $fnames; do
	if [[ -d $x ]]; then #If is directory
		cd $x #Step in
		look="NULL"
		look=`find . -name makefile` #Look for makefiles
		if [[ ! -n "$look" ]]; then
			look=`find . -name Makefile` #Look for Makefiles
		fi
		if [[ ! -n "$look" ]]; then
			look=`find . -name GNUmakefile` #Look for Gnumakefiles
		fi
		
		if [[ $look ]]; then #If a makefile was found make it
			for i in awgh ; do
				prev=`pwd`
				cd ${look%%/makefile} 2>/dev/null 
				make
				cd $prev
			done
		else #No make file
			echo "No makefile found for this one gcc all c files"
			gcc `find . -name "*.c"` #Compile all c files
		fi
		cd ../
	fi
done

echo
echo "Running Programs"
echo

for a in $fnames; do
	if [[ -d $a ]]; then #If is directory
		cd $a #Step in
		files=`find . -name "*"` #Find all files
		for b in $files; do
			if [[ -x $b && $b != "makefile" && $b != "Makefile" && $b != "GNUmakefile" && $b != *.mk && $b != "." && $b != "./." ]]; then
			#If the file is executable and not a makefile or a directory the run it
				echo
				echo "Running $b"	
				echo
				./$b
			fi
		done
		cd ../
	fi
done

echo
echo "Finished"
echo

#Notes!:

#When creating a new file name to extract to I used the %% operator.
#This operator removes everything from the name specified. 
#This allowed me to use the resulting name to create
#a new file to extract to without the file extentions.

#I added extra echo statments in my script. This was done to better
#the readability of output to the screen, and announce what the script
#is doing as it runs.
