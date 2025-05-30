package com.example.demo_arsyntask;

import android.adservices.ondevicepersonalization.FederatedComputeScheduler;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class MyArsynTask extends AsyncTask<Void,Integer,Void> {
   Activity contextcha;

   public  MyArsynTask(Activity ctx){
       contextcha = ctx;
   }

    @Override
    protected Void doInBackground(Void... params) {
        for (int  i = 0;i<=100;i++){
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return  null;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextcha,"onPreExcute!",Toast.LENGTH_SHORT).show();

   }
    @Override
    protected void onProgressUpdate(Integer... values) {

        super.onProgressUpdate(values);
        ProgressBar paCha=(ProgressBar)
                contextcha.findViewById(R.id.progressBar);
        int giatri=values[0];
        paCha.setProgress(giatri);
        TextView
                txtmsg=(TextView)contextcha.findViewById(R.id.textView);
        txtmsg.setText(giatri+"%");
    }

    @Override
    protected void onPostExecute(Void Result) {
        super.onPostExecute(Result);
        Toast.makeText(contextcha, "Update xong roi do!",
                Toast.LENGTH_LONG).show();
    }

}
