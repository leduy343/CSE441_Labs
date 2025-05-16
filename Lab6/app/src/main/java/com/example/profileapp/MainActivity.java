package com.example.profileapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtCM,edtBoSung;
    Button btnSend;
    CheckBox chkbao,chkcode,chksach;
    RadioGroup group;
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
        edtCM = findViewById(R.id.edtCMND);
        edtBoSung = findViewById(R.id.edtBoSung);
        btnSend = findViewById(R.id.btnsend);
        chkbao = findViewById(R.id.chkbao);
        chkcode = findViewById(R.id.chkcode);
        chksach = findViewById(R.id.chksach);
        group = findViewById(R.id.group);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInformation();
            }
        });

    }
    public void ShowInformation(){
        String ten = edtName.getText().toString();
        ten = ten.trim();
        if(ten.length()<3){
            edtName.requestFocus();
            edtName.selectAll();
            Toast.makeText(this,"Ten phai >=3 ki tu",Toast.LENGTH_SHORT).show();
            return;
        }
        String cmnd = edtCM.getText().toString();
        cmnd = cmnd.trim();
        if(cmnd.length()!=9){
            edtCM.requestFocus();
            edtCM.selectAll();
            Toast.makeText(this,"cmnd phai co dung 9 ki tu",Toast.LENGTH_SHORT).show();
            return;
        }
        String bang="";
        group = findViewById(R.id.group);
        int id = group.getCheckedRadioButtonId();
        if (id==-1){
            Toast.makeText(this,"Phai chon bang cap",Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rad = findViewById(id);
        bang = rad.getText()+"";
        String sothich = "";
        if(chksach.isChecked()){
            sothich+=chksach.getText()+"\n";
        }
        if(chkbao.isChecked()){
            sothich+=chkbao.getText()+"\n";
        }
        if(chkcode.isChecked()){
            sothich+=chkcode.getText()+"\n";
        }
        String bosung = edtBoSung.getText()+"";

        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setTitle("thong tin ca nhan");
        builder.setPositiveButton("Dong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        String msg = ten+"\n"+cmnd+"\n"+bang+"\n"+sothich+"\n"+"Thong tin bo sung\n"+bosung+"\n";
        builder.setMessage(msg);
        builder.create().show();
    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setPositiveButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
        return super.getOnBackInvokedDispatcher();
    }
}