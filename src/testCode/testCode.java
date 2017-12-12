package testCode;

class encrypt_recursive {

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
    int POWER = 2:
    int LENGTH = password_char.length;
    Char[] encrypted_char = new int {encrypt_recursive(password_char, FIRST_INDEX, LAST_INDEX, SWAP_INDEX, MULTIPLIER, POWER)};
    String encrypted_string = “”;
    for(int i = 0; i < LENGTH; i++){
        encrypted_string+=encrypted_char[i];
    }
    return encrypted_string;
}

//recursive
private static encrypt_recursive(char[] password, int first, int last, int swap, int multiplier, int power, int length) {
    //multiply
    for(int i = 0; i < length+1; i++) {
        if(i%2==0){
            password[i]*=multiplier ;
    }
    
    //swap
    int first_num= password[swap];
    Int last_num= password[password.length-swap];
    password[first] = last_num;
    password[last] = first_num;

    //power
    for(int i = 0; i < length+1; i++) {
        if(i%2==1){
            password[i] = password[i].pow(power);
        }
    }

    //divide
    for(int i = 0; i < length; i++) {
        if(i%2==0){
            password[i]/=multiplier;
    }

     if(swap<(length/2)){
         return password_recursive(password, first, last, swap++, multiplier++, power++);
    }
    else{
        return password;
    }
}
}