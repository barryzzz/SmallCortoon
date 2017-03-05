package xi.lsl.code.app.main.home.homechild;

import java.util.List;

import xi.lsl.code.app.main.base.BasePresenter;
import xi.lsl.code.app.main.base.BaseView;
import xi.lsl.code.lib.utils.entity.Book;


/**
 * Created by lishoulin on 2017/2/14.
 */

public interface BookContract {

    interface View extends BaseView<Presenter> {
        boolean isActive();

        void showBooks(List<Book> books);

        void faild(String msg);

        void showNoBooks();

        void showSub(boolean msg);

    }


    interface Presenter extends BasePresenter {
        /**
         * 加载订阅的
         */
        void loadSubBook();

        /**
         * 加载一周热门的
         *
         * @param page 页码
         */
        void loadWeekBook(int page);

        /**
         * 订阅或者取消订阅
         *
         * @param bookId
         * @param isSub
         */
        void subBook(int bookId, boolean isSub);


    }
}
