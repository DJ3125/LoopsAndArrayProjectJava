package com.example.loopsandarraysproject_10_1_23;

public class TwoNumberMath {
    /*
    precondition: Requires 2 integers to check if they are divisible
    return: returns a boolean value describing if the first parameter can be divisible by the second parameter and have a remainder of 0
     */
    public boolean isFirstNumDivisibleBySecondNum(int numOne, int numTwo){
        if(numTwo == 0){return false;}
        return numOne%numTwo == 0;
    }

    /*
    precondition: Requires 2 integer values, with the first value being divided by a second value. Also requires a string to determine whether the output should be a decimal, an improper fraction, or a proper fraction
    return: returns the quotient of the first parameter divided by the second parameter, either as a decimal, a proper fraction or an improper fraction, depending on if the string parameter is D, P, or I Respectively
     */
    public String getQuotient(int firstNum, int secondNum, char type){
        int greatestCommonFactor = this.getGreatestCommonFactor(Math.abs(firstNum), Math.abs(secondNum));
        if((double)firstNum/secondNum % 1 == 0){return "" + firstNum/secondNum;}
        else if(type == 'D'){return "" + ((int)Math.round((double)firstNum/secondNum * 100) / 100.0);}
        else if(type == 'P' && firstNum/secondNum != 0){return (firstNum/secondNum) + " " + Math.abs((firstNum/greatestCommonFactor) % (secondNum/greatestCommonFactor)) + "/" + Math.abs(secondNum)/greatestCommonFactor;}
        else{return ((firstNum/secondNum < 0) ? "-" : "") + Math.abs(firstNum/greatestCommonFactor) + "/" + Math.abs(secondNum/greatestCommonFactor);}
    }

    /*
    precondition: Requires 2 integer values to find the greatest common factor of
    return: Returns an integer value representing the greatest common factor between both numbers inputted
     */
    public int getGreatestCommonFactor(int firstNum, int secondNum){
        if(firstNum == secondNum && secondNum == 0){return -1;}
        else if(firstNum == 0 || secondNum == 0 || firstNum%secondNum == 0 || secondNum%firstNum == 0){return Math.max(firstNum, secondNum);}
        else if(firstNum == secondNum){return firstNum;}
        int greatestCommonFactor = 1;
        for(Integer i : (new OneNumberMath()).getPrimeFactorization(firstNum)){
            if(firstNum % i == 0 && secondNum % i == 0) {
                greatestCommonFactor *= i;
                firstNum/= i;
                secondNum /= i;
            }
        }
        return greatestCommonFactor;
    }
}
