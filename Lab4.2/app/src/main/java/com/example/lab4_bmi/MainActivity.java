package com.example.lab4_bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtWeight, edtHeight, edtBMI, edtDiag;
    Button btnBMI;
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
        edtName = findViewById(R.id.edtName);
        edtBMI=findViewById(R.id.edtBmi);
        edtWeight =findViewById(R.id.edtWeight);
        edtDiag = findViewById(R.id.edtDiag);
        edtHeight = findViewById(R.id.edtHeight);
        btnBMI = findViewById(R.id.btnBmi);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H  = Double.parseDouble(edtHeight.getText()+"");
                double W = Double.parseDouble(edtWeight.getText() +"");
                double BMI = W/Math.pow(H,2);
                String Diag ="" ;
                if(BMI<18){
                    Diag = "Underweight";
                } else if (BMI<=24.9) {
                   Diag = "Normal weight";
                } else if (BMI<29.9) {
                    Diag= "Obesity (Class 1)";
                } else if (BMI<=34.9) {
                    Diag= "Obesity (Class 2)";
                }else{
                    Diag= "Obesity (Class 3)";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                edtDiag.setText(Diag);

            }
        });
    }
}