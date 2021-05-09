package com.syt.jsw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syt.jsw.R;
import com.syt.jsw.activity.NewListActivity;

/**
 * 新闻首页 适配器
 *
 * @author syt
 * created in 2021/5/5 16:50
 */
public class NewTopAdapter extends RecyclerView.Adapter<NewTopAdapter.MyViewHolder> {
    private String[] newTopData;
    private Context mContext;

    public void setDataSource(Context context, String[] dataSource) {
        newTopData = dataSource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_top_text_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextView.setText(newTopData[position]);
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, NewListActivity.class);
            switch (newTopData[position]) {
                case "推荐":
                    intent.putExtra("title", "热点推荐");
                    intent.putExtra("type", "top");
                    break;
                case "国内":
                    intent.putExtra("title", "国内资讯");
                    intent.putExtra("type", "guonei");
                    break;
                case "国际":
                    intent.putExtra("title", "国际资讯");
                    intent.putExtra("type", "guoji");
                    break;
                case "娱乐":
                    intent.putExtra("title", "娱乐资讯");
                    intent.putExtra("type", "yule");
                    break;
                case "体育":
                    intent.putExtra("title", "体育资讯");
                    intent.putExtra("type", "tiyu");
                    break;
                case "军事":
                    intent.putExtra("title", "军事资讯");
                    intent.putExtra("type", "junshi");
                    break;
                case "科技":
                    intent.putExtra("title", "科技资讯");
                    intent.putExtra("type", "keji");
                    break;
                case "财经":
                    intent.putExtra("title", "财经资讯");
                    intent.putExtra("type", "caijing");
                    break;
                case "时尚":
                    intent.putExtra("title", "时尚资讯");
                    intent.putExtra("type", "shishang");
                    break;
                case "游戏":
                    intent.putExtra("title", "游戏资讯");
                    intent.putExtra("type", "youxi");
                    break;
                case "汽车":
                    intent.putExtra("title", "汽车资讯");
                    intent.putExtra("type", "qiche");
                    break;
                case "健康":
                    intent.putExtra("title", "健康资讯");
                    intent.putExtra("type", "jiankang");
                    break;
            }
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newTopData.length;
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
