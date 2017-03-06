package xi.lsl.code.app.com.comment;

import xi.lsl.code.lib.utils.base.BasePresenter;
import xi.lsl.code.lib.utils.base.BaseView;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public interface CommentContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * 读取评论
         */
        void loadComment();

        /**
         * 进行评论
         */
        void sendComment();
    }
}
