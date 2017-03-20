package xi.lsl.code.app.main.search;

import java.util.List;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.BookEntity;

/**
 * searchActivity 的presenter
 * Created by lishoulin on 2017/3/12.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchModel mModel;
    private SearchContract.View mView;
    private CompositeSubscription mSubscription;

    public SearchPresenter(SearchModel model, SearchContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void search(final String str) {
        mView.showloading();
        mSubscription.add(mModel.search(str).subscribe(new Action1<BookEntity>() {
            @Override
            public void call(BookEntity bookEntity) {
                if (bookEntity != null && bookEntity.getReturn().getList() != null && bookEntity.getReturn().getList().size() > 0) {
                    //有搜索记录
                    mView.showSearchBooks(bookEntity.getReturn().getList());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mView.faild(throwable.getMessage());
                mView.dissloading();
            }
        }, new Action0() {
            @Override
            public void call() {
                mView.dissloading();
                mModel.setSearch(str);
            }
        }));
    }

    @Override
    public void clearSearch() {
        mModel.clearSearch();
        getLocalSearch();
    }

    @Override
    public void getLocalSearch() {
        String str[] = mModel.getSearch();
        mView.showLocalSearch(str);
    }

    @Override
    public void setLocalSearchBrowse(Book book) {
    }

    @Override
    public List<Book> getLocalSearchBrowse() {
        return null;
    }

    @Override
    public void subscribe() {
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null)
            mSubscription.clear();
    }
}
