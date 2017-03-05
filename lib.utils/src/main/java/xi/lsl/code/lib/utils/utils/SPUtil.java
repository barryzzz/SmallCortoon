package xi.lsl.code.lib.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import xi.lsl.code.lib.utils.App;


/**
 * Created by lishoulin on 2017/2/8.
 */

public class SPUtil {
    private static final Context CONTEXT = App.getContext();
    private static final Gson GSON = new Gson();
    private static final String SP_NAME = App.class.getSimpleName();
    private static final String SETTING_NAME = "cartoon_setting_by_Barry";

    private SPUtil() {
        throw new UnsupportedOperationException("禁止实例化该类！");
    }

    /**
     * save setting about app
     *
     * @param key
     * @param type
     */
    public static void saveSetting(String key, int type) {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
        preferences.edit().putInt(key, type).apply();
    }

    /**
     * get setting about app
     *
     * @param key
     * @return
     */
    public static int getSetting(String key) {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    /**
     * save cache
     *
     * @param name key
     * @param obj  key value
     */
    public static void saveObject(String name, Object obj) {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = GSON.toJson(obj);
        editor.putString(name, json);
        editor.apply();
    }

    /**
     * get something cache by key
     *
     * @param name   key
     * @param aClass class
     * @param <T>    object
     * @return value
     */
    public static <T> T getObject(String name, Class<T> aClass) {
        return getObject(name, aClass, null);
    }

    public static <T> T getObject(String name, Class<T> aClass, T defaultValue) {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String json = preferences.getString(name, "");
        if (!"".equals(json)) {
            try {
                T t = GSON.fromJson(json, aClass);
                return t;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return defaultValue;
    }

    /**
     * clear cache by name
     *
     * @param name
     */
    public static void delCache(String name) {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        preferences.edit().remove(name).apply();
    }

    /**
     * clear all cache
     */
    public static void clearAll() {
        SharedPreferences preferences = CONTEXT.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }


}
