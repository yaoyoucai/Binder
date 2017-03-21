package shbd.test.inter;


import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * 项目名称：Binder
 * 类描述：
 * 创建人：yh
 * 创建时间：2017/3/21 16:25
 * 修改人：yh
 * 修改时间：2017/3/21 16:25
 * 修改备注：
 */
public abstract class Stub extends Binder implements ComputeInterface {
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        String x = data.readString();
        String y = data.readString();
        String result = transact(x, y);
        reply.writeString(result);
        return true;
    }


    public static ComputeInterface asInterface(IBinder binder) {
        return new Proxy(binder);
    }
}
