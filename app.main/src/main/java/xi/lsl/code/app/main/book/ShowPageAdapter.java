package xi.lsl.code.app.main.book;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import xi.lsl.code.lib.utils.entity.Slide;
import xi.lsl.code.lib.utils.utils.Imageloader;


/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/3/1.
 */

public class ShowPageAdapter extends PagerAdapter {

    private List<Slide.ListBean> mListBeen;
    private Context mContext;
    private ImageView mImageView;
    private List<ImageView> mViews;


    public ShowPageAdapter(List<Slide.ListBean> listBeen, Context context) {
        mListBeen = listBeen;
        mContext = context;
        mViews = new ArrayList<>();
        if (mListBeen != null)
            initView(mListBeen.size());
    }

    private void initView(int length) {
        for (int i = 0; i < length; i++) {
            ImageView view = new ImageView(mContext);
            mViews.add(view);
        }
    }

    @Override
    public int getCount() {
        return mListBeen == null ? 0 : mListBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mImageView = mViews.get(position);
        mImageView.setAdjustViewBounds(true);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("info--->", "" + mListBeen.get(position).getLink());
            }
        });
        Imageloader.display(mContext, mListBeen.get(position).getImg(), mImageView);
        container.addView(mImageView, 0);
        return mImageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mImageView = mViews.get(position);
        container.removeView(mImageView);
    }
}
