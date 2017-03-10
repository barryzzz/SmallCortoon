package xi.lsl.code.app.main.search;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;


/**
 * Created by lishoulin on 2017/2/13.
 */

public class SearchFragment extends LazyFragment {
    private static SearchFragment fragment;

    @InjectView(R.id.search_sea)
    TextView mSearchtextView;

    public static SearchFragment newInstance() {
        return fragment == null ? new SearchFragment() : fragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_search);
        ButterKnife.inject(this, getContentView());
    }

    @Override
    public void lazyLoad() {

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.search_sea)
    public void goSearch(View view) {
        View view1 = findViewById(R.id.search_sea);
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view1, "sharesearch");
        startActivity(intent, options.toBundle());
    }
}
