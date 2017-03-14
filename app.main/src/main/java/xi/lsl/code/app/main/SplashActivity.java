package xi.lsl.code.app.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.net.RxSchedulers;
import xi.lsl.code.lib.utils.utils.Imageloader;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/13.
 */

public class SplashActivity extends BaseActivity {
    @InjectView(R.id.splash_img)
    ImageView mImageView;
    @InjectView(R.id.splash_tv)
    TextView mTextView;
    @InjectView(R.id.splash_bt)
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        initSplash();

//        int code = SPUtil.getSetting(Setting.IS_LOGIN);
//        if (code == Setting.CODE_0) {
//            gotoActivity(LoginActivity.class, true);
//        } else if (code == Setting.CODE_1) {
//            gotoActivity(MainActivity.class, true);
//        }
    }


    private void initSplash() {


        Observable<Splash> observable = Observable.create(new Observable.OnSubscribe<Splash>() {
            @Override
            public void call(Subscriber<? super Splash> subscriber) {

                if (!subscriber.isUnsubscribed()) {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("http://openapi.kymjs.com/splash").build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        String json = response.body().string();
                        Gson gson = new Gson();
                        ArrayList<JsonObject> objects = gson.fromJson(json, new TypeToken<ArrayList<JsonObject>>() {
                        }.getType());
                        Splash splash = gson.fromJson(objects.get(0), Splash.class);
                        subscriber.onNext(splash);
                    } catch (IOException e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                    subscriber.onCompleted();
                }

            }
        }).compose(RxSchedulers.<Splash>io_main());


        observable.subscribe(new Action1<Splash>() {
            @Override
            public void call(Splash splash) {
                mTextView.setText(splash.description);
                Imageloader.display(mContext, splash.image_url, mImageView);
                gotoActivity();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });


    }


    private void gotoActivity() {
        Observable.interval(1, TimeUnit.SECONDS).take(3).compose(RxSchedulers.<Long>io_main()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {

                Log.e("info---.", aLong + "");
                Resources res = getResources();
                String text = String.format(res.getString(R.string.jump), aLong);
                mButton.setText(text);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                mButton.setText("跳转");
            }
        });

    }


    private class Splash {
        public int action_type;
        public String start_time;
        public String description;
        public String image_url;
        public String order_index;
        public String scheme;

    }


}
