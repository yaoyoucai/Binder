// ITimer.aidl
package shbd.normallistener;
import shbd.normallistener.IListener;
// Declare any non-default types here with import statements

interface ITimer {
    void addListener(IListener listener);

    void cancel(IListener listener);
}
