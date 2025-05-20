package com.example.lab73;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    EditText edtna,edtnb;
    Button btnTong,btnHieu;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtna = findViewById(R.id.edtnA);
        edtnb = findViewById(R.id.edtnB);
        btnHieu = findViewById(R.id.btnHieu);
        btnTong = findViewById(R.id.btnTong);
        intent = getIntent();
        int a = intent.getIntExtra("soa",0);
        int b = intent.getIntExtra("sob",0);

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a+b;
                intent.putExtra("kq",sum);
                setResult(33,intent);
                finish();
            }
        });
        btnHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hieu = a-b;
                intent.putExtra("kq",hieu);
                setResult(34,intent);
                finish();
            }
        });
    }
}