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
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by pc77 on 18/4/18.
 */
public class MyService extends Service{
    static final int MSG_SAY_HELLO =1 ;
    //static final MSG_SAY_HELLO=1;
    Messenger m;
    //ServiceConnection sc;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        m=new Messenger(new MyHandler());
        return m.getBinder();
    }

    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case MSG_SAY_HELLO:
                    //Toast.makeText(MyService.this, msg.arg1+msg.arg2+"", Toast.LENGTH_SHORT).show();
                    Message ms=Message.obtain(null,MainActivity.MSG_SAY_HELLO,(msg.arg1+msg.arg2),0);
                    try {
                        msg.replyTo.send(ms);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                default: super.handleMessage(msg);
            }
        }
    }

}
