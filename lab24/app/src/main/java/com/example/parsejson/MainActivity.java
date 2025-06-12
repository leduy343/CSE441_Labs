package com.example.parsejson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lvTigia;
    TextView txtdate;
    ArrayList<Tygia> dstygia;
    MyArrayAdapter myadapter;
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

        lvTigia = (ListView) findViewById(R.id.listView1);
        txtdate = (TextView) findViewById(R.id.txtdate);
        getdate();
        dstygia = new ArrayList<Tygia>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview,dstygia);
        lvTigia.setAdapter(myadapter);
        TyGiaTask task = new TyGiaTask();
        task.execute();
    }
    public void getdate() {
        //Lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo định dạng dd/mm/yyyy
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        //Hiển thị lên TextView
        txtdate.setText("Hôm Nay: "+simpleDate.format(currentDate));
    }
    class TyGiaTask extends AsyncTask<Void, Void, ArrayList<Tygia>>
    {
        @Override
        protected ArrayList<Tygia> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<Tygia> ds = new ArrayList<Tygia>();
            try {
                //Đây là link Server
                URL url=new URL("https://dongabank.com.vn/exchange/export");
                //Mở Connection ra
                HttpURLConnection connection= (HttpURLConnection)
                        url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept", "*/*");
                //lấy dữ liệu mà server trả về
                // Lây chuỗi dữ liệu InputStream trả về
                InputStream is= connection.getInputStream();
                //Chuyển kiểu về kiểu UTF-8 và Đưa vào bộ đọc dữ liệu
                InputStreamReader isr=new InputStreamReader(is,"UTF-8");
                //Lưu vào bộ đệm
                BufferedReader br=new BufferedReader(isr);
                String line=br.readLine();
                StringBuilder builder=new StringBuilder();
                while (line!=null)
                {
                    builder.append(line);
                    line=br.readLine();
                }
                Log.e("SERVER_RESPONSE", builder.toString());
                String json = "{ \"items\": [" +
                        "{ \"type\": \"USD\", \"imageurl\": \"https://example.com/usd.png\", \"muatienmat\": \"23000\", \"muack\": \"23100\", \"bantienmat\": \"23500\", \"banck\": \"23600\" }," +
                        "{ \"type\": \"EUR\", \"imageurl\": \"https://example.com/eur.png\", \"muatienmat\": \"25000\", \"muack\": \"25100\", \"bantienmat\": \"25500\", \"banck\": \"25600\" }" +
                        "]}";
                //Bỏ hai ngoặc tròn trong dữ liệu trả về
                json=json.replace("(", "");
                json=json.replace(")","");
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray= jsonObject.getJSONArray("items");
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject item=jsonArray.getJSONObject(i);
                    Tygia tiGia=new Tygia();
                    tiGia.setType(item.getString("type"));
                    if(item.has("imageurl")) {
                        tiGia.setImageurl(item.getString("imageurl"));
                        url=new URL(tiGia.getImageurl());
                        connection= (HttpURLConnection)
                                url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("UserAgent","Mozilla/5.0 ( compatible ) ");
                        connection.setRequestProperty("Accept", "*/*");
                        Bitmap bitmap =
                                BitmapFactory.decodeStream(connection.getInputStream());
                        tiGia.setBitmap(bitmap);
                    }
                    if(item.has("muatienmat")) {
                        tiGia.setMuatienmat(item.getString("muatienmat"));
                    }
                    if(item.has("muack")) {
                        tiGia.setMuack(item.getString("muack"));
                    }
                    if(item.has("bantienmat")) {
                        tiGia.setBantuenmat(item.getString("bantienmat"));
                    }
                    if(item.has("banck")) {
                        tiGia.setBanck(item.getString("banck"));
                    }
                    ds.add(tiGia);
                }
                Log.d("JSON_DONGA",json);
            } catch (Exception ex) {
                // TODO: handle exception
                Log.e("Lỗi", ex.toString());
            }
            return ds;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            myadapter.clear();
        }
        @Override
        protected void onPostExecute(ArrayList<Tygia> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }
    }

}