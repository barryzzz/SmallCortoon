package xi.lsl.code.app.main.book;

import java.util.List;

import xi.lsl.code.lib.utils.base.BasePresenter;
import xi.lsl.code.lib.utils.base.BaseView;
import xi.lsl.code.lib.utils.entity.Book;

/**
 * Created by lishoulin on 2017/3/20.
 */

public interface BookFlContract {

    interface View extends BaseView<Presenter> {

        void showBooks(List<Book> books);

        void showFaild(String msg);
    }

    interface Presenter extends BasePresenter {
        void getCategoryData(String ClassifyId, String Size, String PageIndex);
    }
}
