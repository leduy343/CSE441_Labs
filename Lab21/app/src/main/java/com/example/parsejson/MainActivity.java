package com.example.parsejson;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnparse;
    ListView lv;
    ArrayList<String> List;
    ArrayAdapter adapter;
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
        btnparse = findViewById(R.id.button);
        lv = findViewById(R.id.lv);
        List = new ArrayList<>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,List);
        lv.setAdapter(adapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parsejson();
            }
            private void parsejson() {
                String json = null;
                try {
                    InputStream inputStream = getAssets().open("computer.json");
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();
                    json = new String(buffer, "UTF-8");
                    JSONObject reader = new JSONObject(json);
                    List.add(reader.getString("MaDM"));
                    List.add(reader.getString("TenDM"));
                    JSONArray myarray = reader.getJSONArray("Sanphams");
                    for (int i = 0;i<myarray.length();i++)
                    {
                        JSONObject myobj = myarray.getJSONObject(i);
                        List.add(myobj.getString("MaSP")+" - "+myobj.getString("TenSP"));
                                List.add(myobj.getString("SoLuong")+
                                                " * "+myobj.getString("DonGia")+" = "+myobj.getString("ThanhTien"));
                                        List.add(myobj.getString("Hinh"));
                    }
                    adapter.notifyDataSetChanged();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                catch (JSONException e2)
                {
                    e2.printStackTrace();
                }
            }

        });
    }
}