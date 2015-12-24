
//Class: CSCD305
//Homework: Assignment 2
//Compilers used: g++ and cl
//Author: Brandon Fowler

#include "cbiginteger.h"

//=================================================================
CBigInteger::CBigInteger(int size, int sign){
	this->size = size;
	this->sign = sign;
	this->digits = NULL;
}

//================================================================
CBigInteger::CBigInteger(const CBigInteger& original){
	this->size = 0;
	this->sign = 1;
	this->digits = NULL;
	*this = original;
}

//================================================================
CBigInteger::~CBigInteger(){
	this->size = 0;
	this->sign = 0;
	delete [] this->digits;
	this->digits = NULL;
}

//================================================================
istream& operator>>(istream& in, CBigInteger& biginteger){
	string input;
	cin>>input;	//Take in an integer as a string
	
	if(input[0] == '-'){
		biginteger.sign = -1;
		input[0] = '0';
	}
	else{
		biginteger.sign = 1;
	}
	
	biginteger.size = input.length();
	
	if(biginteger.digits != NULL){
		delete [] biginteger.digits;
	}
	
	biginteger.digits = new int[biginteger.size];
	
	for(int i = 0; i < biginteger.size; i++){
			biginteger.digits[i] = ((int)input[i])-('0'); //Using ASCII math to convert each char into actual integers

	}
	
	for(int i = 0; i < biginteger.size; i++){
			if(biginteger.digits[i] < 0 || biginteger.digits[i] > 9){//Check each digit for bad input
				biginteger.size = 0;
				biginteger.sign = 1;
				cout<<endl;
				cout<<"Bad input entered. Please try again: ";
				cin>>biginteger;//Continue input call until good input is entered
			}
	}
	
	biginteger.stripZeros();
	
	return in;
}

//=================================================================
ostream& operator<<(ostream& out, CBigInteger& biginteger){
	
	if(biginteger.size != 0){
	
		if(biginteger.sign == -1){
			cout<<'-';
		}
	
		for(int i = 0; i < biginteger.size; i++){
			cout<<biginteger.digits[i];
		}
	}
	return out;
}

//=================================================================
CBigInteger& CBigInteger::operator=(const CBigInteger& original){
	if(*this != original){
		this->size = original.size;
		this->sign = original.sign;
		
		if(this->digits != NULL){
			delete [] this->digits;
		}
		
		this->digits = new int[original.size];
		
		for(int i = 0; i < original.size; i++){
			this->digits[i] = original.digits[i];
		}
	}
	return *this;
}

//=================================================================
bool CBigInteger::operator>(const CBigInteger& rhs) const{
	
	if(this->sign > rhs.sign){
		return true;
	}
	
	if(this->sign < rhs.sign){
		return false;
	}
	
	//At this point I know both signs are the same
	
	if(this->size > rhs.size && this->sign > 0 || this->size < rhs.size && this->sign < 0){ 
		return true;
	}
	
	if(this->size > rhs.size && this->sign < 0 || this->size < rhs.size && this->sign > 0){ 
		return false;
	}
	
	//At this point both signs and size must be the same
	
	if(this->sign > 0){//If positive, check each digit till bigger number is confirmed
		for(int i = 0; i < this->size; i++){
			if(this->digits[i] > rhs.digits[i]){
				return true;
			}
			if(this->digits[i] < rhs.digits[i]){
				return false;
			}
		}
	}
	
	if(this->sign < 0){//If negative, check each digit till bigger number is confirmed
		for(int i = 0; i < this->size; i++){
			if(this->digits[i] < rhs.digits[i]){
				return true;
			}
			if(this->digits[i] > rhs.digits[i]){
				return false;
			}
		}
	}
	
	return false;
}

//=================================================================
bool CBigInteger::operator<(const CBigInteger& rhs) const{
	if(*this == rhs || *this > rhs){//If equal or greater, then can't be less
		return false;
	}
	
	return true;
}

//=================================================================
bool CBigInteger::operator==(const CBigInteger& rhs) const{
	if(this->sign != rhs.sign){//If signs differ, then can't be equal
		return false;
	}
	
	if(this->size != rhs.size){//If sizes differ, then can't be equal
		return false;
	}
	
	//At this point size and signs must be the same
	
	for(int i = 0; i < this->size; i++){//Compare digits for differences
		if(this->digits[i] != rhs.digits[i]){
			return false;
		}
	}
	return true;
}

//=================================================================
bool CBigInteger::operator!=(const CBigInteger& rhs) const{
	return !(*this == rhs);
}

//=================================================================
bool CBigInteger::operator>=(const CBigInteger& rhs) const{

	if(*this == rhs || *this > rhs){
		return true;
	}
	else{
		return false;
	}
}

//=================================================================
bool CBigInteger::operator<=(const CBigInteger& rhs) const{
	
	if(*this == rhs || *this < rhs){
		return true;
	}
	else{
		return false;
	}
}

//=================================================================
CBigInteger CBigInteger::operator+(const CBigInteger& rhs) const{

	if(this->sign == 1 && rhs.sign == -1){//First positive, second negative, call subtraction
		CBigInteger temp = rhs;
		temp.sign = 1;
		return *this - temp;
	}
	
	if(this->sign == -1 && rhs.sign == 1){//First negative, second positive, call subtraction
		CBigInteger temp = *this;
		temp.sign = 1;
		return rhs - temp;
	}

	int carry = 0;
	int longerLength;
	int shorterLength;
	CBigInteger sum;
	
	if(this->size > rhs.size){
		longerLength = this->size;
		shorterLength = rhs.size;
	}
	else{
		longerLength = rhs.size;
		shorterLength = this->size;
	}
	
	sum.digits = new int[longerLength+1];
	sum.size = longerLength+1;
	sum.sign = this->sign;
	
	//If first BigInt if longer, do calculations based on that information
	if(this->size > rhs.size){
		int j = 0;
		int i = 0;
		for(i = shorterLength-1, j = longerLength-1; i >= 0; i--, j--){
			carry = carry + this->digits[j] +rhs.digits[i];
			sum.digits[j+1] = carry % 10;
			carry = carry/10;
		}
		
		for(j; j >= 0; j--){
			carry = carry + this->digits[j];
			sum.digits[j+1] = carry % 10;
			carry = carry/10;
		}
	}
	
	//If second BigInt longer, do calculations based on that information
	if(rhs.size > this->size){
		int j = 0;
		int i = 0;
		for(i = shorterLength-1, j = longerLength-1; i >= 0; i--, j--){
			carry = carry + this->digits[i] + rhs.digits[j];
			sum.digits[j+1] = carry % 10;
			carry = carry/10;
		}
		
		for(j; j >= 0; j--){
			carry = carry + rhs.digits[j];
			sum.digits[j+1] = carry % 10;
			carry = carry/10;
		}
	}
	
	//If both BigInts are equal in length, do calculations based on that information
	if(rhs.size == this->size){
		for(int i = shorterLength-1; i >= 0; i--){
			carry = carry + this->digits[i] + rhs.digits[i];
			sum.digits[i+1] = carry % 10;
			carry = carry/10;
		}
	}
	sum.digits[0] = carry;
	
	sum.stripZeros();
	
	return sum;
}

//===================================================================
CBigInteger CBigInteger::operator-(const CBigInteger& rhs) const{

	if(this->sign == -1 && rhs.sign == 1){//If first is negative and second positive, switch sign, call addition
		CBigInteger temp = rhs;
		temp.sign = -1;
		return *this + temp;
	}
	
	if(this->sign == -1 && rhs.sign == -1){//If Both negative, switch signs, call subtraction
		CBigInteger temp = rhs;
		temp.sign = 1;
		CBigInteger temp2 = *this;
		temp2.sign = 1;
		return temp - temp2;
	}
	
	if(this->sign == 1 && rhs.sign == -1){//If first positive and second negative, switch sign, call addition
		CBigInteger temp = rhs;
		temp.sign = 1;
		return *this + temp;
	}

	int longerLength;
	int shorterLength;
	CBigInteger dif;
	int borrow = 0;
	
	if(this->size > rhs.size){
		longerLength = this->size;
		shorterLength = rhs.size;
	}
	else{
		longerLength = rhs.size;
		shorterLength = this->size;
	}
	
	if(*this < rhs){
		dif.sign = -1;
	}
	
	dif.digits = new int[longerLength];
	dif.size = longerLength;
	
	//If first BigInt is longer, do calculations based on that information
	if(this->size > rhs.size){
		int j = 0;
		int i = 0;
		for(i = shorterLength-1, j = longerLength-1; i >= 0; i--,j--){
			if((this->digits[j] - borrow) < rhs.digits[i]){
				dif.digits[j] = this->digits[j] - rhs.digits[i] - borrow + 10;
				borrow = 1;
			}
			else{
				dif.digits[j] = this->digits[j] - rhs.digits[i] - borrow;
				borrow = 0;
			}
		}
		
		for(j; j >= 0; j--){
			if((this->digits[j] - borrow) < 0){
				dif.digits[j] = this->digits[j] - borrow + 10;
				borrow = 1;
			}
			else{
				dif.digits[j] = this->digits[j] - borrow;
				borrow = 0;
			}
		}
	}
	
	//If second BigInt is longer, do calculations based on that information 
	if(this->size < rhs.size){
		int j = 0;
		int i = 0;
		for(i = shorterLength-1, j = longerLength-1; i >= 0; i--,j--){
			if(this->digits[i] > (rhs.digits[j] - borrow)){
				dif.digits[j] = rhs.digits[j] - this->digits[i] - borrow + 10;
				borrow = 1;
			}
			else{
				dif.digits[j] = rhs.digits[j] - this->digits[i] - borrow;
				borrow = 0;
			}
		}
		
		for(j; j >= 0; j--){
			if((rhs.digits[j] - borrow) < 0){
				dif.digits[j] = rhs.digits[j] - borrow + 10;
				borrow = 1;
			}
			else{
				dif.digits[j] = rhs.digits[j] - borrow;
				borrow = 0;
			}
		}
	}
	
	//If both BigInts are the same length, do calculations based on that information
	if(this->size == rhs.size){
		if(*this >= rhs){
			for(int i = shorterLength-1; i > 0; i--){
				if((this->digits[i] - borrow) < rhs.digits[i]){
					dif.digits[i] = this->digits[i] - rhs.digits[i] - borrow + 10;
					borrow = 1;
				}
				else{
					dif.digits[i] = this->digits[i] - rhs.digits[i] - borrow;
					borrow = 0;
				}
			}
		
			if((this->digits[0] - borrow) < rhs.digits[0]){
				dif.digits[0] = (this->digits[0] - rhs.digits[0] - borrow)*(-1);
				dif.sign = -1;
			}
			else{
				dif.digits[0] = this->digits[0] - rhs.digits[0] - borrow;
			}
		}
		else{
			for(int i = shorterLength-1; i > 0; i--){
				if((rhs.digits[i] - borrow) < this->digits[i]){
					dif.digits[i] = rhs.digits[i] - this->digits[i] - borrow + 10;
					borrow = 1;
				}
				else{
					dif.digits[i] = rhs.digits[i] - this->digits[i] - borrow;
					borrow = 0;
				}
			}
		
			if((rhs.digits[0] - borrow) < this->digits[0]){
				dif.digits[0] = (rhs.digits[0] - this->digits[0] - borrow)*(-1);
				dif.sign = -1;
			}
			else{
				dif.digits[0] = rhs.digits[0] - this->digits[0] - borrow;
			}
		}
	}
	
	dif.stripZeros();
	
	return dif;
}

//======================================================================
CBigInteger CBigInteger::operator*(const CBigInteger& rhs) const{

	int *res;//Stores final result
	int *hold;//Holds individual digit by digit calculations and zeros as place holders
	int firstLength = this->size-1;
	int secondLength = rhs.size-1;
	int i, j, x, y = 0, z = 0;//For loop and increment variables
	int rem = 0;
	int add = 0;
	CBigInteger result;
	res = new int[firstLength+secondLength+3]();//Initialize for maximum possible final digits

	if(this->size > rhs.size){//Use longest size for space calculations
		hold = new int[(this->size+2)*(this->size)*(2)+1]();//Initialize with certainty of enough space  
	}
	else{
		hold = new int[(rhs.size+2)*(rhs.size)*(2)+1]();//Initialize with certainty of enough space
	}
	
	//Multiply digit by digit(elementary style long multiplication)
	for(i = secondLength; i >= 0; i--){
		rem = 0;
		for(j = firstLength; j>=0; j--){
			hold[z++] = (this->digits[j]*rhs.digits[i] + rem)%10;
			rem = (this->digits[j]*rhs.digits[i] + rem)/10;
		}
		hold[z++] = rem;
		
		y++;
		for(x = 0; x < y; x++){//Add zeros for place holders
			hold[z++] = 0;
		}
	}
	
	z = 0;
	rem = 0;
	
	//Finish by summing calculations into a result array
	for(i = 0; i < firstLength+secondLength+2; i++){
		add = 0;
		x = 0;
		for(j = 1; j <= secondLength+1; j++){
			if(i <= firstLength + j){
				add = add + hold[x+i];
			}
			x = x + j + firstLength + 1;
		}
		res[z++] = (add + rem)%10;
		rem = (add + rem)/10;
	}
	res[z] = rem;
	
	//Prep CBigInteger to receive result array
	result.digits = new int[z];
	result.size = z;
	
	j= 0;
	for(i = z-1; i >= 0; i--){//Copy results into CBigInteger
		result.digits[j++] = res[i];
	}
	
	if(this->sign != rhs.sign){//Adjust CBigInteger to have proper sign(default is 1)
		result.sign = -1;
	}
	
	result.stripZeros();
	
	delete [] hold;
	delete [] res;
	
	return result;
}

//==================================================================
void CBigInteger::stripZeros(){
	
	int zeroCount = 0;
	
	//Find amount of leading zeros
	for(int i = 0; i < this->size && this->digits[i] == 0; i++){
		zeroCount++;
	}
	
	//If has leading zeros, resize and copy without leading zeros
	if(zeroCount > 0 && this->size > 1){
		int *temp = new int[this->size-zeroCount];
		for(int i = zeroCount, j = 0; i < this->size; i++, j++){
			temp[j] = this->digits[i];
		}
		this->size = this->size - zeroCount;
		delete [] this->digits;
		this->digits = new int[this->size];
		for(int i = 0; i < this->size; i++){
			this->digits[i] = temp[i];
		}
		delete [] temp;
		if(this->size == 0){
			this->size = 1;
			delete [] this->digits;
			this->digits = new int[1];
			this->digits[0] = 0;
		}
	}
}
