package gdut.bsx.netmonitor;


import android.support.annotation.MainThread;

/**
 * NetChangeObserver 网络改变的观察者
 *
 * @author baishixian
 * @date 2018/8/14 17:30
 */
public interface NetChangeObserver {
    /**
     * 网络连接
     */
    @MainThread
    void onNetConnected();

    /**
     * 网络断开
     */
    @MainThread
    void onNetDisConnect();
}
