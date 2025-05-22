package com.example.calendarnote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

TextView txtDate;
EditText edtHour,edtmi,edtNote;
Button btnadd;
ListView lsv;

ArrayList<String> arrayList;
ArrayAdapter<String> adapter;
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

        txtDate= findViewById(R.id.txtDate);
        edtHour= findViewById(R.id.edthour);
        edtmi= findViewById(R.id.edtminute);
        edtNote= findViewById(R.id.edtwork);
        btnadd= findViewById(R.id.btnadd);
        lsv= findViewById(R.id.lsv);

        arrayList =new ArrayList<>();
    //Khao bao adapter, dua du lieu cua arraylist vao adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
    //Dua du lieu adapter vao listview
        lsv.setAdapter(adapter);
    //Lay du lieu thoi gian he thong
        //txtDate.setText(Calendar.getInstance().getTime().toString());
        Date currentdate = Calendar.getInstance().getTime();
        //format ngay thang
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hom nay: "+simpleDateFormat.format(currentdate));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNote.getText().toString().equals("")||
                        edtHour.getText().toString().equals("")||
                        edtmi.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                else {
                    String str = edtNote.getText().toString()+" - "+edtHour.getText().toString()+" - "+
                            edtmi.getText().toString();

                    arrayList.add(str);
                    adapter.notifyDataSetChanged();
                    edtmi.setText("");
                    edtNote.setText("");
                    edtHour.setText("");
                }
            }
        });
    }
}