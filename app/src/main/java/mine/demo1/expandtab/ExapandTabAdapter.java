package mine.demo1.expandtab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by jh on 2017/1/19.
 */

public class ExapandTabAdapter extends BaseRecyclerAdapter<String> {

    public ExapandTabAdapter(Context context, List<String> dataList, int resourceID) {
        super(context, dataList, resourceID);
    }

    @Override
    public RecyclerView.ViewHolder getNormalItemHolder(View view) {
        return new NormalItemAdapter(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalItemAdapter) {
            NormalItemAdapter hold = (NormalItemAdapter) holder;
            hold.textView.setText(dataList.get(position));
        } else {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            if (!mShowFooter) {
                footerViewHolder.progressBar.setVisibility(View.GONE);
                footerViewHolder.textView.setText("没有更多了");
            } else {
                footerViewHolder.progressBar.setVisibility(View.VISIBLE);
                footerViewHolder.textView.setText("正在加载...");
            }
        }
    }

    class NormalItemAdapter extends RecyclerView.ViewHolder {
        TextView textView;

        public NormalItemAdapter(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
