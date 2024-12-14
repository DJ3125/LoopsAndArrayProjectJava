package com.example.loopsandarraysproject_10_1_23;

import java.util.ArrayList;

public class Cyphers {
    /*
    precondition: Requires a String to modify. Requires an integer to shift all the letters stored in the first String parameter forward by a certain amount.
    returns: Returns a string representing the original string inputted with all the letters shifted forward a certain amount
     */
    public String encodeStringIntoCaesar(String stringToShift, int shiftAmount){return this.shiftLettersInString(stringToShift, shiftAmount, true);}

    /*
    precondition: Requires a String to modify.
    returns: Returns a string representing the original string inputted with all the letters shifted forward a random amount
     */
    public String encodeStringIntoCaesar(String stringToShift){return this.shiftLettersInString(stringToShift, (int)(Math.random() * 25) + 1, true);}

    /*
    precondition: Requires a String to modify. Requires an integer to shift all the letters stored in the first String parameter backward by a certain amount.
    returns: Returns a string representing the original string inputted with all the letters shifted backwards a certain amount
     */
    public String decodeStringIntoCaesar(String stringToShift, int shiftAmount){return this.shiftLettersInString(stringToShift, shiftAmount, false);}

    /*
    precondition: Requires a String to modify
    return: Returns an array list containing all the possible modifications of the string with all the letters being shifted forwards the same amount. It represents all the possible versions of the decoded string
     */
    public ArrayList<String> decodeStringIntoCaesar(String stringToShift){
        ArrayList<String> allPossibleValues = new ArrayList<>();
        for(int i = 0; i< 25; i++){allPossibleValues.add(this.shiftLettersInString(stringToShift, i, false));}
        return allPossibleValues;
    }

    /*
    precondition: Requires a String to modify
    return: Returns a string in which all the letters stored in the string inputted are inverted as according to the atbash cypher
     */
    public String encodeAndDecodeStringByAtbash(String stringToModify){
        String newString = "";
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i<stringToModify.length(); i++){
            String partToExamine = stringToModify.substring(i, i + 1);
            if(Character.isLetter(stringToModify.charAt(i))){
                if(Character.isUpperCase(stringToModify.charAt(i))){
                    int positionOfModified = 25 - upperCaseLetters.indexOf(partToExamine);
                    newString = newString.concat(upperCaseLetters.substring(positionOfModified, positionOfModified + 1));
                }else{
                    int positionOfModified = 25 - lowerCaseLetters.indexOf(partToExamine);
                    newString = newString.concat(lowerCaseLetters.substring(positionOfModified, positionOfModified + 1));
                }
            }else{newString = newString.concat(partToExamine);}
        }
        return newString;
    }

    /*
    precondition: Requires a String to modify. Requires an integer to shift all the letters stored in the String stored in the instance of the class by a certain amount. Requires a boolean to know whether to shift all the letters forwards or backwards
    returns: Returns a string representing the original string inputted with all the letters shifted a certain amount
     */
    private String shiftLettersInString(String stringToShift, int shiftAmount, boolean shiftForwards){
        String encodedString = "";
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        shiftAmount *= (shiftForwards) ? 1 : -1;
        for(int i = 0; i<stringToShift.length(); i++){
            String partToExamine = stringToShift.substring(i, i + 1);
            if(Character.isLetter(stringToShift.charAt(i))){
                if(Character.isUpperCase(stringToShift.charAt(i))){
                    int positionOfModified = (upperCaseLetters.indexOf(partToExamine) + (shiftAmount%26) + 26) % 26;
                    encodedString = encodedString.concat(upperCaseLetters.substring(positionOfModified, positionOfModified + 1));
                }else{
                    int positionOfModified = (lowerCaseLetters.indexOf(partToExamine) + (shiftAmount%26) + 26) % 26;
                    encodedString = encodedString.concat(lowerCaseLetters.substring(positionOfModified, positionOfModified + 1));
                }
            }else{encodedString = encodedString.concat(partToExamine);}
        }
        return encodedString;
    }
}
