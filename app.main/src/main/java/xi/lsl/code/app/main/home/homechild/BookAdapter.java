package xi.lsl.code.app.main.home.homechild;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.App;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.utils.Imageloader;
import xi.lsl.code.lib.utils.utils.SPUtil;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/21.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    private WeakReference<Context> contextWeakReference;
    private List<Book> datas;
    private boolean isShowSub = false;

    private OnItemClickLinsenter itemClickLinsenter;
    private onSubItemClickLinsenter subItemClickLinsenter;


    public BookAdapter(Context context, List<Book> datas) {
        this.contextWeakReference = new WeakReference<Context>(context);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.datas = datas;
    }


    public BookAdapter(Context context, List<Book> datas, boolean isShowSub) {
        this.contextWeakReference = new WeakReference<Context>(context);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.datas = datas;
        this.isShowSub = isShowSub;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_book, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final Book book = datas.get(position);
        Imageloader.display(contextWeakReference.get(), book.getFrontCover(), holder.mImageView);
        holder.mTitile.setText(book.getTitle());
        if (null != book.getLastChapter() && !"null".equals(book.getLastChapter().getChapterNo()))
            holder.mComTitle.setText(book.getTitle() + book.getLastChapter().getChapterNo() + "话");
        holder.mExplain.setText(book.getExplain());
        if (itemClickLinsenter != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickLinsenter.onItemClick(v, position);
                }
            });
        }
        if (subItemClickLinsenter != null) {
            holder.mSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subItemClickLinsenter.onSubItemClick(v, position);
                }
            });
        }
        String bookId = SPUtil.getObject(App.userInfo.getEmail() + "BookId" + book.getId(), String.class, "-1");
        if (isShowSub) {
            holder.mSub.setText(book.getAuthor());
            holder.mSub.setEnabled(false);
        } else {
            if (bookId.equals(String.valueOf(book.getId()))) {
                holder.mSub.setText(book.getAuthor());
                holder.mSub.setEnabled(false);
            } else {
                holder.mSub.setText("订阅");
            }
        }


        holder.mTime.setText(book.getRefreshTimeStr());
    }

    @Override
    public int getItemCount() {
        int count = datas == null ? 0 : datas.size();
        return count;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_book_com_title)
        TextView mComTitle;
        @InjectView(R.id.item_book_img)
        ImageView mImageView;
        @InjectView(R.id.item_book_title)
        TextView mTitile;
        @InjectView(R.id.item_book_explain)
        TextView mExplain;
        @InjectView(R.id.item_book_time)
        TextView mTime;
        @InjectView(R.id.item_book_sub)
        TextView mSub;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public void setOnItemClickLinsenter(OnItemClickLinsenter itemClickLinsenter) {
        this.itemClickLinsenter = itemClickLinsenter;
    }

    public interface OnItemClickLinsenter {
        void onItemClick(View v, int position);
    }

    public void setSubItemClickLinsenter(onSubItemClickLinsenter subItemClickLinsenter) {
        this.subItemClickLinsenter = subItemClickLinsenter;
    }

    public interface onSubItemClickLinsenter {
        void onSubItemClick(View v, int position);
    }


}
