package com.example.loopsandarraysproject_10_1_23;

import java.util.ArrayList;

public class OneNumberMath {
    /*
    Precondition: requires 2 integers to specify the multiples of which number the user wants and how many multiples the user wishes to have
    Return: returns an ArrayList of numbers that represents the multiples of the 1st parameter. The amount of numbers it returns is determined by the second parameter inputted
     */
    public ArrayList<Integer> getMultiple(int numberToExamine, int amountMultiples){
        ArrayList<Integer> multiples = new ArrayList<>();
        for(int i = 1; i<= amountMultiples; i++){multiples.add(i * numberToExamine);}
        return multiples;
    }

    /*
    precondition: requires an integer to examine
    return: returns a String describing if the number inputted is even or not
     */
    public String isEven(int numberToExamine){
        if(numberToExamine%2==0){return "Even";}
        else{return "Odd";}
    }

    /*
    precondition: Requires an integer to get the factorial of
    returns: Returns the factorial as an integer of the number inputted
     */
    public int getFactorial(int numberToGetFactorial){
        int factorial = 1;
        for(int i = 1; i <= numberToGetFactorial; i++){factorial *= i;}
        return factorial;
    }

    /*
    precondition: Requires an integer to examine and get the prime factorization of.
    returns: returns a string of the prime factorization of the number inputted
     */
    public ArrayList<Integer> getPrimeFactorization(int numberToExamine){
        int tempNumber = numberToExamine;
        int primeNumberSelected = 2;
        ArrayList<Integer> primeFactorization = new ArrayList<>();
        while(primeNumberSelected <= numberToExamine/2 && tempNumber != 1){
            if(tempNumber % primeNumberSelected == 0){
                tempNumber /= primeNumberSelected;
                primeFactorization.add(primeNumberSelected);
            }else{primeNumberSelected++;}
        }
        if(primeFactorization.isEmpty()){primeFactorization.add(numberToExamine);}
        return primeFactorization;
    }
}