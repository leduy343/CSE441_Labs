package com.example.intent_call_sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Call_Activity extends AppCompatActivity {

    ImageButton btncall;
    Button btncallBack;
    EditText edtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btncall = findViewById(R.id.ibtn_call);
        btncallBack = findViewById(R.id.btl_call_back1);
        edtPhone = findViewById(R.id.edt_phone);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("Tel: "+edtPhone.getText().toString()));
                if(ActivityCompat.checkSelfPermission(Call_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(Call_Activity.this, new String [] {Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(intent);
            }

        });
        btncallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}