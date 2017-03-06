package xi.lsl.code.lib.utils.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.lib.utils.utils.ActivityController;

/**
 * activity基类
 * Created by lishoulin on 2017/2/8.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected View statusBarView;
    protected CompositeSubscription mSubscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mSubscription = new CompositeSubscription();

        ActivityController.addActivity(this);
    }

    protected void gotoActivity(Class<?> aClass, boolean isFinsh) {
        Intent intent = new Intent(mContext, aClass);
        startActivity(intent);
        if (isFinsh) {
            finish();
        }
    }

    public void gotoActivity(Class aClass, Map<String, String> map) {
        Intent intent = new Intent(mContext, aClass);

        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        startActivity(intent);
    }

    /**
     * 状态栏切换
     * false状态是从全屏--->不是全屏
     * true状态是从不是全屏---->全屏
     */
    protected void switchStatusBar(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    protected Map<String, String> createMap() {
        return new HashMap<>();
    }


    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
        mSubscription.clear();
    }


}
