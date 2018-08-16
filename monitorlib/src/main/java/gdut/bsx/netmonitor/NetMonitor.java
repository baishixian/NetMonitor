package gdut.bsx.netmonitor;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;


/**
 * NetMonitor
 *
 * @author baishixian
 * @date 2018/8/15 14:38
 */
public class NetMonitor implements MonitorCallback {

    private final Set<NetChangeObserver> mNetChangeObservers;

    @Override
    public void notifyChanged(int netState) {
        Log.d(Constants.TAG, "NetMonitor notifyChanged netChangeObservers size: " + mNetChangeObservers.size());

        for (NetChangeObserver observer : mNetChangeObservers) {
            if (netState == NetworkState.NET_CONNECTED) {
                observer.onNetConnected();
            } else {
                observer.onNetDisConnect();
            }
        }
    }

    private static class NetMonitorHolder {
        private final static NetMonitor INSTANCE = new NetMonitor();
    }

    private NetMonitor() {
        mNetChangeObservers = new HashSet<>();
    }

    public static NetMonitor getInstance() {
        return NetMonitorHolder.INSTANCE;
    }

    /**
     * 开始监听网络变化
     * @param context
     */
    public void start(@NonNull Context context) {
        Log.d(Constants.TAG, "NetMonitor start");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d(Constants.TAG, "NetMonitor start with BroadcastReceiver.");
            NetworkChangedReceiver.registerNetworkStateReceiver(context, this);
        } else {
            Log.d(Constants.TAG, "NetMonitor start with NetworkCallback.");
            NetworkChangeCallBack.register(context, this);
        }
    }

    /**
     * 释放监听网络变化
     * @param context
     */
    public void release(@NonNull Context context) {
        Log.d(Constants.TAG, "NetMonitor release");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkChangedReceiver.unRegisterNetworkStateReceiver(context);
        } else {
            NetworkChangeCallBack.unregister(context);
        }

        removeAllObserver();
    }


    /**
     * 检查网络状态
     * @param context
     */
    public void checkNetworkState(Context context) {
        Log.d(Constants.TAG, "NetMonitor checkNetworkState");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkChangedReceiver.checkNetworkState(context);
        } else {
            NetworkChangeCallBack.checkNetworkState(context);
        }
    }

    /**
     * 注册网络状态变化订阅者
     * @param netChangeObserver
     */
    public void registerObserver(@NonNull NetChangeObserver netChangeObserver) {
        mNetChangeObservers.add(netChangeObserver);
    }

    /**
     * 移除网络状态变化订阅者
     * @param netChangeObserver
     */
    public void removeObserver(@NonNull NetChangeObserver netChangeObserver) {
        mNetChangeObservers.remove(netChangeObserver);
    }

    /**
     * 移除所有网络状态变化订阅者
     */
    public void removeAllObserver() {
        mNetChangeObservers.clear();
    }

}
