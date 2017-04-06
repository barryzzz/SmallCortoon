package xi.lsl.code.app.main.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.Result;
import xi.lsl.code.lib.utils.entity.SubEntity;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * //获取本周更新/订阅/最新推荐/搜索/获取分类记录 业务
 * Created by lishoulin on 2017/2/14.
 */

public class BookModel {


    /**
     * 获取books
     *
     * @param map
     * @return
     */
    public Observable<Result<Book>> getBook(Map<String, String> map) {
        return Nets.getShuHuiApis().getBooks(map).compose(RxSchedulers.<Result<Book>>io_main());
    }

    /**
     * 获取本周更新
     *
     * @param days
     * @param Subscribe
     * @param page
     * @return
     */
    public Observable<Result<Book>> getWeekBook(String days, String Subscribe, int page) {
        final List<Book> bookList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Days", days);
        map.put("Subscribe", Subscribe);
        map.put("Size", String.valueOf(10));
        map.put("PageIndex", String.valueOf(page));
        return getBook(map);
    }

    /**
     * 获取订阅的book list
     */
    public Observable<Result<Book>> getSubBook() {
        Map<String, String> map = new HashMap<>();
        map.put("Subscribe", "\"0\"");

        return getBook(map).compose(RxSchedulers.<Result<Book>>io_main());
    }


    /**
     * 订阅或者取消订阅
     *
     * @param bookId
     * @param isSub
     * @return
     */
    public Observable<SubEntity> subBook(int bookId, boolean isSub) {
        Map<String, String> map = new HashMap<>();
        map.put("bookid", String.valueOf(bookId));
        map.put("isSubscribe", String.valueOf(isSub));
//        @Query("BookId") int bookid, @Query("isSubscribe") boolean isSubscribe
        return Nets.getShuHuiApis().subBook(map).compose(RxSchedulers.<SubEntity>io_main());
    }


}
