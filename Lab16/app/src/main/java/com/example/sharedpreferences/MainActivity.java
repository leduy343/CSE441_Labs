package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtA,edtB,edtKq;
    Button btnTong,btnclear;
    TextView txtlichsu;
    String Lichsu= "";
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
        edtKq = findViewById(R.id.edtKQ);
        btnTong = findViewById(R.id.btnTong);
        btnclear = findViewById(R.id.btnClear);
        txtlichsu = findViewById(R.id.txtLichsu);

        SharedPreferences sharedPreferences = getSharedPreferences("mysave",MODE_PRIVATE);
        Lichsu = sharedPreferences.getString("ls","");
        txtlichsu.setText(Lichsu);
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int kq = a+b;
                edtKq.setText(kq+"");
                Lichsu +="a "+" b = "+kq;
                txtlichsu.setText(Lichsu);
                Lichsu  += "\n";


            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lichsu ="";
                txtlichsu.setText(Lichsu);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("mysave",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ls",Lichsu);
        editor.commit();
    }
}