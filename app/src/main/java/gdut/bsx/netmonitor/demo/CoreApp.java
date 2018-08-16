package gdut.bsx.netmonitor.demo;

import android.app.Application;
import android.util.Log;

import gdut.bsx.netmonitor.NetMonitor;

/**
 * CoreApp
 *
 * @author baishixian
 * @date 2018/8/15 16:25
 */
public class CoreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("CoreApp","onCreate");
        NetMonitor.getInstance().start(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("CoreApp","onTerminate");
        NetMonitor.getInstance().release(this);
    }

}
