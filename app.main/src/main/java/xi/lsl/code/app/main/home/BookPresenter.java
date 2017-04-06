package xi.lsl.code.app.main.home;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.entity.Result;
import xi.lsl.code.lib.utils.entity.SubEntity;
import xi.lsl.code.lib.utils.utils.L;

/**
 * Created by lishoulin on 2017/2/14.
 */

public class BookPresenter implements BookContract.Presenter {

    private CompositeSubscription compositeSubscription;

    private BookModel bookModel;

    private BookContract.View view;


    public BookPresenter(BookModel bookModel, BookContract.View view) {
        this.bookModel = bookModel;
        this.view = view;
    }

    @Override
    public void loadSubBook() {
        view.showloading();
        compositeSubscription.add(bookModel.getSubBook().subscribe(new Action1<Result<Book>>() {
            @Override
            public void call(Result<Book> bookEntity) {

                if (bookEntity != null && bookEntity.getReturn().getT().size() > 0) {
                    Gson gson = new Gson();
                    JsonArray jsonArray = gson.toJsonTree(bookEntity.getReturn().getT()).getAsJsonArray();
                    List<Book> books = gson.fromJson(jsonArray.toString(), new TypeToken<List<Book>>() {
                    }.getType());
                    view.showBooks(books);
                    gson = null;
                } else {
                    view.showNoBooks();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                view.dissloading();
                view.faild(throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                view.dissloading();
            }
        }));

    }

    @Override
    public void loadWeekBook(int PageIndex) {
        view.showloading();
        bookModel.getWeekBook("\"7\"", "\"0\"", PageIndex).subscribe(new Action1<Result<Book>>() {
            @Override
            public void call(Result<Book> bookEntity) {
                if (bookEntity != null && bookEntity.getReturn().getT().size() > 0) {
                    if (view.isActive()) {
                        Gson gson = new Gson();
                        JsonArray jsonArray = gson.toJsonTree(bookEntity.getReturn().getT()).getAsJsonArray();
                        List<Book> books = gson.fromJson(jsonArray.toString(), new TypeToken<List<Book>>() {
                        }.getType());
                        L.d(jsonArray.toString());
                        view.showBooks(books);
                        gson = null;
                    }
                } else {
                    view.showNoBooks();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                view.dissloading();
            }
        }, new Action0() {
            @Override
            public void call() {
                view.dissloading();
            }
        });
    }

    @Override
    public void subBook(int bookId, boolean isSub) {
        compositeSubscription.add(bookModel.subBook(bookId, isSub).subscribe(new Action1<SubEntity>() {
            @Override
            public void call(SubEntity subEntity) {
                view.showSub(subEntity.isReturn());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        }));
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
