package xi.lsl.code.lib.utils.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/10.
 */

public class ActivityUtils {


    public static void addFragmentToActivity(@NonNull FragmentManager manager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(frameId, fragment);
        transaction.commit();
    }


    public static void removeFragmentFromActivity() {

    }
}
