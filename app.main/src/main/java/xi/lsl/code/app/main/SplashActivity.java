package xi.lsl.code.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xi.lsl.code.app.main.main.MainActivity;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.utils.SPUtil;
import xi.lsl.code.lib.utils.utils.Setting;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/13.
 */

public class SplashActivity extends BaseActivity {
    @InjectView(R.id.splash_img)
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        iniNet();

        int code = SPUtil.getSetting(Setting.IS_LOGIN);
        if (code == Setting.CODE_0) {
            gotoActivity(LoginActivity.class, true);
        } else if (code == Setting.CODE_1) {
            gotoActivity(MainActivity.class, true);
        }
    }


    private void iniNet() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://openapi.kymjs.com/splash").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {


                } else {

                }

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
