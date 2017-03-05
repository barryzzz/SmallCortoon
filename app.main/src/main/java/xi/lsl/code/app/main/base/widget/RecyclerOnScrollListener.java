package xi.lsl.code.app.main.base.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/22.
 */

public abstract class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    protected boolean isloading = false;

    //已经加载出来的Item的数量
    private int totalItemCount;


    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int lastVisibleItem;


    private layoutManagerType mLayoutManagerType;

    private int[] lastPosition;

    private enum layoutManagerType {
        LINEAR_LAYOUT,
        GRID_LAYOUT,
        STAGGERED_GRID_LAYOUT
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (mLayoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                mLayoutManagerType = layoutManagerType.LINEAR_LAYOUT;
            } else if (layoutManager instanceof GridLayoutManager) {
                mLayoutManagerType = layoutManagerType.GRID_LAYOUT;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                mLayoutManagerType = layoutManagerType.STAGGERED_GRID_LAYOUT;
            } else {
                throw new RuntimeException("unnot user layoutmanger");
            }
        }

        switch (mLayoutManagerType) {
            case LINEAR_LAYOUT:
                lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID_LAYOUT:
                lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID_LAYOUT:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPosition == null) {
                    lastPosition = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPosition);
                lastVisibleItem = findMax(lastPosition);
                break;
            default:
                break;
        }

    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        visibleItemCount = layoutManager.getChildCount();
        totalItemCount = layoutManager.getItemCount();


        if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem >= totalItemCount - 1) {
            if (!isloading) {
                isloading = true;
                onLoadMore();
            }
        }
    }

    public abstract void onLoadMore();


    private int findMax(int last[]) {
        int max = last[0];
        for (int value : last) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


}
