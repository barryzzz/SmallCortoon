package xi.lsl.code.app.main.book;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.utils.Imageloader;

/**
 * book分类页面
 * Created by lishoulin on 2017/3/20.
 */

public class BookFlAdapter extends RecyclerView.Adapter<BookFlAdapter.ViewHolder> {

    private Context contexts;
    private List<BookFragment.FlBean> datas;

    public BookFlAdapter(Context contexts, List<BookFragment.FlBean> datas) {
        this.contexts = contexts;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_fl, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(datas.get(position).title);
        Imageloader.display(contexts, datas.get(position).url, holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnBookClickLisenter.onBookClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_book_fl_tv)
        TextView mTextView;
        @InjectView(R.id.item_book_fl_img)
        ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    private OnBookClickLisenter mOnBookClickLisenter;

    public void setOnBookClickLisenter(OnBookClickLisenter lisenter) {
        mOnBookClickLisenter = lisenter;
    }

    public interface OnBookClickLisenter {

        void onBookClick(int position);
    }
}
