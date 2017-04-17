package xi.lsl.code.app.com.comment;

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
import xi.lsl.code.app.com.R;
import xi.lsl.code.lib.utils.entity.BmobComment;
import xi.lsl.code.lib.utils.utils.Imageloader;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/4/17.
 */

public class ComAdapter extends RecyclerView.Adapter<ComAdapter.ViewHolder> {

    private Context mContext;
    private List<BmobComment.ResultsBean> datas;

    public ComAdapter(Context context, List<BmobComment.ResultsBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BmobComment.ResultsBean resultsBean = datas.get(position);
        Imageloader.display(mContext, resultsBean.user.getHeadurl(), holder.mHeadIco);
        holder.mUsername.setText(resultsBean.user.getEmail());
        holder.mContent.setText(resultsBean.comment_content);
        holder.mTime.setText(resultsBean.createdAt);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.item_com_img)
        ImageView mHeadIco;
        @InjectView(R.id.item_com_username)
        TextView mUsername;
        @InjectView(R.id.item_com_time)
        TextView mTime;
        @InjectView(R.id.item_com_content)
        TextView mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
