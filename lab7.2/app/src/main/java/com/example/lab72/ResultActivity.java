package com.example.lab72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    TextView txtkq;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtkq = findViewById(R.id.txtkq);
        btnback = findViewById(R.id.btnback);
        Intent yourIntent = getIntent();
        Bundle yourbundle = yourIntent.getBundleExtra("mybackage");
        int a = yourbundle.getInt("soa");
        int b = yourbundle.getInt("sob");

        String kq ="";
        if (a==0&&b==0){
            kq = "vo so nghiem";
        }
        else if(a==0&& b!=0){
            kq = "vo nghiem";
        }
        else{
            DecimalFormat dcf = new DecimalFormat("0.##");
            kq = dcf.format(-b*1.0/a);

        }
        txtkq.setText(kq);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}