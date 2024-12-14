package com.example.loopsandarraysproject_10_1_23;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {
    @FXML Button startButton, confirmOneNumButton, confirmCypherOperationButton, diceRollButton;
    @FXML TabPane myTabPane;
    @FXML TextField oneNumberSelectedTextBox, multiplesTextBox, firstNumTextBox, secondNumTextBox, shiftAmountTextBox, wordExamineTextBox, diceSidesNumTextBox, diceAmountTextBox, sideWeightedTextBox, amountWeightedTextBox, diceRoundsTextBox, wordSearchTextBox;
    @FXML VBox multiplesVBox, caesarCypherVBox;
    @FXML ListView<String> oneNumOptionsListView, twoNumListView, cypherOptionsListView, cypherResultsListView, searchCriteriaListView, wordsListView, wordSearchListView;
    @FXML Label oneNumResultLabel, twoNumResultLabel, cypherErrorLabel, diceRollingErrorLabel, diceRollResults, diceStatistics, amountOfEachSum, wordListLabel;
    @FXML HBox weightedDiceHBox, diceResultsHBox;
    @FXML CheckBox diceWeightedCheckBox, caseSensitiveCheckBox;

    //Initialize the scene
    @FXML public void handleInitializeScene(){
        startButton.setDisable(true);
        startButton.setVisible(false);
        myTabPane.setDisable(false);
        myTabPane.setVisible(true);
        oneNumOptionsListView.getItems().addAll("Multiples Of The Number", "If It is Even Or Odd", "The Factorial", "The Prime Factorization");
        oneNumOptionsListView.getSelectionModel().selectFirst();
        twoNumListView.getItems().addAll("Is The First Num Divisible By The Second Num?", "First Num divided by the Second Num (Decimal Form)", "First Num divided by the Second Num (Improper Fraction Form)", "First Num divided by the Second Num (Proper Fraction Form)", "Greatest Common Factor");
        twoNumListView.getSelectionModel().selectFirst();
        cypherOptionsListView.getItems().addAll("Encode Caesar", "Decode Caesar", "Encode Atbash", "Decode Atbash");
        cypherOptionsListView.getSelectionModel().selectFirst();
        searchCriteriaListView.getItems().addAll("Whole Word", "Partial Word");
        searchCriteriaListView.getSelectionModel().selectFirst();
        wordsListView.getItems().addAll("Nauseous", "Dilate", "Indict", "Liquefy", "Wednesday", "Sherbet", "Bologna", "Ingenious", "Playwright", "Fuchsia", "Gobbledegook", "Pochemuchka", "Chiaroscurist");
        wordSearchListView.getItems().addAll(wordsListView.getItems());
    }

//  For the One Number Operations
    @FXML public void handleOneNumOptionClicked(){multiplesVBox.setDisable(!oneNumOptionsListView.getSelectionModel().getSelectedItem().equals("Multiples Of The Number"));}
    @FXML public void handleOneNumClicked(){
        try {
            OneNumberMath performOperations = new OneNumberMath();
            int myNum = Integer.parseInt(oneNumberSelectedTextBox.getText());
            switch (oneNumOptionsListView.getSelectionModel().getSelectedItem()) {
                case "Multiples Of The Number":
                    if (Integer.parseInt(multiplesTextBox.getText()) <= 0 || myNum <= 0) {oneNumResultLabel.setText("No Multiples");}
                    else {oneNumResultLabel.setText("" + performOperations.getMultiple(myNum, Integer.parseInt(multiplesTextBox.getText())));}
                    break;
                case "If It is Even Or Odd": oneNumResultLabel.setText(performOperations.isEven(myNum)); break;
                case "The Factorial":
                    if (myNum > 12 || myNum < 0) {oneNumResultLabel.setText("Not Possible. Numbers below 0 or above 12 aren't supported");}
                    else {oneNumResultLabel.setText("" + performOperations.getFactorial(myNum));}
                    break;
                case "The Prime Factorization":
                    if (myNum <= 1) {oneNumResultLabel.setText("Not Possible");}
                    else {oneNumResultLabel.setText("" + performOperations.getPrimeFactorization(myNum));}
                    break;
            }
        }catch (NumberFormatException e){oneNumResultLabel.setText("Invalid Inputs.");}
    }

    // For the Two Number Operation
    @FXML public void handleTwoNumConfirm(){
        try{
            int firstNum = Integer.parseInt(firstNumTextBox.getText());
            int secondNum = Integer.parseInt(secondNumTextBox.getText());
            TwoNumberMath performOperation = new TwoNumberMath();
            String selectedOperation = twoNumListView.getSelectionModel().getSelectedItem();
            if(selectedOperation.equals("Is The First Num Divisible By The Second Num?")){twoNumResultLabel.setText("" + performOperation.isFirstNumDivisibleBySecondNum(firstNum, secondNum));}
            else if (selectedOperation.equals("Greatest Common Factor")) {
                if(firstNum < 0 || secondNum < 0){twoNumResultLabel.setText("No negative values are allowed in this operation.");}
                else{
                    twoNumResultLabel.setText("" + performOperation.getGreatestCommonFactor(firstNum, secondNum));
                    if(twoNumResultLabel.getText().equals("1")){twoNumResultLabel.setText("none");}
                }
            }
            else if(secondNum == 0){twoNumResultLabel.setText("Not Possible. Cannot Divide By 0");}
            else{twoNumResultLabel.setText(performOperation.getQuotient(firstNum, secondNum, selectedOperation.charAt(selectedOperation.indexOf("(") + 1)));}
        }catch (NumberFormatException e){twoNumResultLabel.setText("Invalid Inputs.");}
    }

    //For the Cypher operations
    @FXML public void handleCypherOperationSelection(){
        shiftAmountTextBox.setText("");
        caesarCypherVBox.setDisable(cypherOptionsListView.getSelectionModel().getSelectedIndex() > 1);
    }
    @FXML public void handlePerformCypherOperation(){
        cypherErrorLabel.setText("");
        cypherResultsListView.getItems().clear();
        String wordToExamine = wordExamineTextBox.getText();
        Cyphers performOperation = new Cyphers();
        if(wordToExamine.equals("")){cypherErrorLabel.setText("Error: There is no word to examine");}
        else if (cypherOptionsListView.getSelectionModel().getSelectedIndex() <= 1) {
            if(shiftAmountTextBox.getText().equals("")){
                if(cypherOptionsListView.getSelectionModel().getSelectedIndex() == 0){cypherResultsListView.getItems().addAll(performOperation.encodeStringIntoCaesar(wordToExamine));}
                else{cypherResultsListView.getItems().addAll(performOperation.decodeStringIntoCaesar(wordToExamine));}
            }else{
                try{
                    int amountShift = Integer.parseInt(shiftAmountTextBox.getText());
                    if(cypherOptionsListView.getSelectionModel().getSelectedIndex() == 0){cypherResultsListView.getItems().add(performOperation.encodeStringIntoCaesar(wordToExamine, amountShift));}
                    else{cypherResultsListView.getItems().add(performOperation.decodeStringIntoCaesar(wordToExamine, amountShift));}
                }catch (NumberFormatException e){cypherErrorLabel.setText("Error: The input for the index to shift is invalid.");}
            }
        }else{cypherResultsListView.getItems().add(performOperation.encodeAndDecodeStringByAtbash(wordToExamine));}
    }

    //For the dice rolling
    @FXML public void handleDiceRoll(){
        diceRollingErrorLabel.setText("");
        try{
            int diceSides = Integer.parseInt(diceSidesNumTextBox.getText());
            int diceAmount = Integer.parseInt(diceAmountTextBox.getText());
            int diceRounds = Integer.parseInt(diceRoundsTextBox.getText());
            if(diceSides < 3){diceRollingErrorLabel.setText("You cannot have a dice with less than 3 sides");}
            else if(diceAmount <= 0){diceRollingErrorLabel.setText("You cannot have 0 or negative amount of dice");}
            else if(diceRounds <= 0){diceRollingErrorLabel.setText("You cannot have 0 or a negative amount of rounds");}
            else {
                ArrayList<Integer> diceRolls = null;
                DiceRolling performOperations = new DiceRolling();
                if(diceWeightedCheckBox.isSelected()){
                    if(Integer.parseInt(sideWeightedTextBox.getText()) < 1 || Integer.parseInt(sideWeightedTextBox.getText()) > diceSides){diceRollingErrorLabel.setText("You cannot weight a side that doesn't exist.");}
                    else if(Double.parseDouble(amountWeightedTextBox.getText()) < 0 || Double.parseDouble(amountWeightedTextBox.getText()) > 1){diceRollingErrorLabel.setText("You Cannot Have A probability less than 0 or greater than 1");}
                    else{diceRolls = performOperations.getSumOfDiceRolls(diceSides, diceAmount, diceRounds, Integer.parseInt(sideWeightedTextBox.getText()), Double.parseDouble(amountWeightedTextBox.getText()));}
                }else{diceRolls = performOperations.getSumOfDiceRolls(diceSides, diceAmount, diceRounds);}
                if(diceRolls != null){
                    diceResultsHBox.setVisible(true);
                    diceRollResults.setText("Results:\n" + diceRolls);
                    diceStatistics.setText(performOperations.getMinMaxAndAverageForDiceRoll(diceRolls));
                    amountOfEachSum.setText(performOperations.getAmountOfEachSum(diceSides, diceAmount, diceRolls).toString() + " from a range from " + diceAmount + " to " + diceSides * diceAmount);
                }
            }
        }catch (NumberFormatException e){diceRollingErrorLabel.setText("Invalid Inputs.");}
    }
    @FXML public void handleWeightDice(){
        weightedDiceHBox.setDisable(!diceWeightedCheckBox.isSelected());
        sideWeightedTextBox.setText("");
        amountWeightedTextBox.setText("");
    }

    //For word search
    @FXML public void handleSearchingForWord(){
        WordArray performOperations = new WordArray();
        String[] words = {"Nauseous", "Dilate", "Indict", "Liquefy", "Wednesday", "Sherbet", "Bologna", "Ingenious", "Playwright", "Fuchsia", "Gobbledegook", "Pochemuchka", "Chiaroscurist"};
        ArrayList<String> listToLookIn = new ArrayList<>();
        for(String i : words){listToLookIn.add(i);}
        wordSearchListView.getItems().clear();
        if(searchCriteriaListView.getSelectionModel().getSelectedItem().equals("Whole Word")){
            wordSearchListView.getItems().clear();
            wordSearchListView.getItems().addAll(wordsListView.getItems());
            wordListLabel.setText("Is Present: " + performOperations.isWordPresent(listToLookIn, wordSearchTextBox.getText(), caseSensitiveCheckBox.isSelected()) + "\n Indexes: " + performOperations.getIndexesOfWord(listToLookIn, wordSearchTextBox.getText(), caseSensitiveCheckBox.isSelected()).toString());
        }else{wordSearchListView.getItems().addAll(performOperations.findPartialWords(listToLookIn, wordSearchTextBox.getText(), caseSensitiveCheckBox.isSelected()));}
    }
}