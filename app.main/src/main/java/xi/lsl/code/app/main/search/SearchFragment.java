package xi.lsl.code.app.main.search;

import android.os.Bundle;

import xi.lsl.code.app.main.R;
import xi.lsl.code.app.main.base.widget.LazyFragment;


/**
 * Created by lishoulin on 2017/2/13.
 */

public class SearchFragment extends LazyFragment {
    private static SearchFragment fragment;
//
//    @in(R.id.search_sea)
//    TextView mSearchtextView;

    public static SearchFragment newInstance() {
        return fragment == null ? new SearchFragment() : fragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_search);
//        ButterKnife.bind(this, getContentView());
    }

    @Override
    public void lazyLoad() {

    }

//
//    @OnClick(R.id.search_sea)
//    public void goSearch(View view) {
//        int location[] = new int[2];
//        mSearchtextView.getLocationOnScreen(location);
//        Intent intent = new Intent(getActivity(), SearchActivity.class);
//        intent.putExtra("x", location[0]);
//        intent.putExtra("y", location[1]);
//        startActivity(intent);
//    }
}
