package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) 
	{

		for (int i = 0; i < number.length(); i++) 
		{
			//Check if character for conditions if not 1 or 0
			char ch = number.charAt(i);
			if(ch!='0' && ch!='1') 
			{
				number ="0"; //If not 1 or 0, store 0
				return;
			}
		}
		// Removing trailing zeros
		int trail;
		for (trail = 0; trail < number.length(); trail++) 
		{
			if (number.charAt(trail)!='0')
				break;
		}
		// Excluding trailing zeros, if are.
		this.number=number.substring(trail); 

		// uncomment the following code
		if(this.number =="") { // replace empty strings with a single zero
			this.number ="0";
		}

    }

	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}

	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The fians addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		
		int index1 = num1.number.length()-1;
		int index2 = num2.number.length()-1;
		
		int carry = 0;
		String num3 = "";  //Binary sum

		//Looping until everything has been processed
		while(index1>=0 ||  index2>=0 || carry!=0)
		{
			int sum = carry; //Setting Carry

			if(index1>=0){
				sum += (num1.number.charAt(index1)=='1')? 1:0; //Converts digits to sums.
				index1--; //Update
			}

			if(index2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(index2)=='1')? 1:0; //Converts digits to sums.
				index2--; //Update
			}

			carry = sum/2; //Setting NEW Carry
			sum = sum%2;  //Result
			num3 = ((sum==0)? "0":"1") + num3; //Converting the sum into String + num3
		}

		Binary ans = new Binary(num3); //Giving an object the sum value
		return ans;
	}

	public static Binary OR(Binary num1,Binary num2)
	{
		
		int index1 = num1.number.length()-1;
		int index2  =num2.number.length()-1;
		
		int sum = 0;
		String num3 ="";  //Binary sum

		//Looping
		while(index1 >=0  ||  index2>=0 )
		{
			if(index1>=0 || index2>=0) //Set condition if index1 or index2 >= 0
			{ 
				sum = (index1 >= 0 && num1.number.charAt(index1) == '1') || (index2 >= 0 && num2.number.charAt(index2) == '1') ? 1 : 0; //Converts digits to sums.
			}

			index1--;
			index2--;

			num3 =( (sum==1)? "1":"0") + num3; //Converting the sum into String + num3
		}

		Binary ans = new Binary(num3); //Giving an object the sum value
		return ans;
	}

	public static Binary AND(Binary num1, Binary num2) 
	{
		int index1 = num1.number.length() - 1;
		int index2 = num2.number.length() - 1;
		int sum = 0;
	
		String num3 = "";
	
		while (index1 >= 0 || index2 >= 0) //If either values are greater than 0.
		{ 
			sum = 0; // set sum to 0
			if (index1 >= 0 && index2 >= 0) //If both of the values inside of index 1 and index 2 are 1 then put 1 into the sum otherwise 0
			{
				sum = (num1.number.charAt(index1) == '1' && num2.number.charAt(index2) == '1') ? 1 : 0;
			}

			// Decrease index
			index1--;
			index2--;
			num3 = ((sum == 0) ? '0' : '1') + num3;
		}
	
		Binary ans = new Binary(num3); //Giving an object the sum value
		return ans;
	}


	public static Binary leftShift(Binary numshift, int shiftspace) //Making a shift function to help with the multiplication function; 'numshift' specifies what number to shift, while 'shiftspace' how many spaces to shift
	{
        return new Binary(numshift.getValue() + "0".repeat(shiftspace));
    }



	public static Binary Multiply(Binary num1, Binary num2) {

		//New class to hold 'ans' value
        Binary ans = new Binary("0");
		int index2 = num2.getValue().length() - 1;

		int count = 0;

		while (index2 >= 0) {
				
				if (num2.getValue().charAt(index2) == '1') 
				{
					// Shifting the bit and creating the binary object of the shifted number to be sent into add
					Binary shiftedNum = leftShift(num1, count);
					ans = add(ans, shiftedNum);
				}

			// Switch to the left bit
			index2--;
			count++;
		}
      
        return ans;
    }
}