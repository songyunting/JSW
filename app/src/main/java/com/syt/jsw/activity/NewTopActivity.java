package com.syt.jsw.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.syt.jsw.Interface.OnRequestResult;
import com.syt.jsw.R;
import com.syt.jsw.adapter.NewAdapter;
import com.syt.jsw.adapter.NewTopAdapter;
import com.syt.jsw.pojo.NewDto;
import com.syt.jsw.utils.JsonUtils;
import com.syt.jsw.utils.OkgoUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.syt.jsw.net.Url.J_API_KEY;
import static com.syt.jsw.net.Url.NEW_TOP;

/**
 * 新闻头条
 *
 * @author syt
 * created in 2021/5/9 16:34
 */
public class NewTopActivity extends AppCompatActivity implements OnRequestResult {

    @BindView(R.id.rl_new_top_view)
    RecyclerView newTopView;

    @BindView(R.id.rl_recommend_view)
    RecyclerView recommendView;

    @BindView(R.id.avi_loading)
    AVLoadingIndicatorView aviLoading;

    private NewAdapter newAdapter;
    private List<NewDto> newDtoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_top);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        recommendView.setLayoutManager(new LinearLayoutManager(this));
        newAdapter = new NewAdapter(R.layout.new_view_item, newDtoList);
        recommendView.setAdapter(newAdapter);
        Map<String, String> param = new HashMap<>();
        param.put("type", "top");
        param.put("key", J_API_KEY);
        param.put("page", "1");
        param.put("page_size", "5");
        OkgoUtils.getInstance().get(NEW_TOP, param, 0, NewTopActivity.this);
    }

    private void initView() {
        newTopView = findViewById(R.id.rl_new_top_view);
        recommendView = findViewById(R.id.rl_recommend_view);
        aviLoading = findViewById(R.id.avi_loading);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        newTopView.setLayoutManager(mLayoutManager);
        String[] dataSource = {"推荐", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚", "游戏", "汽车", "健康"};
        NewTopAdapter newTopAdapter = new NewTopAdapter();
        newTopAdapter.setDataSource(this, dataSource);
        newTopView.setAdapter(newTopAdapter);
    }

    @OnClick(R.id.left_back)
    void back() {
        finish();
    }

    @Override
    public void onRequestStart(int reqCode) {
        aviLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(int code, String json) {
        if (!StringUtils.isEmpty(json)) {
            String data = JsonUtils.getValue(json, "result");
            JSONObject jsonObject = JSONObject.parseObject(data);
            String data2 = jsonObject.getString("data");
            newDtoList = JsonUtils.getList(data2, NewDto.class);
            if (newDtoList.size() > 0) {
                newAdapter.addData(newDtoList);
            }
        }
    }

    @Override
    public void onFailed(int code, String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onRequestFinish(int reqCode) {
        aviLoading.setVisibility(View.GONE);
    }
}