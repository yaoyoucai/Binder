package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by 25110 on 2017/3/20.
 */

public class ComputeService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Binder binder = new Binder() {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String x = data.readString();
            String y = data.readString();
            String result = strcat(x, y);
            reply.writeString(result);
            return true;
        }
    };

    public String strcat(String x, String y) {
        return x + y;
    }
}
