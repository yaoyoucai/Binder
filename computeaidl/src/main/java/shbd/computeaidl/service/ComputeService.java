package shbd.computeaidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import shbd.computeaidl.activity.ComputeInterface;

/**
 * 项目名称：Binder
 * 类描述：
 * 创建人：yh
 * 创建时间：2017/3/21 9:23
 * 修改人：yh
 * 修改时间：2017/3/21 9:23
 * 修改备注：
 */
public class ComputeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    Binder binder = new ComputeInterface.Stub() {
        @Override
        public String strcat(String x, String y) throws RemoteException {
            return x + y;
        }
    };

}
