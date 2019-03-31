package com.jablonskiwebdevelopment.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private String calcInput = "0";

    public String getCalcInput() {
        return calcInput;
    }

    public String typeCalcInput(String input) {
        //TODO: Replace two operations in a row with current operation
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
            Pattern p = Pattern.compile("(^\\d)$");
            Matcher m = p.matcher(input);
            System.out.println("DOUBLE DUPLICATES");

            int lastCharIndex = calcInput.length() - 1;
            String lastChar = calcInput.substring(lastCharIndex);
            if (isOperation(lastChar)) {
                //FIXME: Not working correctly. Replacing full string instead of just last character. Check regexp
                return m.replaceFirst(input);
            }
        }
        return calcInput + input;
    }

    public void clear() {
        this.calcInput = "0";
    }

    private boolean isOperation(String input) {
        System.out.println("IS OPERATION" + input == "/");
        return input.equals("+")|| input.equals("-") || input.equals("x")|| input.equals("/");
    }
}
