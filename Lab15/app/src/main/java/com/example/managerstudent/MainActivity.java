package com.example.managerstudent;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText edtMalop, edtLop, edtSiso;
    Button btnInsert,btnquery,btndelete,btnupdate;

    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    SQLiteDatabase sqLiteDatabase;
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
        edtLop = findViewById(R.id.edtLop);
        edtMalop = findViewById(R.id.edtMaLop);
        edtSiso = findViewById(R.id.edtsiso);
        btndelete = findViewById(R.id.btndelete);
        btnquery = findViewById(R.id.btnquery);
        btnInsert = findViewById(R.id.btninsert);
        btnupdate = findViewById(R.id.btnupdate);

        lv = findViewById(R.id.lv);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arrayList);
        sqLiteDatabase = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);
        try{
            String sql = "CREATE TABLE tbllop(malop TEXT primary key,tenlop TEXT, siso INTEGER)";
            sqLiteDatabase.execSQL(sql);
        }catch (Exception e){
            Log.e("Error","Table da ton tai");
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtMalop.getText().toString();
                String tenlop = edtLop.getText().toString();
                int siso = Integer.parseInt(edtSiso.getText().toString());
                ContentValues values = new ContentValues();
                values.put("malop",malop);
                values.put("tenlop",tenlop);
                values.put("siso",siso);
                String msg = "";
                if(sqLiteDatabase.insert("tbllop",null,values)==-1){
                    msg = "Fail to insert Record";
                }
                else {
                    msg = "Insert record Sucessfully";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtMalop.getText().toString();
                int n = sqLiteDatabase.delete("tbllop","malop = ?",new String[]{malop});
                String msg ="";
                if(n==0){
                    msg ="No Record to delete";
                }
                else {
                    msg =n + "record is delete ";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siso = Integer.parseInt(edtSiso.getText().toString());
                String malop = edtMalop.getText().toString();
                ContentValues values = new ContentValues();
                values.put("siso",siso);
                int n= sqLiteDatabase.update("tbllop",values,"malop =?",new String[]{malop});
                String msg = "";
                if(n==0){
                    msg = "No record to update";
                }
                else {
                    msg = n+ " record is updated";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                Cursor c = sqLiteDatabase.query("ttblop",null,
                        null,null,null,null,null);
                c.moveToNext();
                String data = "";
                while (c.isAfterLast()==false)
                {
                    data = c.getString(0)+" - "+ c.getString(1) +" - "+ c.getString(2);
                    c.moveToNext();
                    arrayList.add(data);
                }
                c.close();
                adapter.notifyDataSetChanged();
            }
        });
    }
}