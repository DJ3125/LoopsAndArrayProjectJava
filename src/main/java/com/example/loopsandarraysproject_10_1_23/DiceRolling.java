package com.example.loopsandarraysproject_10_1_23;

import java.util.ArrayList;

public class DiceRolling {
    /*
    precondition: Requires the 2 integers representing the amount of sides on each dice, and the amount of dice that were rolled. Requires an ArrayList containing the sums of the rolls from each round
    return: Returns an arrayList containing how many of each sum there is based on the original ArrayList inputted
     */
    public ArrayList<Integer> getAmountOfEachSum(int amountOfSidesOnDice, int amountOfDice,  ArrayList<Integer> rollsToExamine){
        ArrayList<Integer> listOfSumQuantities = new ArrayList<>();
        for(int i = 0; i < amountOfDice*(amountOfSidesOnDice) - amountOfDice + 1; i++){listOfSumQuantities.add(0);}
        for(Integer i : rollsToExamine){listOfSumQuantities.set(i - amountOfDice, listOfSumQuantities.get(i - amountOfDice) + 1);}
        return listOfSumQuantities;
    }

    /*
    precondition: Requires an ArrayList of Integers
    return: Returns a string describing the minimum value, the maximum value, and the average value of the values in the ArrayList
     */
    public String getMinMaxAndAverageForDiceRoll(ArrayList<Integer> rollsToExamine){
        int min, max;
        double average = 0;
        min = max = 0;
        for(Integer i : rollsToExamine){
            if(min == 0 || i < min){min = i;}
            max = Math.max(max, i);
            average += i;
        }
        average /= rollsToExamine.size();
        return "Min = " + min + ", Max = " + max + ", Average = " + average;
    }

    /*
    precondition: Requires 4 integers and 1 decimal specifying the amount of sides on each dice, the amount of dice, the amount of rounds rolling, the weighted side on the dice, and the probability of the dice landing on the weighted side
    Return: Returns the an ArrayList with the sum of the dice rolls of each round
     */
    public ArrayList<Integer> getSumOfDiceRolls(int diceSides, int amountOfDice, int numRounds, int weightedSide, double probabilityOfWeightedSide){
        ArrayList<Integer> diceRolls = new ArrayList<>();
        double unweightedSideProbability = (1 - probabilityOfWeightedSide)/(diceSides - 1);
        for(int j = 0; j < numRounds; j++){
            int sum = 0;
            for(int i = 0; i < amountOfDice; i++){
                double chance = Math.random();
                int intervalSides = diceSides;
                boolean sideFound = false;
                while(!sideFound){
                    sideFound = chance >= 1 - ((diceSides - intervalSides) * unweightedSideProbability + ((intervalSides <= weightedSide) ? probabilityOfWeightedSide : 0) + ((intervalSides == weightedSide) ? 0 : unweightedSideProbability));
                    intervalSides--;
                }
                sum += intervalSides + 1;
            }
            diceRolls.add(sum);
        }
        return diceRolls;
    }

    /*
    precondition: Requires 3 integers specifying the amount of sides on each dice, the amount of dice, and the amount of rounds rolling
    Return: Returns the an ArrayList with the sum of the dice rolls of each round
     */
    public ArrayList<Integer> getSumOfDiceRolls(int diceSides, int amountOfDice, int numRounds){return getSumOfDiceRolls(diceSides, amountOfDice, numRounds, 1, 1.0/diceSides);}
}
