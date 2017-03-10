package xi.lsl.code.app.main.book.hot;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;


/**
 * 热血
 * Created by lishoulin on 2017/3/1.
 */

public class HotFragment extends LazyFragment {
    private static Fragment mFragment;

    public static HotFragment newInstance() {
        return mFragment == null ? new HotFragment() : (HotFragment) mFragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_hot);
        ButterKnife.inject(this,getContentView());
    }

    @Override
    public void lazyLoad() {

    }
}
