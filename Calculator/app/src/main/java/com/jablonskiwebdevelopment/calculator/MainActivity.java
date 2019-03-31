package com.jablonskiwebdevelopment.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator = new Calculator();

    // View Variables
    private Button button9;
    private Button button8;
    private Button button7;
    private Button button6;
    private Button button5;
    private Button button4;
    private Button button3;
    private Button button2;
    private Button button1;
    private Button button0;

    private Button division;
    private Button multiplication;
    private Button subtraction;
    private Button addition;
    private Button equals;

    private Button decimal;

    private Button clearButton;

    private TextView calculationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign Views from the layout file to variables
        calculationInput = findViewById(R.id.calculationInput);

        button9 = findViewById(R.id.button9);
        button8 = findViewById(R.id.button8);
        button7 = findViewById(R.id.button7);
        button6 = findViewById(R.id.button6);
        button5 = findViewById(R.id.button5);
        button4 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        button1 = findViewById(R.id.button1);
        button0 = findViewById(R.id.button0);

        division = findViewById(R.id.division);
        multiplication = findViewById(R.id.multiplication);
        subtraction = findViewById(R.id.subtraction);
        addition = findViewById(R.id.addition);

        clearButton = findViewById(R.id.clearButton);

        View.OnClickListener listener9 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("9"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener8 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("8"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener7 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("7"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener6 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("6"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("5"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("4"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("3"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("2"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("1"));
                calculator.showAnswer();
            }
        };
        View.OnClickListener listener0 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("0"));
                calculator.showAnswer();
            }
        };

        // Operation listeners
        View.OnClickListener listenerDivision = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("/"));
            }
        };

        View.OnClickListener listenerMultiplication = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("x"));
            }
        };

        View.OnClickListener listenerSubtraction = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("-"));
            }
        };

        View.OnClickListener listenerAddition = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText(calculator.typeCalcInput("+"));
            }
        };

        View.OnClickListener listenerClear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationInput.setText("0");
                calculator.clear();
            }
        };


        // Call event listeners on buttons
        button9.setOnClickListener(listener9);
        button8.setOnClickListener(listener8);
        button7.setOnClickListener(listener7);
        button6.setOnClickListener(listener6);
        button5.setOnClickListener(listener5);
        button4.setOnClickListener(listener4);
        button3.setOnClickListener(listener3);
        button2.setOnClickListener(listener2);
        button1.setOnClickListener(listener1);
        button0.setOnClickListener(listener0);

        division.setOnClickListener(listenerDivision);
        multiplication.setOnClickListener(listenerMultiplication);
        subtraction.setOnClickListener(listenerSubtraction);
        addition.setOnClickListener(listenerAddition);

        clearButton.setOnClickListener(listenerClear);

    }
}
