package xi.lsl.code.app.main.read;

import java.util.List;

import xi.lsl.code.lib.utils.base.BasePresenter;
import xi.lsl.code.lib.utils.base.BaseView;
import xi.lsl.code.lib.utils.entity.BookList;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/13.
 */

public interface ReadContract {
    interface View extends BaseView<Presenter> {
        /**
         * 获取书本集数信息
         *
         * @param data
         */
        void showBookList(List<BookList.ReturnBean.ListBean> data);

        /**
         * 查看内容
         *
         * @param url 返回内容读取的url
         */
        void showBook(String url);

        /**
         * 抛出错误信息
         *
         * @param msg
         */
        void faild(String msg);

    }

    interface Presenter extends BasePresenter {
        /**
         * 获取集数目录
         */
        void getBookLists(String bookid, int PageIndex);

        /**
         * @param id 集数
         */
        void setBookId(String id);
    }
}
