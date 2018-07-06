package com.macor.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void asignarValor(View v) {
        Button bvalor = (Button) v;
        String valor = bvalor.getText().toString();

        //if (digit1 > 0) {
        //digit2 = Integer.parseInt(valor);
        //} else {
        //  digit1 = Integer.parseInt(valor);
        //}
        //Toast.makeText(this, "Digit1: " + digit1 + " Digit2: " + digit2, Toast.LENGTH_SHORT).show();
        //TextView display = (TextView) findViewById(R.id.display);
        //display.append(valor);
    }

    public void addNumber(View view) {
        final Button btnNumber = (Button) view;
        final String valor = btnNumber.getText().toString();
        final TextView txtOperation = findViewById(R.id.txtOperation);

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOperation.append(valor);
            }
        });
    }

    public void clearText(View view) {
        final Button btnClear = findViewById(R.id.clear);
        final TextView txtOperation = findViewById(R.id.txtOperation);

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String text = txtOperation.getText().toString();
                text = text.substring(0, text.length() - 1);
                txtOperation.setText(text);
            }
        });

        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                txtOperation.setText("");
                return true;
            }
        });
    }
}
