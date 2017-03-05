package xi.lsl.code.app.main.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xi.lsl.code.app.main.R;
import xi.lsl.code.app.main.about.MeFragment;
import xi.lsl.code.app.main.book.BookFragment;
import xi.lsl.code.app.main.home.HomeFragment;
import xi.lsl.code.app.main.search.SearchFragment;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.main_vp)
    ViewPager viewPager;
    @InjectView(R.id.main_rg_group)
    RadioGroup mRadioGroup;

    private static final List<Fragment> fragments = new ArrayList<>(4);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }


    private void init() {
        if (fragments.size() <= 0) {
            fragments.add(HomeFragment.newInstances());
            fragments.add(BookFragment.newInstance());
            fragments.add(SearchFragment.newInstance());
            fragments.add(MeFragment.newInstance());
        }

        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.main_rb_home);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.main_rb_book);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.main_rb_search);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.main_rb_me);
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

    @OnClick({R.id.main_rb_home, R.id.main_rb_book, R.id.main_rb_search, R.id.main_rb_me})
    public void bottomOnClick(View v) {
        switch (v.getId()) {
            case R.id.main_rb_home:
                viewPager.setCurrentItem(0, true);
                break;
            case R.id.main_rb_book:
                viewPager.setCurrentItem(1, true);

                break;
            case R.id.main_rb_search:
                viewPager.setCurrentItem(2, true);

                break;
            case R.id.main_rb_me:
                viewPager.setCurrentItem(3, true);

                break;
            default:
                break;
        }

    }


    private class MyPageAdapter extends FragmentStatePagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }


}
