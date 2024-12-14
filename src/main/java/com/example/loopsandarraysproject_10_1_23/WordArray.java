package com.example.loopsandarraysproject_10_1_23;

import java.util.ArrayList;

public class WordArray {
    /*
    precondition: Requires an arrayList full of Strings to examine. Requires the string part desired to be found. Requires a boolean to determine if the user wants the words to be of a same case or not
    return: returns an Array list containing all the words in the arraylist stored in the instance of the class which have the specified portion
     */
    public ArrayList<String> findPartialWords(ArrayList<String> listOfWords, String wordDesired, boolean sameCase){
        ArrayList<String> partialWords = new ArrayList<>();
        for(String i : listOfWords){
            int interval = 0;
            boolean wordFound = false;
            while(interval < i.length() - wordDesired.length() + 1 && !wordFound){
                String wordBit = i.substring(interval, interval + wordDesired.length());
                wordFound = wordBit.toLowerCase().equals(wordDesired.toLowerCase()) && !sameCase || wordBit.equals(wordDesired);
                interval++;
            }
            if(wordFound){partialWords.add(i);}
        }
        return partialWords;
    }

    /*
    precondition:  Requires an arrayList full of Strings to examine. Requires the string desired to be found. Requires a boolean to determine if the user wants the words to be of a same case or not
    return: returns a boolean value describing if the word is present in the array or not
     */
    public boolean isWordPresent(ArrayList<String> listOfWords, String wordDesired, boolean sameCase){return this.getIndexesOfWord(listOfWords, wordDesired, sameCase).size() > 0;}

    /*
    precondition:  Requires an arrayList full of Strings to examine. Requires the string desired to be found. Requires a boolean to determine if the user wants the words to be of a same case or not
    return: returns an array list returning all the indexes of the word in the array list stored in the specified instance of the class
     */
    public ArrayList<Integer> getIndexesOfWord(ArrayList<String> listOfWords, String wordDesired, boolean sameCase){
        ArrayList<Integer> indicesOfWord = new ArrayList<>();
        for(int i = 0; i< listOfWords.size(); i++){if((listOfWords.get(i).toLowerCase().equals(wordDesired.toLowerCase()) && !sameCase) || listOfWords.get(i).equals(wordDesired)){indicesOfWord.add(i);}}
        return indicesOfWord;
    }
}
