package xi.lsl.code.app.com.comment;

import rx.subscriptions.CompositeSubscription;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/6.
 */

public class CommentPresenter implements CommentContract.Presenter {

    private CommentModel mCommentModel;
    private CommentContract.View mView;
    private CompositeSubscription mSubscription;

    public CommentPresenter(CommentModel commentModel, CommentContract.View view) {
        mCommentModel = commentModel;
        mView = view;
    }

    @Override
    public void loadComment(int bookid) {

    }

    @Override
    public void sendComment() {

    }

    @Override
    public void subscribe() {
        mSubscription=new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();;
        mSubscription=null;
    }
}
