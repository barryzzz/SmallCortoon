package xi.lsl.code.app.main.read;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.entity.BmobReponse;
import xi.lsl.code.lib.utils.entity.Chapter;
import xi.lsl.code.lib.utils.entity.Result;
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
    public void loadChapter(final String bookid, int PageIndex) {
        mView.showloading();
        mReadModel.queryChapterLists(bookid, String.valueOf(PageIndex)).subscribe(new Action1<Result<Chapter>>() {

            @Override
            public void call(Result<Chapter> bookList) {

                Gson gson = new Gson();
                JsonArray jsonArray = gson.toJsonTree(bookList.getReturn().getT()).getAsJsonArray();
                List<Chapter> chapters = gson.fromJson(jsonArray.toString(), new TypeToken<List<Chapter>>() {
                }.getType());
                mView.showChapterList(chapters);
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
    public void setChapterId(String id) {
        String url = CommonApis.URL_IMG_CHAPTER + id;
        mView.showChapter(url);
    }

    @Override
    public void saveBookAndChapter(String bookId, String ChapterId, String BookName, String ChapterName) {
        mSubscription.add(mReadModel.insertBook(bookId, ChapterId, BookName, ChapterName).subscribe(new Action1<BmobReponse>() {
            @Override
            public void call(BmobReponse bmobReponse) {
                Log.e("info--》", "code" + bmobReponse.code);
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
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null)
            mSubscription.clear();
        mSubscription = null;
    }
}
