package xi.lsl.code.app.main.book.mouse;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;

/**
 * Created by lishoulin on 2017/3/1.
 */

public class MouseFragment extends LazyFragment {
    private static Fragment mFragment;

    public static MouseFragment newInstance() {
        return mFragment == null ? new MouseFragment() : (MouseFragment) mFragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_mouse);
        ButterKnife.inject(this, getContentView());
    }

    @Override
    public void lazyLoad() {

    }
}
