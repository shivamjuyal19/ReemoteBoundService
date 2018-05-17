package com.example.pc77.reemotebound;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int MSG_SAY_HELLO =1 ;
    Messenger mn;

    ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mn = new Messenger(iBinder);
            Message msg = Message.obtain(null, MyService.MSG_SAY_HELLO, 9, 6);
            try {
                mn.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            msg.replyTo=new Messenger(new ReplyHandler());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mn=null;

        }
    };
    public class ReplyHandler extends Handler {
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case MainActivity.MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), msg.arg1+msg.arg2+"", Toast.LENGTH_SHORT).show();

                default: super.handleMessage(msg);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i=new Intent(this,MyService.class);
        bindService(i,sc, Context.BIND_AUTO_CREATE);

        }


}
