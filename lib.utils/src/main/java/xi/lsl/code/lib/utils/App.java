package xi.lsl.code.lib.utils;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

import xi.lsl.code.lib.utils.entity.User;


/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/8.
 */

public class App extends Application {

    private static Context mContext;
    public static User userInfo;
    private final String TAG = "xi.lsl.info--->";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Logger.init(TAG);
    }

    public static Context getContext() {

        return mContext;
    }


}
