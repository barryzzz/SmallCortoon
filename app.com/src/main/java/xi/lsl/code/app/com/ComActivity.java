package xi.lsl.code.app.com;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.wequick.small.Small;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.com.R;
import xi.lsl.code.app.com.comment.ComAdapter;
import xi.lsl.code.app.com.comment.CommentContract;
import xi.lsl.code.app.com.comment.CommentModel;
import xi.lsl.code.app.com.comment.CommentPresenter;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.entity.BmobComment;

public class ComActivity extends BaseActivity implements CommentContract.View {

    @InjectView(R.id.com_rv)
    RecyclerView mRecyclerView;
    @InjectView(R.id.title_back)
    Button mBack;
    @InjectView(R.id.title_tv)
    TextView mTitle;


    private ComAdapter mAdapter;

    private List<BmobComment.ResultsBean> datas;


    private CommentContract.Presenter mPresenter;

    private String bookId = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        ButterKnife.inject(this);

        init();

        iniRecycleView();


    }

    private void init() {
        Uri uri = Small.getUri(this);
        if (uri != null) {
            bookId = uri.getQueryParameter("bookid");
            mTitle.setText(uri.getQueryParameter("bookname"));
        } else {
            toast("bookId读取异常!");
            mTitle.setText("读取书本信息失败");
        }
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void iniRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        datas = new ArrayList<>();
        mAdapter = new ComAdapter(mContext, datas);

        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new CommentPresenter(new CommentModel(), this);
        mPresenter.subscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bookId != null)
            mPresenter.loadComment(bookId);
        else
            toast("bookId读取异常!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showloading() {

    }

    @Override
    public void dissloading() {

    }

    @Override
    public void faild(String msg) {

    }

    @Override
    public void showComment(List<BmobComment.ResultsBean> comments) {
        if (comments.size() > 0) {
            datas.clear();
            datas.addAll(comments);
            mAdapter.notifyDataSetChanged();
        }
    }
}
