package xi.lsl.code.app.main.search;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.base.BaseActivity;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/28.
 */

public class SearchActivity extends BaseActivity {
    @InjectView(R.id.search_edit)
    EditText mSearchEdit;
    @InjectView(R.id.search_back)
    TextView mBack;
    @InjectView(R.id.search_sea_btn)
    TextView mSearch;
    @InjectView(R.id.search_frame)
    LinearLayout mFramelayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode().setDuration(1000));
        getWindow().setExitTransition(null);

        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);


//        mSearchEdit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                mSearchEdit.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                perforAnimation();
//            }
//        });
    }

    /**
     * 进入动画
     */
    private void perforAnimation() {
        float originY = getIntent().getIntExtra("y", 0);
        int[] location = new int[2];
        mSearchEdit.getLocationOnScreen(location);

        float translateY = originY - (float) location[1];

        mSearchEdit.setY(mSearchEdit.getY() + translateY);
        mSearch.setY(mSearchEdit.getY() + (mSearchEdit.getHeight() - mSearch.getHeight()) / 2);
        mBack.setY(mSearchEdit.getY() + (mSearchEdit.getHeight() - mBack.getHeight()) / 2);

        float top = getResources().getDisplayMetrics().density * 5;
        ValueAnimator tranAnimator = ValueAnimator.ofFloat(mSearchEdit.getHeight(), top);
        tranAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSearchEdit.setY((Float) animation.getAnimatedValue());
                mSearch.setY(mSearchEdit.getY() + (mSearchEdit.getHeight() - mSearch.getHeight()) / 2);
                mBack.setY(mSearchEdit.getY() + (mSearchEdit.getHeight() - mBack.getHeight()) / 2);

            }
        });


        ValueAnimator scalaVa = ValueAnimator.ofFloat(0.7f, 1);
        scalaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSearchEdit.setScaleX((Float) animation.getAnimatedValue());
            }
        });

        ValueAnimator alphaVa = ValueAnimator.ofFloat(0, 1f);
        alphaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSearch.setAlpha((Float) animation.getAnimatedValue());
                mSearchEdit.setAlpha((Float) animation.getAnimatedValue());
                mBack.setAlpha((Float) animation.getAnimatedValue());
            }
        });


        tranAnimator.setDuration(500);
        scalaVa.setDuration(500);
        alphaVa.setDuration(500);

        tranAnimator.start();
        scalaVa.start();
        alphaVa.start();
    }

    /**
     * 退出动画
     */
    private void perforExitAnimation() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        perforExitAnimation();
        finishAfterTransition();
    }


}


