package shbd.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import shbd.test.inter.Stub;

/**
 * 项目名称：Binder
 * 类描述：
 * 创建人：yh
 * 创建时间：2017/3/21 16:02
 * 修改人：yh
 * 修改时间：2017/3/21 16:02
 * 修改备注：
 */
public class ComputeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public IBinder binder = new Stub() {
        @Override
        public String transact(String x, String y) {
            return x + y;
        }
    };
}
