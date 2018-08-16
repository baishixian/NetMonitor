package gdut.bsx.netmonitor;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * MonitorCallback
 *
 * @author baishixian
 * @date 2018/8/15 14:56
 */
interface MonitorCallback {

    /**
     * 网络连接状态
     */
    @IntDef({NetworkState.NET_CONNECTED, NetworkState.NET_DISCONNECTED})
    @Retention(RetentionPolicy.SOURCE)
    @interface NetworkState {
        int NET_CONNECTED = 0;
        int NET_DISCONNECTED = 1;
    }

    /**
     * 通知网络改变
     * @param netState
     */
    void notifyChanged(@NetworkState int netState);
}
