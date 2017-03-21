package shbd.test.inter;


import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * 项目名称：Binder
 * 类描述：
 * 创建人：yh
 * 创建时间：2017/3/21 16:30
 * 修改人：yh
 * 修改时间：2017/3/21 16:30
 * 修改备注：
 */
public class Proxy implements ComputeInterface {
    private IBinder mRemote;

    public Proxy(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    public IBinder asInterface() {
        return mRemote;
    }

    @Override
    public String transact(String x, String y) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeString(x);
        data.writeString(y);
        try {
            mRemote.transact(1, data, reply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return reply.readString();
    }
}
