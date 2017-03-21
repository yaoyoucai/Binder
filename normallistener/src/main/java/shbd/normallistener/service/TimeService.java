package shbd.normallistener.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shbd.normallistener.IListener;
import shbd.normallistener.ITimer;

/**
 * 项目名称：Binder
 * 类描述：
 * 创建人：yh
 * 创建时间：2017/3/21 14:01
 * 修改人：yh
 * 修改时间：2017/3/21 14:01
 * 修改备注：
 */
public class TimeService extends Service {
    private List<IListener> listeners = new ArrayList<>();

    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            private int num = 0;

            @Override
            public void run() {
                num++;
                for (IListener listener : listeners) {
                    try {
                        listener.callback(num);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 2000);
    }

    IBinder binder = new ITimer.Stub() {
        @Override
        public void addListener(IListener listener) throws RemoteException {
            listeners.add(listener);
        }

        @Override
        public void cancel(IListener listener) throws RemoteException {
            listeners.remove(listener);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
