package com.syt.jsw.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syt.jsw.R;
import com.syt.jsw.pojo.NewDto;
import com.syt.jsw.web.NewDetailsWeb;

import java.util.List;

/**
 * 新闻列表 适配器
 *
 * @author syt
 * created in 2021/5/9 14:10
 */
public class NewAdapter extends BaseQuickAdapter<NewDto, BaseViewHolder> {

    public NewAdapter(int layoutResId, @Nullable List<NewDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewDto dto) {
        TextView source, title, date;
        ImageView imgMessage;
        LinearLayout linearLayout;
        linearLayout = helper.getView(R.id.layout_new_item);
        source = helper.getView(R.id.tv_source);
        title = helper.getView(R.id.tv_title);
        date = helper.getView(R.id.tv_date);
        imgMessage = helper.getView(R.id.img_message);

        source.setText(dto.getAuthor_name());
        title.setText(dto.getTitle());
        date.setText(dto.getDate());
        Glide.with(mContext)
                .load(dto.getThumbnail_pic_s())
                .asBitmap()
                .error(R.drawable.ic_null_img)
                .fallback(R.drawable.ic_null_img)
                .into(imgMessage);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewDetailsWeb.class);
                intent.putExtra("url", dto.getUrl());
                mContext.startActivity(intent);
            }
        });
    }
}
