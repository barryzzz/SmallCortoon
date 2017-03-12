package xi.lsl.code.app.main.search;

import java.util.List;

import xi.lsl.code.lib.utils.base.BasePresenter;
import xi.lsl.code.lib.utils.base.BaseView;
import xi.lsl.code.lib.utils.entity.Book;

/**
 * Created by lishoulin on 2017/3/12.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {
        /**
         * 获取搜索记录
         *
         * @param str
         */
        void showLocalSearch(String[] str);

        /**
         * 获取搜索内容
         *
         * @param books
         */
        void showSearchBooks(List<Book> books);


        /**
         * 错误信息抛出
         *
         * @param msg
         */
        void faild(String msg);

    }

    interface Presenter extends BasePresenter {
        /**
         * 搜索
         *
         * @param str
         */
        void search(String str);

        /**
         * 清除搜索
         */
        void clearSearch();

        /**
         * 获取最近搜索记录
         */
        void getLocalSearch();


        /**
         * 保存最近浏览
         *
         * @param book
         */
        void setLocalSearchBrowse(Book book);

        /**
         * 获取最近浏览
         *
         * @return
         */
        List<Book> getLocalSearchBrowse();

    }

}
