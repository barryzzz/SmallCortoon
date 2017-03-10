package xi.lsl.code.app.main.book.mic;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;

/**
 * 国产
 * Created by lishoulin on 2017/3/1.
 */

public class MadeInChinaFragment extends LazyFragment {

    private static Fragment mFragment;

    public static MadeInChinaFragment newInstance() {
        return mFragment == null ? new MadeInChinaFragment() : (MadeInChinaFragment) mFragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_madeinchina);
        ButterKnife.inject(this, getContentView());
    }

    @Override
    public void lazyLoad() {

    }
}
