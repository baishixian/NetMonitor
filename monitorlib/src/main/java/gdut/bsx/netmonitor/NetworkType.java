package gdut.bsx.netmonitor;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * NetworkType 网络类型
 *
 * @author baishixian
 * @date 2018/8/15 15:03
 */
@StringDef({NetworkType.NONE, NetworkType.WIFI, NetworkType.MOBILE_4G, NetworkType.MOBILE_3G, NetworkType.MOBILE_2G})
@Retention(RetentionPolicy.SOURCE)
public @interface NetworkType {
    String NONE = "None";
    String WIFI = "Wi-Fi";
    String MOBILE_4G = "4G";
    String MOBILE_3G = "3G";
    String MOBILE_2G = "2G";
    String UNKNOWN = "Unknown";
}
