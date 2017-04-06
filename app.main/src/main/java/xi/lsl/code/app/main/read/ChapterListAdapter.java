package xi.lsl.code.app.main.read;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.entity.Chapter;
import xi.lsl.code.lib.utils.utils.L;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/4/6.
 */

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ViewHolder> {

    private List<Chapter> mChapters;


    public ChapterListAdapter(List<Chapter> mChapters) {
        this.mChapters = mChapters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popu_layout_item, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //TODO 根据当前章节，给章节标记 待实现
        final Chapter chapter = mChapters.get(position);
        holder.mSort.setText(String.valueOf(chapter.getSort()));
        holder.mSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnChapterClickListener != null) {
                    mOnChapterClickListener.onChapterClick(v, chapter);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChapters == null ? 0 : mChapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.popu_sort)
        TextView mSort;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    /**
     * 抛出点击事件
     */
    private onChapterClickListener mOnChapterClickListener;

    public void setOnChapterClickListener(onChapterClickListener mOnChapterClickListener) {
        this.mOnChapterClickListener = mOnChapterClickListener;
    }

    interface onChapterClickListener {
        void onChapterClick(View v, Chapter chapter);
    }
}
