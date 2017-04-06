package xi.lsl.code.app.main.book;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.Result;
import xi.lsl.code.lib.utils.net.Nets;
import xi.lsl.code.lib.utils.net.RxSchedulers;

/**
 * book model
 * Created by lishoulin on 2017/3/20.
 */

public class BookFlModel {

    /**
     * 获取某一分类30条记录
     * ClassifyId分类标识，0热血，1国产，2同人，3鼠绘
     * Size每次获取的消息条数，最大值为30
     * PageIndex获取第几页的数据
     */
    Observable<Result<Book>> getCategoryData(String ClassifyId, String Size, String PageIndex) {
        Map<String, String> map = createMap();
        map.put("ClassifyId", ClassifyId);
        map.put("Size", Size);
        map.put("PageIndex", PageIndex);
        return Nets.getShuHuiApis().getBooks(map).compose(RxSchedulers.<Result<Book>>io_main());
    }

    private Map<String, String> createMap() {
        return new HashMap<>();
    }

}
