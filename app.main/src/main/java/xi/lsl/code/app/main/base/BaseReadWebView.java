package xi.lsl.code.app.main.base;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import xi.lsl.code.lib.utils.utils.ScreenUtil;


/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/27.
 */

public class BaseReadWebView extends WebView {
    private int mWidth;
    private int mHeigth;
    private int startY;
    private int mTouchSlop;
    private onCenterClickLisenter onCenterClickLisenter;
    private onScrollLisenter onScrollLisenter;

    public BaseReadWebView(Context context) {
        this(context, null, 0);
    }

    public BaseReadWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseReadWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mWidth = ScreenUtil.getScreenWidth();
        mHeigth = ScreenUtil.getScreenHeight();
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int dx = (int) event.getX();
                int dy = (int) event.getY();
                startY = dy;
                if (ScreenUtil.getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT) {
                    if (dx >= mWidth / 3 && dx <= mWidth * 2 / 3 && dy >= mHeigth / 3 && dy <= mHeigth * 2 / 3) {
                        if (onCenterClickLisenter != null) {
                            onCenterClickLisenter.onClick(this);
                        }
                    }
                } else {
                    if (dy >= mWidth / 3 && dy <= mWidth * 2 / 3 && dx >= mHeigth / 3 && dx <= mHeigth * 2 / 3) {
                        if (onCenterClickLisenter != null) {
                            onCenterClickLisenter.onClick(this);
                        }
                    }
                }

                break;

            case MotionEvent.ACTION_UP:
//                int move = ((int) event.getY()) - startY;
//                if (move > mTouchSlop || -move < mTouchSlop) {
//                    if (onScrollLisenter != null) {
//                        onScrollLisenter.onScroll(this);
//                    }
//                }
                break;

        }
        return super.onTouchEvent(event);
    }

    public void setOnCenterClickLisenter(onCenterClickLisenter onCenterClickLisenter) {
        this.onCenterClickLisenter = onCenterClickLisenter;
    }

    public interface onCenterClickLisenter {
        void onClick(View v);
    }

    public void setOnScrollLisenter(onScrollLisenter onScrollLisenter) {
        this.onScrollLisenter = onScrollLisenter;
    }

    public interface onScrollLisenter {
        void onScroll(View v);
    }


}
