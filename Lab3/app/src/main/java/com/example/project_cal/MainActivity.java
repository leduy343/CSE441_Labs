package com.example.project_cal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtA,edtB,edtRslt;
    Button btnminus,btnplus,btntimes,btndiv;
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
        edtA =findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtRslt = findViewById(R.id.edtTotal);
        btndiv = findViewById(R.id.btnDiv);
        btnminus =findViewById(R.id.btnSub);
        btnplus = findViewById(R.id.btnPlus);
        btntimes =findViewById(R.id.btnMul);
        btntimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());
                edtRslt.setText("a * b = " +(a*b));
            }
        });
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());
                edtRslt.setText("a + b = " +(a+b));
            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());

                edtRslt.setText("a - b = " +(a-b));
            }
        });
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());
                if( b == 0){
                    edtRslt.setText("B can't to 0");
                }
                else{
                     edtRslt.setText("a / b = "+ (a/b));
                }
            }
        });
    }
}