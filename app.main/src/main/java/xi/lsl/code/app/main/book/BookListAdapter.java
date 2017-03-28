package xi.lsl.code.app.main.book;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xi.lsl.code.app.main.R;
import xi.lsl.code.lib.utils.entity.Book;
import xi.lsl.code.lib.utils.utils.Imageloader;

/**
 * Created by lishoulin on 2017/3/21.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private List<Book> mBooks;
    private WeakReference<Context> mWeakReference;

    public BookListAdapter(Context context, List<Book> books) {
        if (books == null)
            books = new ArrayList<>();
        this.mBooks = books;
        this.mWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mBooks.get(position);

        Imageloader.display(mWeakReference.get(), book.getFrontCover(), holder.mHead);

        holder.mTitle.setText(book.getTitle());
        holder.mAuther.setText(book.getAuthor());

        Resources resources = mWeakReference.get().getResources();
        //热度
        String hot = String.format(resources.getString(R.string.hot), "222");
        holder.mHot.setText(hot);
        holder.mDes.setText(book.getExplain());

    }

    @Override
    public int getItemCount() {
        return mBooks == null ? 0 : mBooks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.book_item_head)
        ImageView mHead;
        @InjectView(R.id.book_item_title)
        TextView mTitle;
        @InjectView(R.id.book_item_auther)
        TextView mAuther;
        @InjectView(R.id.book_item_hot)
        TextView mHot;
        @InjectView(R.id.book_item_des)
        TextView mDes;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
