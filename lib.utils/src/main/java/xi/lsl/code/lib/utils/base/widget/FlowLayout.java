package xi.lsl.code.lib.utils.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lishoulin on 2017/3/11.
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int heigthMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heigthModel = MeasureSpec.getMode(heightMeasureSpec);

        int layoutHeigth = 0; //布局的高度
        int currentHeigth = 0; //当前高度

        int currentLineWidth = 0;  //当前行宽
        int maxLineWidth = 0;//最大行宽
        int count = getChildCount();

        View childView = null;

        for (int i = 0; i < count; i++) {
            childView = getChildAt(i);
            if (childView.getVisibility() == GONE) {
                continue;
            }
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            int childwidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childheigth = childView.getMeasuredHeight() + params.bottomMargin + params.topMargin;

            if ((currentLineWidth + childwidth) > widthMeasure - getPaddingLeft() - getPaddingRight()) {
                maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                layoutHeigth += currentHeigth;

                currentLineWidth = childwidth;
                currentHeigth = childheigth;
            } else {
                currentLineWidth += childwidth;
                currentHeigth = Math.max(childheigth, layoutHeigth);
            }

            if (i == count - 1) {
                maxLineWidth = Math.max(maxLineWidth, currentLineWidth);
                layoutHeigth += childheigth;
            }
        }


        setMeasuredDimension(widthModel != MeasureSpec.EXACTLY ?
                        maxLineWidth + getPaddingRight() + getPaddingLeft() : widthMeasure,
                heigthModel != MeasureSpec.EXACTLY ?
                        layoutHeigth + getPaddingBottom() + getPaddingTop() : layoutHeigth);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = getWidth();

        int rigthLimit = width - getPaddingRight();

        int baseLeft =0+ getPaddingLeft();
        int baseTop = 0+getPaddingTop();

        int curLeft = baseLeft;
        int curTop = baseTop;




        View child=null;


        int viewL, viewT, viewR, viewB;
        MarginLayoutParams params=null;

        int childwidth;
        int childheigth;

        int childW, childH;

        int lastChildHeigth = 0;

        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            childW = child.getMeasuredWidth();
            childH = child.getMeasuredHeight();


            params = (MarginLayoutParams) child.getLayoutParams();

            childwidth = childW + params.leftMargin + params.rightMargin;
            childheigth = childH + params.bottomMargin + params.topMargin;

            if (curLeft + childwidth > rigthLimit) {
                curTop = curTop + lastChildHeigth;
                viewL = baseLeft + params.leftMargin;
                viewT = curTop + params.topMargin;
                viewR = viewL + childW;
                viewB = viewT + childH;

                curLeft = baseLeft + childwidth;
            } else {

                viewL = curLeft + params.leftMargin;
                viewT = curTop + params.topMargin;
                viewR = viewL + childW;
                viewB = viewT + childH;

                curLeft = curLeft + childwidth;

            }
            lastChildHeigth = childheigth;
            child.layout(viewL, viewT, viewR, viewB);

        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
