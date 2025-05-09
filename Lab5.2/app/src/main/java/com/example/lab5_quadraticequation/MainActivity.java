package com.example.lab5_quadraticequation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtA,edtB,edtC;
    TextView txtRslt;
    Button btnNext,btncal,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        txtRslt = findViewById(R.id.txtRslt);
        btncal = findViewById(R.id.btnCal);
        btnExit = findViewById(R.id.btnExit);
        btnNext = findViewById(R.id.btnNext);
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int c = Integer.parseInt(edtC.getText().toString());
                String result;
                DecimalFormat dcf = new DecimalFormat("#.0");

                if(a == 0){
                    if(b==0){
                        if (c==0){
                            result = "The equation has infinitely many solutions";
                        }
                        else{
                            result = "The equation has no solution";
                        }
                    }
                    else{
                        result = "The equation has one solution , x= "+dcf.format(-c/b);
                    }
                }
                else {
                    double delta = b*b-4*a*c;
                    if(delta<0){
                        result = "The equation has no solution";
                    } else if (delta == 0) {
                        result = "The equation has a double root x1=x2= "+ dcf.format(-b/(2*a));
                    }else{
                        result = "The equation has two solutions: x1 = " + dcf.format((-b+Math.sqrt(delta))/(2*a))+"; x2 = "+dcf.format((-b-Math.sqrt(delta))/(2*a));
                    }
                }
                txtRslt.setText(result);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtA.setText("");
                edtB.setText("");
                edtC.setText("");
                txtRslt.setText("");
                edtA.requestFocus();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}