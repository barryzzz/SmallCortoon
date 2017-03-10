package xi.lsl.code.app.main.book.fan;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;

/**
 * 同人
 * Created by lishoulin on 2017/3/1.
 */

public class FanFragment extends LazyFragment {

    private static Fragment mFragment;

    public static FanFragment newInstance() {
        return mFragment == null ? new FanFragment() : (FanFragment) mFragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_fan);
        ButterKnife.inject(this, getContentView());
    }

    @Override
    public void lazyLoad() {

    }
}
