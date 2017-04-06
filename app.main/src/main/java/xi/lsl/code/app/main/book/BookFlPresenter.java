package xi.lsl.code.app.main.book;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.Result;

/**
 * book presenter
 * Created by lishoulin on 2017/3/20.
 */

public class BookFlPresenter implements BookFlContract.Presenter {
    private CompositeSubscription mSubscription;
    private BookFlContract.View mView;
    private BookFlModel mBookFlModel;

    public BookFlPresenter(BookFlContract.View view, BookFlModel bookFlModel) {
        mView = view;
        mBookFlModel = bookFlModel;
    }

    @Override
    public void getCategoryData(String ClassifyId, String Size, String PageIndex) {
        mView.showloading();
        mSubscription.add(mBookFlModel.getCategoryData(ClassifyId, "30", PageIndex).subscribe(new Action1<Result<Book>>() {
            @Override
            public void call(Result<Book> bookEntity) {
                if (bookEntity != null) {
                    mView.showBooks(bookEntity.getReturn().getT());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.showFaild(throwable.getMessage());
                mView.dissloading();
            }
        }, new Action0() {
            @Override
            public void call() {
                mView.dissloading();
            }
        }));
    }

    @Override
    public void subscribe() {
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null)
            mSubscription.clear();
        mSubscription = null;
    }
}
