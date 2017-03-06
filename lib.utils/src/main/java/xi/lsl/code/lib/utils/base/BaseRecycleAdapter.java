package xi.lsl.code.lib.utils.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/21.
 */

public abstract class BaseRecycleAdapter<T extends BaseRecycleAdapter.Item> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface Item {
        int TYPE_HEADER = 0;
        int TYPE_FOOTER = 1;

        /**
         * 返回item类型，其值不能为0或者1；
         *
         * @return
         */
        int getType();
    }


    protected int headerViewRes;
    protected int footerViewRes;

    protected boolean hasHeader;
    protected boolean hasFooter;

    protected List<T> list = null;


    public int getHeaderViewRes() {
        return headerViewRes;
    }

    public void setHeaderViewRes(int headerViewRes) {
        if (this.headerViewRes != 0) {
            if (!hasHeader) {
                this.headerViewRes = headerViewRes;
                this.hasHeader = true;
                notifyItemChanged(0);
            } else {
                this.headerViewRes = headerViewRes;
                notifyItemChanged(0);
            }
        } else {
            if (hasHeader()) {
                this.hasHeader = false;
                notifyItemRemoved(0);
            }
        }
    }

    public int getFooterViewRes() {
        return footerViewRes;
    }

    public void setFooterViewRes(int footerViewRes) {
        if (footerViewRes != 0) {
            if (!hasFooter()) {
                this.footerViewRes = footerViewRes;
                this.hasFooter = true;
                if (hasHeader()) {
                    notifyItemChanged(list.size() + 1);
                } else {
                    notifyItemChanged(list.size());
                }
            }
        } else {
            if (hasFooter()) {
                this.hasFooter = false;
                if (hasHeader()) {
                    notifyItemRemoved(list.size() + 1);
                } else {
                    notifyItemRemoved(list.size());
                }
            }
        }


        this.footerViewRes = footerViewRes;
    }

    public boolean isHasHeader(int position) {

        return hasHeader() && position == 0;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean isHasFooter(int position) {
        if (hasHeader()) {
            return hasFooter() && position == list.size() + 1;
        } else {
            return hasFooter() && position == list.size();
        }
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public BaseRecycleAdapter(List<T> list) {
        this.list = list;
    }

    public BaseRecycleAdapter(List<T> list, int headerViewRes) {
        this.list = list;
        setHeaderViewRes(headerViewRes);
    }


    public BaseRecycleAdapter(int headerViewRes, int footerViewRes, List<T> list) {
        this.list = list;
        setHeaderViewRes(headerViewRes);
        setFooterViewRes(footerViewRes);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (hasHeader() && viewType == Item.TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(getHeaderViewRes(), parent, false);
            return new HeaderViewHolder(v);
        } else if (hasFooter() && viewType == Item.TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(getFooterViewRes(), parent, false);
            return new FooterViewHolder(v);
        } else {
            return onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
