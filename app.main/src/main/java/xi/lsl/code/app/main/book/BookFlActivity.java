package xi.lsl.code.app.main.book;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.BaseActivity;
import xi.lsl.code.lib.utils.entity.Book;

public class BookFlActivity extends BaseActivity implements BookFlContract.View, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.title_tv)
    TextView mTextView;
    @InjectView(R.id.book_fl_rv)
    RecyclerView mRecyclerView;
    @InjectView(R.id.book_fl_rf)
    SwipeRefreshLayout mRefreshLayout;

    private BookFlPresenter mPresenter;
    private int pageIndex = 0;
    private int pageSize = 30;
    private String ClassifyId;

    private BookListAdapter mAdapter;
    private List<Book> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_fl);
        ButterKnife.inject(this);
        iniView();
    }

    private void iniView() {
        ClassifyId = getIntent().getStringExtra("ClassifyId");
        String title = getIntent().getStringExtra("Titles");
        mBooks = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new BookListAdapter(this, mBooks);
        mTextView.setText(title);

        mPresenter = new BookFlPresenter(this, new BookFlModel());


        mPresenter.subscribe();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ClassifyId != null) {
            mPresenter.getCategoryData(ClassifyId, String.valueOf(pageSize), String.valueOf(pageIndex));
            mRefreshLayout.setRefreshing(true);
        }
    }

    @OnClick({R.id.title_back})
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
//                finish();
                mAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void showBooks(List<Book> books) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (mBooks != null) {
            if (books != null && books.size() > 0) {
                mBooks.addAll(books);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showFaild(String msg) {

    }

    @Override
    public void setPresenter(BookFlContract.Presenter presenter) {

    }

    @Override
    public void showloading() {

    }

    @Override
    public void dissloading() {

    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        mPresenter.getCategoryData(ClassifyId, String.valueOf(pageSize), String.valueOf(pageIndex));
        mBooks.clear();
    }
}
