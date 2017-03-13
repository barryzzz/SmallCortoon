package xi.lsl.code.app.main.read;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import xi.lsl.code.lib.utils.entity.BmobBook;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.BookList;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * Created by lishoulin on 2017/3/7.
 */

public class ReadModel {
    private Gson mGson;

    public ReadModel() {
        this.mGson = new Gson();
    }

    /**
     * 存储书本信息到bmob上面
     *
     * @param bookid
     * @param bookname
     * @return
     */
    public Observable<BmobReponse> insertBook(String bookid, String bookname) {
        Map<String, String> map = new HashMap<>();
        map.put("book_id", bookid);
        map.put("book_name", bookname);
        return Nets.getBmobApis().InsertBooks(createBody(map)).compose(RxSchedulers.<BmobReponse>io_main());
    }

    /**
     * @param bookid
     * @return
     */
    public Observable<BmobBook> queryBook(int bookid) {
        Map<String, String> map = createMap();
        map.put("book_id", String.valueOf(bookid));
        String json = mGson.toJson(map);
        return Nets.getBmobApis().QueryBooks(json).compose(RxSchedulers.<BmobBook>io_main());
    }

    /**
     * 获取集数
     *
     * @param bookid    书本id
     * @param PageIndex 页码
     * @return 书本集数信息
     */
    public Observable<BookList> queryBookLists(String bookid, String PageIndex) {
        return Nets.getShuHuiApis().getBookLists(bookid, PageIndex).compose(RxSchedulers.<BookList>io_main());
    }


    private RequestBody createBody(Map<String, String> map) {
        if (mGson == null) {
            throw new RuntimeException("gson is null");
        }
        String json = mGson.toJson(map);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }


    private Map<String, String> createMap() {
        return new HashMap<>();
    }

}
