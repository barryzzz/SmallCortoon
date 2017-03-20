package xi.lsl.code.app.main.search;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.base.widget.FlowLayout;
import xi.lsl.code.lib.utils.entity.Book;

/**
 * Description: 搜索界面
 * Author   :lishoulin
 * Date     :2017/2/28.
 */

public class SearchActivity extends BaseActivity implements SearchContract.View {
    @InjectView(R.id.search_edit)
    EditText mSearchEdit;


    @InjectView(R.id.search_flow)
    FlowLayout mFlowLayout;
    @InjectView(R.id.search_no_search_rl)
    RelativeLayout mNoSearchRL;

    private SearchContract.Presenter mPresenter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode().setDuration(1000));
        getWindow().setExitTransition(null);

        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        mPresenter = new SearchPresenter(new SearchModel(this), this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe(); //注册
        mPresenter.getLocalSearch();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();//取消注册
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }

    @OnClick({R.id.search_clear, R.id.search_sea_btn, R.id.search_back})
    public void doClick(View view) {

        switch (view.getId()) {
            case R.id.search_clear:
                if (mPresenter != null)
                    mPresenter.clearSearch();
                break;
            case R.id.search_sea_btn:
                if (mPresenter != null) {
                    String str = mSearchEdit.getText().toString().trim();
                    mPresenter.search(str);
                }
                break;
            case R.id.search_back:
                finish();
                break;
            default:
                break;
        }

    }


    @Override
    public void showLocalSearch(String[] str) {
        if (str != null && !"".equals(str[0]) && str.length > 0) {
            mNoSearchRL.setVisibility(View.GONE);
            TextView textView;
            for (String s : str) {
                textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_flow_layout, mFlowLayout, false);
                textView.setText(s);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast(((TextView) v).getText().toString());
                    }
                });
                mFlowLayout.addView(textView);
            }
        } else {
            mNoSearchRL.setVisibility(View.VISIBLE);
            mFlowLayout.removeAllViews();
        }
    }

    @Override
    public void showSearchBooks(List<Book> books) {
        //读取 localsearch
        //显示搜索
    }

    @Override
    public void faild(String msg) {
        toast(msg);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }

    @Override
    public void showloading() {

    }

    @Override
    public void dissloading() {

    }
}


