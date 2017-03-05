package xi.lsl.code.app.main.home.homechild;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.R;
import xi.lsl.code.app.main.base.widget.LazyFragment;
import xi.lsl.code.app.main.base.widget.SpacesItemDecoration;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.utils.ScreenUtil;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/22.
 */

public class SubFragment extends LazyFragment implements SubBookContract.View {

    private static SubFragment fragment;

    @InjectView(R.id.sub_rv)
    RecyclerView mSubrecyclerView;
    @InjectView(R.id.sub_refresh)
    SwipeRefreshLayout mSubRefresh;

    private List<Book> mSubBooks;
    private SubBookContract.Presenter presenter;

    private BookAdapter mSubAdapter;

    public static SubFragment newInstance() {
        return fragment == null ? new SubFragment() : fragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_sub);
        ButterKnife.inject(this, getContentView());
        iniview();
    }

    private void iniview() {
        mSubrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mSubrecyclerView.addItemDecoration(new SpacesItemDecoration(ScreenUtil.px2dip(16)));
        mSubrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSubBooks = new ArrayList<>();
        mSubAdapter = new BookAdapter(getContext(), mSubBooks,true);
        mSubrecyclerView.setAdapter(mSubAdapter);
        mSubAdapter.setSubItemClickLinsenter(new BookAdapter.onSubItemClickLinsenter() {
            @Override
            public void onSubItemClick(View v, int position) {
                toast("chakan");
            }
        });

        mSubRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSubBooks.clear();
                presenter.loadSubBooks();
            }
        });

        presenter = new SubBookPresenter(new SubBookModel(),this);
        presenter.subscribe();


        lazyLoad();
    }

    @Override
    public void lazyLoad() {
        presenter.loadSubBooks();
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showBooks(List<Book> books) {

        mSubBooks.addAll(books);
        mSubAdapter.notifyDataSetChanged();

        if(mSubRefresh.isRefreshing()){
            mSubRefresh.setRefreshing(false);
        }
    }

    @Override
    public void faild(String msg) {
        toast(msg);
    }

    @Override
    public void showNoBooks() {
        toast("no data to update");
    }

    @Override
    public void showSub(boolean msg) {

    }


    @Override
    public void setPresenter(SubBookContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showloading() {

    }

    @Override
    public void dissloading() {

    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        presenter.unsubscribe();
    }
}


