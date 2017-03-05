package xi.lsl.code.lib.utils.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/22.
 */

public class RxSchedulers {

    public static <T> Observable.Transformer<T, T> io_main() {

        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
