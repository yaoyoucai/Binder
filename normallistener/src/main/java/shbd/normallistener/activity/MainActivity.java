package shbd.normallistener.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import shbd.normallistener.IListener;
import shbd.normallistener.ITimer;
import shbd.normallistener.R;
import shbd.normallistener.service.TimeService;

public class MainActivity extends AppCompatActivity {
    private ITimer mTimer;

    private boolean isBinder;

    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
    }

    private void bind() {
        Intent intent = new Intent(this, TimeService.class);
        isBinder = bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    public void start(View view) {
        try {
            mTimer.addListener(mListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cancel(View view) {
        try {
            mTimer.cancel(mListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBinder) {
            unbindService(serviceConnection);
        }

    }

    public IListener mListener = new IListener.Stub() {
        @Override
        public void callback(final int num) throws RemoteException {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "" + num, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mTimer = ITimer.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
