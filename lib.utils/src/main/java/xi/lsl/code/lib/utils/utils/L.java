package xi.lsl.code.lib.utils.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Description: 日志输出
 * Author   :lishoulin
 * Date     :2017/3/28.
 */

public class L {
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;


    private L() {
        throw new RuntimeException("L is can't init!");
    }

    /**
     * @param msg log
     */
    public static void v(String msg) {
        print_msg(VERBOSE, null, msg);
    }

    /**
     * @param tag tag
     * @param msg log
     */
    public static void v(String tag, String msg) {
        print_msg(VERBOSE, tag, msg);

    }

    /**
     * @param msg log
     */
    public static void d(String msg) {
        print_msg(DEBUG, null, msg);

    }

    /**
     * @param tag tag
     * @param msg log
     */
    public static void d(String tag, String msg) {
        print_msg(DEBUG, tag, msg);

    }

    /**
     * @param msg log
     */
    public static void i(String msg) {
        print_msg(INFO, null, msg);

    }

    /**
     * @param tag tag
     * @param msg log
     */
    public static void i(String tag, String msg) {
        print_msg(INFO, tag, msg);

    }

    /**
     * @param msg log
     */
    public static void w(String msg) {
        print_msg(WARN, null, msg);

    }

    /**
     * @param tag tag
     * @param msg log
     */
    public static void w(String tag, String msg) {
        print_msg(WARN, tag, msg);

    }

    public static void e(String msg) {
        print_msg(ERROR, null, msg);

    }

    /**
     * @param tag tag
     * @param msg log
     */
    public static void e(String tag, String msg) {
        print_msg(ERROR, tag, msg);
    }

    /**
     * @param json json
     */
    public static void Ljson(String json) {
        Logger.json(json);
    }

    /**
     * @param type log type
     * @param tag  tag
     * @param msg  log
     */
    private static void print_msg(int type, String tag, String msg) {
        switch (type) {
            case VERBOSE:
                if (tag != null)
                    Log.v(tag, msg);
                else
                    Logger.v(msg);
                break;
            case DEBUG:
                if (tag != null)
                    Log.d(tag, msg);
                else
                    Logger.d(msg);
                break;
            case INFO:
                if (tag != null)
                    Log.i(tag, msg);
                else
                    Logger.i(msg);
                break;
            case WARN:
                if (tag != null)
                    Log.w(tag, msg);
                else
                    Logger.w(msg);
                break;
            case ERROR:
                if (tag != null)
                    Log.e(tag, msg);
                else
                    Logger.e(msg);
                break;

        }
    }

}
