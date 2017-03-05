package xi.lsl.code.app.main.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.relex.circleindicator.CircleIndicator;
import rx.subscriptions.CompositeSubscription;
import xi.lsl.code.app.main.R;
import xi.lsl.code.app.main.base.widget.LazyFragment;
import xi.lsl.code.lib.utils.entity.Slide;

/**
 * Created by lishoulin on 2017/2/13.
 */

public class BookFragment extends LazyFragment {

    private static BookFragment fragment;

    private CompositeSubscription mCompositeSubscription;
    private List<Slide.ListBean> mListBeen = null;
    private ShowPageAdapter mPageAdapter;
    private ShowPageHandler mPageHandler;
    //    private List<Fragment> fragments = null;
//
    private static final String[] mTitles = new String[]{"热血", "国产", "同人", "鼠绘"};
    //
//
    @InjectView(R.id.book_vp)
    ViewPager mViewPager;
    @InjectView(R.id.book_vp_tip)
    CircleIndicator mCircleIndicator;
    @InjectView(R.id.book_tb)
    TabLayout mTabLayout;
    @InjectView(R.id.book_vp_content)
    ViewPager mContentViewPager;


    public static BookFragment newInstance() {
        return fragment == null ? new BookFragment() : fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    private void initView() {
//        if (fragments == null || fragments.size() < 0) {
//            fragments = new ArrayList<>(4);
//            fragments.add(HotFragment.newInstance());
//            fragments.add(MadeInChinaFragment.newInstance());
//            fragments.add(FanFragment.newInstance());
//            fragments.add(MouseFragment.newInstance());
//        }

    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_book);
        ButterKnife.inject(this, getContentView());
        if (mListBeen == null) {
            mListBeen = new ArrayList<Slide.ListBean>();
        }
//        mPageAdapter = new ShowPageAdapter(mListBeen, getContext());
//        mViewPager.setAdapter(mPageAdapter);
//        mCircleIndicator.setViewPager(mViewPager);
        initView();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[3]));

//        mContentViewPager.setAdapter(new MyPageAdapter(getFragmentManager(), fragments, mTitles));
//        mTabLayout.setupWithViewPager(mContentViewPager);

        lazyLoad();
    }


    @Override
    public void lazyLoad() {
        try {
            getSlideData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniSlideView(final List<Slide.ListBean> beans) {
        mViewPager.setAdapter(new ShowPageAdapter(beans, getContext()));
        mPageAdapter.notifyDataSetChanged();
        mPageHandler = new ShowPageHandler();
        mPageHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPageHandler.showPage(mViewPager, beans.size());
            }
        }, 6_000);

    }


    private void getSlideData() throws IOException {

//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .addInterceptor(new LoggerInterceptor())
//                .cookieJar(new CookieManger())
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://two.ishuhui.com/")
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        CommonAPis commonAPis = retrofit.create(CommonAPis.class);
//
//
//        mCompositeSubscription.add(commonAPis.getSlide()
//                .compose(RxSchedulers.<Slide>io_main())
//                .subscribe(new Action1<Slide>() {
//                    @Override
//                    public void call(Slide slide) {
//                        if (slide.getList() != null && slide.getList().size() > 0) {
//                            iniSlideView(slide.getList());
//                        }
//                    }
//                }));
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        mCompositeSubscription.clear();
    }
}
