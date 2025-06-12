package com.example.tabselector_2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tabselector_2.R;
import com.example.tabselector_2.model.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int LayoutID;
    public myarrayAdapter(Activity context, int resource,ArrayList<Item> arr) {
        super(context, resource,arr);

        this.context = context;
        this.LayoutID = resource;
        this.myArray = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView =inflater.inflate(LayoutID,null);
        Item myitem = myArray.get(position);
        ImageView btnlike = convertView.findViewById(R.id.btnlike);
        int thich = myitem.getThich();
        if(thich ==1){
            btnlike.setImageResource(R.drawable.favorite);
        }else {
            btnlike.setImageResource(R.drawable.unlike);
        }
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle trạng thái thích
                if (myitem.getThich() == 1) {
                    myitem.setThich(0);
                    btnlike.setImageResource(R.drawable.unlike);
                } else {
                    myitem.setThich(1);
                    btnlike.setImageResource(R.drawable.favorite);
                }
                // Nếu cần cập nhật lại dữ liệu toàn bộ list
                // notifyDataSetChanged(); // chỉ dùng nếu trạng thái ảnh hưởng đến cả list
            }
        });
        TextView txtanme = convertView.findViewById(R.id.txtname);
        txtanme.setText(myitem.getTieude());
        TextView txtmaso = convertView.findViewById(R.id.txtmaso);
        txtmaso.setText(myitem.getMaso());
        return  convertView;
    }
}