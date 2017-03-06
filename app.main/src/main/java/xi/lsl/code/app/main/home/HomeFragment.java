package xi.lsl.code.app.main.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xi.lsl.code.app.main.R;
import xi.lsl.code.app.main.home.homechild.HotWeekFragment;
import xi.lsl.code.app.main.home.homechild.SubFragment;
import xi.lsl.code.lib.utils.base.MyPageAdapter;
import xi.lsl.code.lib.utils.base.widget.LazyFragment;


/**
 * Created by lishoulin on 2017/2/13.
 */

public class HomeFragment extends LazyFragment {

    private static HomeFragment fragment;
    private List<Fragment> fragments;

    @InjectView(R.id.home_vp)
    ViewPager viewPager;
    @InjectView(R.id.home_tab)
    RadioGroup mRadioGroup;

    public static HomeFragment newInstances() {
        return fragment == null ? new HomeFragment() : fragment;
    }


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ButterKnife.inject(this, getContentView());
        init();
    }


    private void init() {

        if (fragments == null || fragments.size() <= 0) {
            fragments = new ArrayList<>(2);
            fragments.add(HotWeekFragment.newInstance());
            fragments.add(SubFragment.newInstance());
        }


        viewPager.setAdapter(new MyPageAdapter(getFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.home_tab_week);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.home_tab_rev);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.home_tab_week, R.id.home_tab_rev})
    public void tabOnclick(View v) {
        switch (v.getId()) {
            case R.id.home_tab_week:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.home_tab_rev:
                viewPager.setCurrentItem(1, true);
                break;
            default:
                break;
        }
    }


    @Override
    public void lazyLoad() {

    }


}
