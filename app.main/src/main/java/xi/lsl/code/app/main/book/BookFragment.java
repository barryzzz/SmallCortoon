package xi.lsl.code.app.main.book;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;

/**
 * book
 * Created by lishoulin on 2017/2/13.
 */

public class BookFragment extends LazyFragment implements BookFlAdapter.OnBookClickLisenter {

    @SuppressLint("StaticFieldLeak")
    private static BookFragment fragment;

    @InjectView(R.id.book_rv)
    RecyclerView mRecyclerView;

    @InjectView(R.id.title_title)
    TextView mTextView;


    private BookFlAdapter mBookFlAdapter;

    public static BookFragment newInstance() {
        return fragment == null ? new BookFragment() : fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_book);
        ButterKnife.inject(this, getContentView());
        mTextView.setText("分类");

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mBookFlAdapter = new BookFlAdapter(getContext(), getFl());
        mBookFlAdapter.setOnBookClickLisenter(this);
        mRecyclerView.setAdapter(mBookFlAdapter);

    }


    @Override
    public void lazyLoad() {

    }


    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();

    }

    @Override
    public void onBookClick(int position) {
        toast(position + "");
        Map<String, String> map = new HashMap<>();
        map.put("ClassifyId", getFl().get(position).classifyIds);
        map.put("Titles", getFl().get(position).title);

        gotoActivity(BookFlActivity.class, map);
    }


    private List<FlBean> getFl() {
        List<FlBean> list = new ArrayList<>();
        list.add(new FlBean("热血", "0", "https://qlogo4.store.qq.com/qzone/408930131/408930131/100?1465139612"));
        list.add(new FlBean("国产", "1", "https://qlogo4.store.qq.com/qzone/408930131/408930131/100?1465139612"));
        list.add(new FlBean("同人", "2", "https://qlogo4.store.qq.com/qzone/408930131/408930131/100?1465139612"));
        list.add(new FlBean("鼠绘", "3", "https://qlogo4.store.qq.com/qzone/408930131/408930131/100?1465139612"));

        return list;

    }

    public class FlBean {
        public String title;
        public String classifyIds;
        public String url;

        FlBean(String title, String classifyIds, String url) {
            this.title = title;
            this.classifyIds = classifyIds;
            this.url = url;
        }
    }
}
