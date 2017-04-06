package xi.lsl.code.app.main.home;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.Result;
import xi.lsl.code.lib.utils.entity.SubEntity;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/24.
 */

public class SubBookModel {
    /**
     * 获取订阅的书本
     *
     * @return
     */
    public Observable<Result<Book>> getSubBooks() {
        return Nets.getShuHuiApis().getSubBooks().compose(RxSchedulers.<Result<Book>>io_main());
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
