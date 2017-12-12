package testCode;

class Encryption{
	public static void main(String[] args) {
		String encrypted_password = encrypt.encrypt("hello world");
		System.out.print(encrypted_password);
	}
}

class encrypt {
	public static String encrypt(String password){
	    char[] password_char = password.toCharArray();
	    for(int i = 0; i < password_char.length; i++) {
	        password_char[i] *= 1;  //should change letter to num
	    }
	    int FIRST_INDEX = 0;
	    int SWAP_INDEX = 0;
	    int LAST_INDEX = password.length()-1;
	    int STARTING_POWER;
	    int MULTIPLIER = 4;  // should be even to prevent truncating
	    int POWER = 2;
	    int LENGTH = password_char.length;
	    return encryption(password_char, FIRST_INDEX, LAST_INDEX, SWAP_INDEX, MULTIPLIER, POWER, LENGTH);
	}
	
	//recursive
	private static String encryption(char[] password, int first, int last, int swap, int multiplier, int power, int length) {
	    //multiply
	    for(int i = 0; i < length+1; i++) {
	        if(i%2==0){
	            password[i] = (char) (password[i] * multiplier);
	        }
	    }
	    
	    //swap
	    int first_num= password[swap];
	    int last_num= password[password.length-swap-1];
	    password[first] = (char) last_num;
	    password[last] = (char) first_num;
	
	    //power
	    for(int i = 0; i < length; i++) {
	        if(i%2==1){
	            password[i] = (char) Math.pow(password[i], power);
	        }
	    }
	
	    //divide
	    for(int i = 0; i < length; i++) {
	        if(i%2==0){
	            password[i] = (char) (password[i] * multiplier);
	        }
	    }
	    
	    //recursion
	    if(swap<(length/2)){
	         return encryption(password, first, last, swap++, multiplier++, power++, length);
	    }
	    else{
	        String encrypted_string = "";
	        for(int i =0; i < password.length; i++) {
	        	encrypted_string += password[i];
	        }
	        return encrypted_string;
	    }
	    
	    /*TODO:
	     * PROBLEMS:
	     * • Recursion
	     * • high # chars do not exist, printing out ?s
	     */

	}
}