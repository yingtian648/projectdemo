package mine.demo1.expandtab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

import mine.demo1.R;

/**
 * Created by jh on 2016/11/15.
 * 加了一个尾项【加载提示】
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected String imgHost;
    protected Context context;
    protected List<T> dataList;
    protected static final int TYPE_ITEM = 0;
    protected static final int TYPE_FOOTER = 1;
    protected boolean mShowFooter = true;
    protected int resourceID;//常规项布局ID
    protected DisplayImageOptions options;
    public BaseRecyclerAdapter(Context context, List<T> dataList, int resourceID) {
        this.context = context;
        this.dataList = dataList;
        this.resourceID = resourceID;
    }

    /**
     * 设置每一项的类型【用于区分是否是最后一项】
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public boolean ismShowFooter() {
        return mShowFooter;
    }

    public void setmShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    @Override
    public int getItemCount() {
        return dataList.size()+1;
    }

    //获取ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(resourceID, parent, false);
            return getNormalItemHolder(v);
        } else {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.footer_layout, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    //获取常规项的ViewHolder
    public abstract RecyclerView.ViewHolder getNormalItemHolder(View view);

    //加载提示项[尾项]
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public TextView textView;
        public FooterViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progress);
            textView = (TextView) view.findViewById(R.id.more_data_msg);
        }

    }
}
