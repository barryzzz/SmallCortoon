package xi.lsl.code.lib.utils.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Describe: activity controller
 * Created by lishoulin on 2016/12/25.
 * Email:408930131@qq.com
 */

public class ActivityController {
    private static Stack<Activity> sActivityStack;
    private static ActivityController instance;

    private ActivityController getInstance() {
        if (instance == null) {
            instance = new ActivityController();
        }
        return instance;
    }

    public static void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        if (activity != null) {
            sActivityStack.add(activity);
        }
    }


    public static void removeActivity() {
        Activity activity = sActivityStack.lastElement();
        removeActivity(activity);
    }

    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            sActivityStack.remove(activity);
            activity = null;
        }
    }

    public static void finshActivity(Class<?> cls) {
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(cls)) {
                removeActivity(activity);
            }
        }
    }

    public static void finshAll() {
        for (int i = 0; i < sActivityStack.size(); i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }

    public static void Exit() {
        try {
            finshAll();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
