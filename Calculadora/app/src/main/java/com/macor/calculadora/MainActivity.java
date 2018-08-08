package com.macor.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    boolean ready = false;
    Double digit1 = Double.valueOf(0);
    Double digit2;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addNumber(View view) {
        final Button btnNumber = (Button) view;
        final String valor = btnNumber.getText().toString();
        final TextView txtOperation = findViewById(R.id.txtOperation);

        txtOperation.append(valor);

        if (ready) {
            doOperation(txtOperation);
        }
    }

    public void addOperation(View view) {
        final Button btnOperation = (Button) view;
        final String operator = btnOperation.getText().toString();
        final TextView txtOperation = findViewById(R.id.txtOperation);

        if (digit1 > 0) {
            digit2 = Double.valueOf(txtOperation.getText().toString());
        } else {
            digit1 = Double.valueOf(txtOperation.getText().toString());
        }

        ready = true;
        txtOperation.append(operator);

    }

    public void clearText(View view) {
        final Button btnClear = findViewById(R.id.clear);
        final TextView txtOperation = findViewById(R.id.txtOperation);

        if (!txtOperation.getText().toString().matches("")) {

            String text = txtOperation.getText().toString();
            text = text.substring(0, text.length() - 1);
            txtOperation.setText(text);

            btnClear.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    txtOperation.setText("");
                    return true;
                }
            });
        } else {
            Log.i(TAG, "--------->>>>  Texto vacio");
        }

    }

    private void doOperation(TextView txtOperation) {

        String operando1 = "";
        String operando2 = "";
        String operador = "";

        String acumulador = "";


        for (int i = 0; i < txtOperation.length(); i++) {

            String digito = Integer.toString(txtOperation.length());

            Log.i("Method doOperation", String.valueOf(txtOperation.length()));

            if (digito == "+" | digito == "-" | digito == "/" | digito == "X") {
                operador = digito;
                operando1 = acumulador;
                acumulador = "";
            } else {
                acumulador = acumulador + digito;
            }

        }

    }
}
