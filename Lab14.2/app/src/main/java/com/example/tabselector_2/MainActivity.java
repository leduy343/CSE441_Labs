package com.example.tabselector_2;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tabselector_2.adapter.myarrayAdapter;
import com.example.tabselector_2.model.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edttim;
    ListView lv1,lv2,lv3;

    TabHost tab;
    ArrayList<Item> list1,list2,list3;

    myarrayAdapter myarr1,myarr2,myarr3;
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
        addControl();
        addEvent();

    }
    private void addEvent(){
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t1")){
                    list1.clear();
                    list1.add(new Item("000001","time machine",0));
                    list1.add(new Item("000002","Da tan",1));
                    list1.add(new Item("000003","lemon",0));
                    myarr1.notifyDataSetChanged();
                }
                if (tabId.equalsIgnoreCase("t2")){
                    list2.clear();
                    list2.add(new Item("000023","golden hour",0));
                    list2.add(new Item("000013","as it was",1));
                    list2.add(new Item("000011","Doremon",0));
                    myarr2.notifyDataSetChanged();
                }
                if (tabId.equalsIgnoreCase("t3")){
                    list3.clear();
                    list3.add(new Item("000023","golden hour",0));
                    list3.add(new Item("000013","as it was",1));
                    list3.add(new Item("000011","Doremon",0));
                    myarr3.notifyDataSetChanged();
                }
            }
        });
    }
    private  void addControl(){
        tab= (TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.search));
        tab.addTab(tab1);
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.search));
        tab.addTab(tab2);
        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("",getResources().getDrawable(R.drawable.search));
        tab.addTab(tab3);

        edttim =findViewById(R.id.edttim);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);

        list1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3 = new ArrayList<Item>();

        myarr1 = new myarrayAdapter(MainActivity.this,R.layout.lisitem,list1);
        myarr2 = new myarrayAdapter(MainActivity.this,R.layout.lisitem,list2);
        myarr3 = new myarrayAdapter(MainActivity.this,R.layout.lisitem,list3);


        lv1.setAdapter(myarr1);
        lv2.setAdapter(myarr2);
        lv3.setAdapter(myarr3);
    }
}