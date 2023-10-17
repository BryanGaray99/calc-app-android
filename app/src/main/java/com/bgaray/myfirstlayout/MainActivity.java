package com.bgaray.myfirstlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            button9, buttonPoint, buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonC,
            buttonCE, buttonEqual;
    private EditText display;
    private double currentValue = 0;
    private String operation;

    /**
     * Crear Controladores para la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedButton = (Button) view;
                String number = clickedButton.getText().toString();
                addToDisplay(number);
            }
        };

        display = findViewById(R.id.display);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonC = findViewById(R.id.buttonC);
        buttonCE= findViewById(R.id.buttonCE);

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);
        buttonPoint.setOnClickListener(numberClickListener);
        buttonDivide.setOnClickListener(view -> setOperation("/"));
        buttonMultiply.setOnClickListener(view -> setOperation("*"));
        buttonPlus.setOnClickListener(view -> setOperation("+"));
        buttonMinus.setOnClickListener(view -> setOperation("-"));
        buttonEqual.setOnClickListener(view -> calculate());
        buttonC.setOnClickListener(view -> clearAll());
        buttonCE.setOnClickListener(view -> clearEntry());
    }

    /**
     * Añade al display el número clickeado
     * @param number
     */
    public void addToDisplay(String number) {
        String currentText = this.display.getText().toString();
        if (number.equals(".") && currentText.contains(".")) {
            return;
        }
        if (currentText.equals("0") || currentText.isEmpty()) {
            this.display.setText(number);
        } else {
            this.display.setText(currentText + number);
        }
    }

    /**
     * Guardar Operación
     * @param op
     */
    private void setOperation(String op){
        this.currentValue = Double.parseDouble(this.display.getText().toString());
        this.display.setText("");
        this.operation = op;
    }

    /**
     * Realizar la operación aritmética
     */
    private void calculate() {
        double  secondValue = Double.parseDouble(this.display.getText().toString());
        double result = 0;
        switch (this.operation) {
            case "+":
                result = this.currentValue + secondValue;
                break;
            case "-":
                result = this.currentValue - secondValue;
                break;
            case "*":
                result = this.currentValue * secondValue;
                break;
            case "/":
                if (secondValue == 0){
                    this.display.setText("No se puede dividir para cero");
                }
                result = this.currentValue / secondValue;
                break;
        }
        this.display.setText(String.valueOf(result));
        this.currentValue = result;
    }

    /**
     * Método para borrar todo el contenido y reiniciar la calculadora.
     */
    private void clearAll() {
        display.setText("0");
        this.currentValue = 0;
        this.operation = "";
    }

    /**
     * Método para borrar la entrada actual en pantalla.
     */
    private void clearEntry() {
        this.display.setText("0");
    }
}