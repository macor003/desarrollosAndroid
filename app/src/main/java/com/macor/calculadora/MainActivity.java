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

    public void addNumber(View view) {
        final Button btnNumber = findViewById(R.id.number7);
        final TextView txtOperation = findViewById(R.id.txtOperation);

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOperation.append("7");
            }
        });
    }

    public void clearText(View view) {
        final Button btnClear = findViewById(R.id.clear);
        final TextView txtOperation = findViewById(R.id.txtOperation);

        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String text = txtOperation.toString();
                text = text.substring(0,text.length()-1);
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
