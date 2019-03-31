package com.jablonskiwebdevelopment.calculator;

import android.service.autofill.FieldClassification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Operations operations = new Operations();

    private String calcInput = "0";

    public String getCalcInput() {
        return calcInput;
    }

    public String typeCalcInput(String input) {
        if (isOperation(input)) {
            this.calcInput = checkForDuplicateOperations(input);
        }
        else if (calcInput.equals("0")) {
            this.calcInput = input;
        }
        else {
            this.calcInput = calcInput + input;
        }
        return calcInput;
    }

    private String checkForDuplicateOperations(String input) {
        if (calcInput.equals("0")) {
            return "0";
        }
        else {
            int lastCharIndex = calcInput.length() - 1;
            String lastChar = calcInput.substring(lastCharIndex);
            if (isOperation(lastChar)) {
                return calcInput.substring(0, lastCharIndex) + input;
            }
        }
        return calcInput + input;
    }

    public void clear() {
        this.calcInput = "0";
    }

    public String backSpace() {
        //TODO: Backspace right after pressing equals should clear input
        int lastCharIndex = calcInput.length() - 1;
        if (lastCharIndex > 0) {
            this.calcInput = calcInput.substring(0, lastCharIndex);
        }
        return calcInput;
    }

    public String equals() {
        this.calcInput = this.showAnswer();
        return calcInput;
    }

    private boolean isOperation(String input) {
        return input.equals("+")|| input.equals("-") || input.equals("x")|| input.equals("/");
    }

    //TODO: Add decimal functionality

    public String showAnswer() {
        String ansInput = calcInput;

        // Does multiplication and division first from left to right;
        Pattern patternMD = Pattern.compile("(-?\\d+)(x|/)(-?\\d+)");
        ansInput = evaluatingAnswer(ansInput, patternMD);

        // Does addition and subtraction from left to right;
        Pattern patternAS = Pattern.compile("(-?\\d+)(\\+|-)(-?\\d+)");
        ansInput = evaluatingAnswer(ansInput, patternAS);

        return removeOperatorFromLastChar(ansInput);
    }

    private String evaluatingAnswer(String ansInput, Pattern pattern) {
        Matcher matcher = pattern.matcher(ansInput);
        while (matcher.find()) {   // Runs while matches exist from left to right
            String evaluatedAnswer = operations.evaluate( matcher.group(1),  matcher.group(2),  matcher.group(3));
            ansInput = ansInput.replaceFirst(Pattern.quote(matcher.group(0)), evaluatedAnswer); // Pattern.quote escapes + character sign inside string
            matcher = pattern.matcher(ansInput);
        }
        return ansInput;
    }

    private String removeOperatorFromLastChar(String input) {
        Pattern pattern = Pattern.compile("[^\\d]$");
        Matcher matcher = pattern.matcher(input);
        System.out.println("Matched last non digit char ");
        if (matcher.find()) {
            System.out.println("FOUND MATCH");
            input = input.substring(0, input.length() - 1);
            System.out.println(input);
        }
        return input;
    }
}
