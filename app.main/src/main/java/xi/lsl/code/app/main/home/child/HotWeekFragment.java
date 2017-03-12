package xi.lsl.code.app.main.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import net.wequick.small.Small;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.App;
import xi.lsl.code.app.main.R;
import xi.lsl.code.app.main.read.WebReadActivity;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;
import xi.lsl.code.lib.utils.base.widget.RecyclerOnScrollListener;
import xi.lsl.code.lib.utils.base.widget.SpacesItemDecoration;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.net.Constants;
import xi.lsl.code.lib.utils.utils.FastOnClickUtil;
import xi.lsl.code.lib.utils.utils.SPUtil;
import xi.lsl.code.lib.utils.utils.ScreenUtil;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

/**
 * 本周热门更新
 * Created by lishoulin on 2017/2/15.
 */

public class HotWeekFragment extends LazyFragment implements BookContract.View {

    private static HotWeekFragment childFragment;

    private BookContract.Presenter presenter;

    private BookAdapter adapter;
    private List<Book> books;

    private int BookPageIndex = 0;
    private boolean isLoadMore = false;
    private Book mCurrentBook = null;

    @InjectView(R.id.hot_rv)
    RecyclerView mHotRecyclerView;

    @InjectView(R.id.hot_refresh)
    SwipeRefreshLayout mRefreshLayout;

    public static HotWeekFragment newInstance() {
        return childFragment == null ? new HotWeekFragment() : childFragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_hot_week);
        ButterKnife.inject(this, getContentView());
        initView();
        //start load
        lazyLoad();
    }


    private void initView() {
        mHotRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        mHotRecyclerView.addItemDecoration(new SpacesItemDecoration(ScreenUtil.px2dip(16f)));
        mHotRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHotRecyclerView.addOnScrollListener(new RecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                BookPageIndex++;
                isLoadMore = true;
                presenter.loadWeekBook(BookPageIndex);
                isloading = false;
            }
        });
        books = new ArrayList<>();
        adapter = new BookAdapter(getContext(), books);
        mHotRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickLinsenter(new BookAdapter.OnItemClickLinsenter() {
            @Override
            public void onItemClick(View v, int position) {
                if (FastOnClickUtil.fastClick(1000)) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put(Constants.WEB_CHAPTER_ID, String.valueOf(books.get(position).getLastChapter().getId()));
                    map.put(Constants.WEB_TITLE, books.get(position).getTitle());
                    gotoActivity(WebReadActivity.class, map);
                } else {
                    toast("重复点击啦");
                }
            }
        });

        adapter.setSubItemClickLinsenter(new BookAdapter.onSubItemClickLinsenter() {
            @Override
            public void onSubItemClick(View v, int position) {
                if (FastOnClickUtil.fastClick800()) {
                    mCurrentBook = books.get(position);
                    presenter.subBook(books.get(position).getId(), true);
                }

            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BookPageIndex = 0;
                books.clear();
                presenter.loadWeekBook(BookPageIndex);
            }
        });


        presenter = new BookPresenter(new BookModel(), this);
        presenter.subscribe();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        presenter.unsubscribe();
    }

    @Override
    public void lazyLoad() {
        presenter.loadWeekBook(BookPageIndex);
    }


    @Override
    public void setPresenter(BookContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showloading() {

    }

    @Override
    public void dissloading() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showBooks(List<Book> b) {
        if (isLoadMore) {
            isLoadMore = false;
            int size = books.size();
            books.addAll(b);
            adapter.notifyItemRangeChanged(size, b.size());

        } else {
            books.addAll(b);
            adapter.notifyDataSetChanged();
        }

        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void faild(String msg) {
        toast(msg);
    }

    @Override
    public void showNoBooks() {
        toast("没有更多数据");
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showSub(boolean msg) {
        if (msg) {
            if (mCurrentBook != null)
                SPUtil.saveObject(App.userInfo.getEmail() + "BookId" + mCurrentBook.getId(), mCurrentBook.getId());
            toast("订阅成功");
            adapter.notifyDataSetChanged();  //需要改进，大范围刷新不可取
        } else {
            SPUtil.saveObject(App.userInfo.getEmail() + "BookId" + mCurrentBook.getId(), mCurrentBook.getId());
            toast("哈哈，你已经订阅过啦");
            adapter.notifyDataSetChanged();
        }
    }
}
