package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public  void processReive(Context context,Intent intent){
        Bundle Extras = intent.getExtras();
        String Mess ="", body ="",address ="";
        if(Extras !=null){
            Object [] smsExtras = (Object[]) Extras.get("pdus");

            for (int i =0;i< smsExtras.length;i++){
                SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtras[i] );
                body =sms.getMessageBody();
                address = sms.getOriginatingAddress();
                Mess+="co mot tin nhan tu "+address+"\n" + body+"vua gui den";
            }

            Toast.makeText(context,Mess,Toast.LENGTH_SHORT).show();
        }
    }
}