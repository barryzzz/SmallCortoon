package xi.lsl.code.app.main.home.homechild;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.BookEntity;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/24.
 */

public class SubBookPresenter implements SubBookContract.Presenter {

    private SubBookContract.View view;

    private SubBookModel subBookModel;

    private CompositeSubscription compositeSubscription;

    public SubBookPresenter(SubBookModel subBookModel, SubBookContract.View view) {
        this.view = view;
        this.subBookModel = subBookModel;
    }

    @Override
    public void loadSubBooks() {
        compositeSubscription.add(subBookModel.getSubBooks().subscribe(new Action1<BookEntity>() {
            @Override
            public void call(BookEntity bookEntity) {
                if (bookEntity != null && bookEntity.getReturn().getList() != null && bookEntity.getReturn().getList().size() > 0) {
                    view.showBooks(bookEntity.getReturn().getList());
                } else {
                    view.showNoBooks();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                view.faild(throwable.getMessage());
                view.dissloading();
            }
        }, new Action0() {
            @Override
            public void call() {
                view.dissloading();
            }
        }));
    }

    @Override
    public void subBook(int bookId, boolean isSub) {

    }

    @Override
    public void subscribe() {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        compositeSubscription.clear();
    }
}
