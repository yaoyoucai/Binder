package com.example.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.service.ComputeService;

public class MainActivity extends AppCompatActivity {
    private TextView mTvResult;

    private IBinder mRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        bind();
    }

    public void start(View view) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        data.writeString("aaaaa");
        data.writeString("bbbbbb");
        try {
            mRemote.transact(1, data, reply, 0);
            mTvResult.setText(reply.readString());
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    public void bind() {
        Intent service = new Intent(this, ComputeService.class);
        bindService(service, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemote = iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
