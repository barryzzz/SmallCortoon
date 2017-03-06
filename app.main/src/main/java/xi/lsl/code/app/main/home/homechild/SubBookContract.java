package xi.lsl.code.app.main.home.homechild;

import java.util.List;

import xi.lsl.code.lib.utils.base.BasePresenter;
import xi.lsl.code.lib.utils.base.BaseView;
import xi.lsl.code.lib.utils.entity.Book;


/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/24.
 */

public interface SubBookContract {

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
        void loadSubBooks();

        /**
         * 订阅或者取消订阅
         *
         * @param bookId
         * @param isSub
         */
        void subBook(int bookId, boolean isSub);

    }


}
