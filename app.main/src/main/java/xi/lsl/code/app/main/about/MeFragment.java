package xi.lsl.code.app.main.about;

import android.os.Bundle;

import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;

/**
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
//        ButterKnife.inject(this, getContentView());

    }

    @Override
    public void lazyLoad() {

    }
}
