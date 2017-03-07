package xi.lsl.code.lib.utils.net;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * net work manger
 * Created by lishoulin on 2017/3/5.
 */

public class Nets {

    public static final String BASE_SHUHUI_API = "http://www.ishuhui.net";
    public static final String BASE_SHUHUI_SLIGE = "http://two.ishuhui.com/";
    public static final String BASE_BMOB_API = "https://api.bmob.cn/1/";

    private static ShuHuiApis sShuHuiApis;
    private static BmobApis sBmobApis;
    private static CommonApis sCommonApis;

    private Nets() {
        throw new RuntimeException("can not init !");
    }

    public static synchronized ShuHuiApis getShuHuiApis() {
        if (sShuHuiApis == null)
            sShuHuiApis = createRetrofit(ShuHuiApis.class);
        return sShuHuiApis;
    }

    public static synchronized BmobApis getBmobApis() {
        if (sBmobApis == null)
            sBmobApis = createRetrofit(BmobApis.class, BASE_BMOB_API, configClient(new SSLLoggerInterceptor()));
        return sBmobApis;
    }

    public static synchronized CommonApis getCommonApis() {
        if (sCommonApis == null)
            sCommonApis = createRetrofit(CommonApis.class, BASE_SHUHUI_SLIGE, configClient());
        return sCommonApis;
    }

    private static <T> T createRetrofit(Class<T> tClass, String baseUrl, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }

    private static <T> T createRetrofit(Class<T> tClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SHUHUI_API)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }


    private static OkHttpClient configClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new LoggerInterceptor())
                .cookieJar(new CookieManger())
                .build();
        return client;
    }

    private static OkHttpClient configClient(Interceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cookieJar(new CookieManger())
                .build();
        return client;
    }
}
