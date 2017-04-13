package xi.lsl.code.app.main;

import android.app.Application;
import android.content.Context;

import xi.lsl.code.lib.utils.entity.BmobUser;


/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/8.
 */

public class App extends Application {

    private static Context mContext;
    public static BmobUser userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {

        return mContext;
    }


}
