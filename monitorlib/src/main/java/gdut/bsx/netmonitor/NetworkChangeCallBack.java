package gdut.bsx.netmonitor;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;


/**
 * NetworkChangeCallBack
 *
 * @author baishixian
 * @date 2018/8/15 15:20
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkChangeCallBack {

    private static MonitorCallback mMonitorCallback;

    private static long lastNotifyTimeMillis;

    private static final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastNotifyTimeMillis <= 3000) {
                Log.d(Constants.TAG, "NetworkChangeCallBack ignore notify");
                return;
            }

            lastNotifyTimeMillis = currentTimeMillis;
            super.onAvailable(network);
            Log.d(Constants.TAG, "NetworkChangeCallBack onAvailable");

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (mMonitorCallback != null) {
                        mMonitorCallback.notifyChanged(MonitorCallback.NetworkState.NET_CONNECTED);
                    }
                }
            });
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            Log.d(Constants.TAG, "NetworkChangeCallBack onLost");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (mMonitorCallback != null) {
                        mMonitorCallback.notifyChanged(MonitorCallback.NetworkState.NET_DISCONNECTED);
                    }
                }
            });

        }
    };

    public static void register(@NonNull Context context, @NonNull MonitorCallback callback) {
        mMonitorCallback = callback;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), mNetworkCallback);
        }
    }

    public static void unregister(@NonNull Context context) {
        mMonitorCallback = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(mNetworkCallback);
        }
    }

    public static void checkNetworkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(mNetworkCallback);
            connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), mNetworkCallback);
        }
    }
}
