package shbd.test.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import shbd.test.inter.ComputeInterface;
import shbd.test.inter.Stub;
import shbd.test.service.ComputeService;

public class MainActivity extends AppCompatActivity {

    private boolean isBinder;

    private TextView mTvResult;

    private ComputeInterface mComputeInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        bind();
    }

    public void start(View view) {
        mComputeInterface.transact("sdfdf", "ddddd");
    }

    private void bind() {
        Intent intent = new Intent(this, ComputeService.class);
        isBinder = bindService(intent, serviceConnectionn, Service.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnectionn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mComputeInterface = Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBind();
    }

    private void unBind() {
        if (isBinder) {
            unbindService(serviceConnectionn);
            isBinder = false;
        }
    }
}
