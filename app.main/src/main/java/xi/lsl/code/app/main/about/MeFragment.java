package xi.lsl.code.app.main.about;

import android.nfc.NdefRecord;
import android.os.Bundle;
import android.view.View;

import net.wequick.small.Small;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;

/**
 * about-
 * Created by lishoulin on 2017/2/13.
 */

public class MeFragment extends LazyFragment {
    private static MeFragment fragment;

    public static MeFragment newInstance() {
        return fragment == null ? new MeFragment() : fragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_me);
        ButterKnife.inject(this, getContentView());

    }

    @Override
    public void lazyLoad() {

    }

    @OnClick({R.id.me_circle_bt, R.id.me_setting_bt, R.id.me_contanct_bt, R.id.me_about_bt})
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.me_circle_bt: //漫画圈
                if (Small.openUri("com/circle", getActivity())) {

                } else {
                    toast("提示下载");
                }
                break;
            case R.id.me_setting_bt: //设置
                gotoActivity(SettingsActivity.class);
                break;
            case R.id.me_contanct_bt: //联系我们
                Small.openUri("about?type=feedback", getContext());
                break;
            case R.id.me_about_bt:  //关于我们
                Small.openUri("about?type=about", getContext());
                break;
            default:
                break;
        }
    }
}
