package com.syt.jsw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syt.jsw.R;
import com.syt.jsw.activity.NewTopActivity;
import com.syt.jsw.activity.WeatherActivity;
import com.syt.jsw.utils.IntentUtils;

/**
 * 首页 适配器
 *
 * @author syt
 * created in 2021/5/5 16:50
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private String[] mDataSource;
    private Context mContext;

    public void setDataSource(Context context, String[] dataSource) {
        mDataSource = dataSource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextView.setText(mDataSource[position]);
        holder.linearLayout.setOnClickListener(v -> {
            switch (mDataSource[position]) {
                case "天气预报":
                    IntentUtils.startActivity(mContext, WeatherActivity.class);
                    break;
                case "新闻头条":
                    IntentUtils.startActivity(mContext, NewTopActivity.class);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSource.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
            linearLayout = itemView.findViewById(R.id.layout_text_item);
        }
    }
}
