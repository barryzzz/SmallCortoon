package xi.lsl.code.app.main.read;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.BookList;
import xi.lsl.code.lib.utils.net.CommonApis;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/13.
 */

public class ReadPresenter implements ReadContract.Presenter {
    private ReadModel mReadModel;
    private CompositeSubscription mSubscription;
    private ReadContract.View mView;

    public ReadPresenter(ReadModel readModel, ReadContract.View view) {
        mReadModel = readModel;
        mView = view;
    }

    @Override
    public void getBookLists(String bookid, int PageIndex) {
        mView.showloading();
        mReadModel.queryBookLists(bookid, String.valueOf(PageIndex)).subscribe(new Action1<BookList>() {
            @Override
            public void call(BookList bookList) {
                mView.showBookList(bookList.getReturn().getList());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.dissloading();
            }
        }, new Action0() {
            @Override
            public void call() {
                mView.dissloading();
            }
        });
    }

    @Override
    public void setBookId(String id) {
        String url = CommonApis.URL_IMG_CHAPTER + id;
        mView.showBook(url);
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
