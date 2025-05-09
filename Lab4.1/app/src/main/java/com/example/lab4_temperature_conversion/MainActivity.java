package com.example.lab4_temperature_conversion;

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

    EditText edtcel,edtfah;
    Button btncel,btnfah,btnclr;
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

        edtcel = findViewById(R.id.edtcel);
        edtfah = findViewById(R.id.edtfah);
        btncel = findViewById(R.id.btncel);
        btnclr = findViewById(R.id.btnclr);
        btnfah =  findViewById(R.id.btnfah);
        btncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double fah = Double.parseDouble(edtfah.getText().toString());
                double cel;
                cel =   (fah-32)*5/9;
                DecimalFormat dcf = new DecimalFormat("#.00");
                edtcel.setText(dcf.format(cel).replace(",","."));
            }
        });
        btnfah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double cel = Double.parseDouble(edtcel.getText().toString());
                double fah;
                fah =   cel*1.8+32;
                DecimalFormat dcf = new DecimalFormat("#.00");
                edtfah.setText(dcf.format(fah).replace(",", "."));
            }
        });
        btnclr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtfah.setText("");
                edtcel.setText("");
            }
        });
    }
}