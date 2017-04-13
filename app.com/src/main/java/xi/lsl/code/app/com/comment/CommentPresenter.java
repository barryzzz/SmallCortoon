package xi.lsl.code.app.com.comment;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.BmobComment;
import xi.lsl.code.lib.utils.utils.L;

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
    public void loadComment(String bookid) {
        mView.showloading();
        mSubscription.add(mCommentModel.getComment(bookid).subscribe(new Action1<BmobComment>() {
            @Override
            public void call(BmobComment comment) {
                if (comment.results != null && comment.results.size() > 0) {
                    mView.showComment(comment.results);
                } else {
                    mView.faild("加载数据异常");
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.dissloading();
                mView.faild(throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                mView.dissloading();
            }
        }));
    }

    @Override
    public void sendComment(String userObjid, String bookObjid, String bookId, String msg) {

        if (!TextUtils.isEmpty(msg)) {
            mView.showloading();
            mSubscription.add(mCommentModel.sendComment(userObjid, bookObjid, bookId, msg).subscribe(new Action1<ResponseBody>() {
                @Override
                public void call(ResponseBody responseBody) {
                    try {
                        L.d(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    mView.dissloading();
                    mView.faild(throwable.getMessage());
                }
            }, new Action0() {
                @Override
                public void call() {
                    mView.dissloading();
                }
            }));
        } else {
            mView.faild("信息不能为空!");
            mView.dissloading();
        }


    }

    @Override
    public void subscribe() {
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
        mSubscription = null;
    }
}
