package xi.lsl.code.app.com.comment;

import java.util.List;

import xi.lsl.code.lib.utils.base.BasePresenter;
import xi.lsl.code.lib.utils.base.BaseView;
import xi.lsl.code.lib.utils.entity.BmobComment;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public interface CommentContract {

    interface View extends BaseView<Presenter> {

        void faild(String msg);

        void showComment(List<BmobComment.ResultsBean> comments);
    }

    interface Presenter extends BasePresenter {
        /**
         * 读取评论
         */
        void loadComment(String bookid);

        /**
         * 进行评论
         */
        void sendComment(String userObjid, String bookObjid, String bookId, String msg);
    }
}
