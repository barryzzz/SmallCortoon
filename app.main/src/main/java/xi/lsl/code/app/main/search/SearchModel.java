package xi.lsl.code.app.main.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.BookEntity;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * 搜索记录action
 * Created by lishoulin on 2017/3/12.
 */

public class SearchModel {
    private Context mContext;
    private static final String spName = "sp_by_search";
    private static final String searchBrowerName = "searchBrowerName";
    private static final String name = "search_save";


    public SearchModel(Context context) {
        mContext = context;
    }


    /**
     * 搜索books
     *
     * @param title
     * @return
     */
    public Observable<BookEntity> search(String title) {
        Map<String, String> map = new HashMap<>();
        map.put("Title", title);
        return Nets.getShuHuiApis().getBooks(map).compose(RxSchedulers.<BookEntity>io_main());
    }


    /**
     * 保存搜索记录
     *
     * @param str
     */
    public void setSearch(String str) {
        if (!TextUtils.isEmpty(str)) {
            SharedPreferences mSharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_APPEND);
            if (!isCon(str)) {
                str += "&";
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(name, str);
                editor.apply();
            }
        }
    }

    //TODO 数据存储方案存在歧义
    public void setLocalSearchBrowse(Book book) {
        if (book != null) {
            //使用数据库？？？
        }
    }

    //TODO
    public List<Book> getLocalSearchBrowse() {

        return null;
    }

    /**
     * 获取搜索记录
     *
     * @return
     */
    public String[] getSearch() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_APPEND);
        String str = mSharedPreferences.getString(name, "");
        if (!str.equals(""))
            str = str.substring(0, str.length() - 1);
        return str.split("&");
    }

    /**
     * 清除搜索记录
     */
    public void clearSearch() {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_APPEND);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(name).apply();
    }

    /**
     * 判断是否已经存在关键字
     *
     * @param str
     * @return
     */
    private boolean isCon(String str) {
        String[] temp = getSearch();
        boolean is = false;
        for (String s : temp) {
            if (str.equals(s)) {
                is = true;
                break;
            }
        }
        return is;
    }


}
