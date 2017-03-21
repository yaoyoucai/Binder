package shbd.computeaidl.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import shbd.computeaidl.service.ComputeService;

public class MainActivity extends AppCompatActivity {
    private TextView mTvResult;

    private ComputeInterface computeInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        bind();
    }

    public void start(View view) {
        String x = "qwert";
        String y = "asdfg";
        try {
            String strcat = computeInterface.strcat(x, y);
            mTvResult.setText(strcat);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        Intent service = new Intent(this, ComputeService.class);
        bindService(service, serviceConnection, Service.BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            computeInterface = ComputeInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
