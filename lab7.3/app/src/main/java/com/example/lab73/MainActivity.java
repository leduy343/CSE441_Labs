package com.example.lab73;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtA,edtB,edtKQ;
    Button btnRequest;

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
    edtKQ = findViewById(R.id.edtKq);
    btnRequest = findViewById(R.id.btnkq);

    btnRequest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            int a = Integer.parseInt(edtA.getText().toString());
            int b =  Integer.parseInt(edtB.getText().toString());
            intent.putExtra("soa",a);
            intent.putExtra("sob",b);

            startActivityForResult(intent,99);

        }
    });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode ==33){
            int sum =data.getIntExtra("kq",0);
            edtKQ.setText(sum+"");
        }
        if(requestCode ==99 && resultCode ==34){
            int sum =data.getIntExtra("kq",0);
            edtKQ.setText(sum+"");
        }
    }
}