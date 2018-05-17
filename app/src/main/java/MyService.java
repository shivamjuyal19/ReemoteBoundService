import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by pc77 on 18/4/18.
 */

public class MyService extends Service{
    private static final int MSG_SAY_HELLO =1 ;
    //static final MSG_SAY_HELLO=1;
    Messenger m;

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
                    Toast.makeText(MyService.this, msg.arg1+msg.arg2, Toast.LENGTH_SHORT).show();
                default: super.handleMessage(msg);
            }
        }
    }

}
