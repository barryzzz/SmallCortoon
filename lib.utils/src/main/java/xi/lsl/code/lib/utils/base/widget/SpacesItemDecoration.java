package xi.lsl.code.lib.utils.base.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/23.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int spance;

    public SpacesItemDecoration(int spance) {
        this.spance = spance;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = spance;
        outRect.right = spance;
        outRect.bottom = spance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spance;
        }
    }
}
