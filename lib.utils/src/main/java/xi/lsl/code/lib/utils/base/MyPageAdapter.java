package xi.lsl.code.lib.utils.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishoulin on 2017/3/1.
 */

public class MyPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] mTitls;

    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);
        if (fragments == null)
            fragments = new ArrayList<>();
        this.fragments = fragments;
    }

    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments, String[] mTitls) {

        super(fm);
        if (fragments == null)
            fragments = new ArrayList<>();
        this.fragments = fragments;
        this.mTitls = mTitls;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitls[position];
    }
}
