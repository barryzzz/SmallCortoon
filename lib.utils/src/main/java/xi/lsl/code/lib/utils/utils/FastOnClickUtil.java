package xi.lsl.code.lib.utils.utils;

import android.os.Looper;
import android.util.Log;

/**
 * Description: 防止重复点击
 * Author   :lishoulin
 * Date     :2017/2/24.
 */

public class FastOnClickUtil {
    private static long lastClickTime;

    /**
     * @return
     */
    public synchronized static boolean fastClick800() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime > 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * @return
     */
    public synchronized static boolean fastClick100() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime > 100) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * @param spantime
     * @return
     */
    public synchronized static boolean fastClick(long spantime) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime > spantime) {
            return true;
        }
        lastClickTime = time;
//        Log.e("infoo-->", "time:" + time + "::::" + lastClickTime);
        return false;
    }
}
