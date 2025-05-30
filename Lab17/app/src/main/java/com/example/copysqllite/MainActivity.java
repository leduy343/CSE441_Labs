package com.example.copysqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String DB_PATH_SUFFIX  = "/database/";
    SQLiteDatabase database = null;
    String DATABASE_NAME = "qlysach.db";
    ListView lv;
    ArrayList<String> list;
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
        processCopy();
        database = openOrCreateDatabase("qlysach.db",MODE_PRIVATE,null);
        lv = findViewById(R.id.lv);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

        Cursor c = database.query("tbsach",null,null,null,null,null,null);
        c.moveToFirst();
        String data = "";
        while (c.isAfterLast()==false){
            data = c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2);
            list.add(data);
            c.moveToNext();

        }
        c.close();
        adapter.notifyDataSetChanged();
    }

    private void processCopy() {
        File dbfile = getDatabasePath(DATABASE_NAME);
        if(!dbfile.exists()){
            try{
                CopyDatabaseFromAsset();
                Toast.makeText(this,"Copy success",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try{
            InputStream myinput ;
            myinput =getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myoutput = new FileOutputStream(outFileName);

            int size = myinput.available();
            byte[] buffer = new byte[size];
            myinput.read(buffer);
            myoutput.write(buffer);
            myoutput.flush();
            myoutput.close();
            myinput.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }
}