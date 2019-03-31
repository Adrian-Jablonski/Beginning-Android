package com.jablonskiwebdevelopment.calculator;

public class Operations {

    public String evaluate(String strNumb1, String operator, String strNumb2) {
        int numb1 = Integer.parseInt(strNumb1);
        int numb2 = Integer.parseInt(strNumb2);
        int answer = 0;
        switch (operator) {
            case "+":
                answer = addition(numb1, numb2);
                System.out.println("PLUS ANSWER " + answer);
                break;
            case "-":
                answer = subtraction(numb1, numb2);
                break;
            case "x":
                answer = multiplication(numb1, numb2);
                break;
            case "/":
                answer = division(numb1, numb2);
                break;
            default:
                break;
        }
        System.out.println("ANSWER " + answer);
        return answer + "";
    }

    public int addition(int numb1, int numb2) {
        return numb1 + numb2;
    }

    public int subtraction(int numb1, int numb2) {
        return numb1 - numb2;
    }

    public int multiplication(int numb1, int numb2) {
        return numb1 * numb2;
    }

    public int division(int numb1, int numb2) {
        return numb1 / numb2;
    }
}
