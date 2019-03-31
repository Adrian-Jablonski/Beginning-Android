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
        //TODO: Show calculation answer while inputting text
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

    private boolean isOperation(String input) {
        return input.equals("+")|| input.equals("-") || input.equals("x")|| input.equals("/");
    }

    public void showAnswer() {
        String ansInput = calcInput;

        // Does multiplication and division first from left to right;
        Pattern patternMD = Pattern.compile("(\\d+)(x|/)(\\d+)");
        ansInput = evaluatingAnswer(ansInput, patternMD);

        // Does addition and subtraction from left to right;
        Pattern patternAS = Pattern.compile("(\\d+)(\\+|-)(\\d+)");
        ansInput = evaluatingAnswer(ansInput, patternAS);

        System.out.println("Answer Input " + ansInput);
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
}
